package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;
import scala.collection.immutable.HashMap;


class DomainCounterAction extends SiteAction {
	private var errors: List[String] = Nil;
	private var resMap: HashMap[String, Int] = new HashMap[String, Int]();

	def handleError(errorInfo: String): Unit = {
		this.errors = errorInfo::this.errors;
	}

	def process(doc: Document, params: Parameters): Unit = {
		val Pattern = "http://([^/]*)${domain}/(.*)".r;
		for (url <- this.getAllURLs(doc)) url match {
			case Pattern(domain) => resMap = (resMap - domain) + ((domain, resMap.get(domain).get + 1));
		}
	}

	def getResults(): List[(String, Int)] = {
		var tmp: List[(String, Int)] = resMap.foldLeft[List[(String, Int)]](Nil)((acc, x) => x::acc)
		tmp.sortWith({ case ((keyA, valA), (keyB, valB)) => if (valA < valB) true else keyA < keyB })
		tmp;
	}
}
