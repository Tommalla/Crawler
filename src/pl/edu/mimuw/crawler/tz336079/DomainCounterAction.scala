package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;
import scala.collection.immutable.HashMap;
import scala.util.matching.Regex



class DomainCounterAction(baseDomain: String) extends SiteAction {
	private var errors: List[String] = Nil;
	private var resMap: HashMap[String, Int] = new HashMap[String, Int]();

	def handleError(errorInfo: String): Unit = {
		this.errors = errorInfo::this.errors;
	}

	def process(doc: Document, params: Parameters): Boolean = {

		val External = new Regex("""https?://([^/]*)(/.*){0,1}""", "domain", "locPath");
		val Local = new Regex("""(.*)/([^/]*)""");


		if ( (doc.baseUri match {
			case External(domain, localPath) => domain == baseDomain
			case _ => true;
		}) == false ) return false;

		for (url <- this.getAllURLs(doc)) url match {
			case External(domain, locPath) => {
					val qty: Int = if (resMap.contains(domain)) resMap.get(domain).get else 0;
					resMap = (resMap - domain) + ((domain, qty + 1));
			}
			case _ => /* Local url, don't bother */
		}

		true;
	}

	def getResults(): List[(String, Int)] = {
		var tmp: List[(String, Int)] = resMap.foldLeft[List[(String, Int)]](Nil)((acc, x) => x::acc)
		tmp.sortWith({ case ((keyA, valA), (keyB, valB)) => if (valA != valB) valA > valB else keyA < keyB})
	}

	def getErrors(): List[String] = {
		this.errors;
	}
}
