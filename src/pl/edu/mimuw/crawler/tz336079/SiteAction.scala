package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;

trait SiteAction {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	def process(doc: Document, params: Parameters): Boolean;
	def handleError(errorInfo: String): Unit;

	def getAllURLs(doc: Document): List[String] = {
		val anchors: Elements = doc.select("a");
		var res: List[String] = Nil;
		for (anchor <- anchors.iterator()) {
			res = anchor.attr("href")::res;
		}
		res;
	}
}
