package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;
import scala.collection.immutable.HashMap;


class DomainCounterAction(baseDomain: String) extends SiteAction {
	private var errors: List[String] = Nil;
	private var resMap: HashMap[String, Int] = new HashMap[String, Int]();

	def handleError(errorInfo: String): Unit = {
		this.errors = errorInfo::this.errors;
	}

	def process(doc: Document, params: Parameters): Boolean = {
		if ( Methods.isURLExternal(doc.baseUri) && Methods.getBaseDomain(doc.baseUri) != baseDomain )
			return false;

		for (url <- this.getAllURLs(doc)) url match {
			case Methods.External(domain, _) => {
					if (domain != baseDomain) {
						val qty: Int = if (resMap.contains(domain)) resMap.get(domain).get else 0;
						resMap = (resMap - domain) + ((domain, qty + 1));
					}
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
