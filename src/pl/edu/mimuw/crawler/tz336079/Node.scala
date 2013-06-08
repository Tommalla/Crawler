package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.Jsoup;
import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;

class Node(url: String) {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	var neighbours: List[Node] = Nil;

	def process(action: SiteAction): Unit = {

		try {
			val doc: Document = Jsoup.connect(url).get();
			action.process(doc);
			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) {
				neighbours = Graph.getNodeFor(anchor.attr("href"))::neighbours;
			}
		} catch {
			case e: Exception => action.error(e.getMessage());
		}
	}
}
