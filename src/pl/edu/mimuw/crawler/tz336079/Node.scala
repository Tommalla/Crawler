package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.Jsoup;
import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;

class Node(url: String) {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	var neighbours: List[Node] = Nil;

	//processes the node (TODO: add action!)
	def process(/* Site action here */): Unit = {

		try {
			val doc: Document = Jsoup.connect(url).get();
			//TODO process the site with action
			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) {
				neighbours = Graph.getNodeFor(anchor.attr("href"))::neighbours;
			}
		} catch {
			case e: Exception => //TODO use error method from SiteAction
		}
		//TODO implement:
		//create the list of neighbours from urls,
		//use SiteAction to collect statistics
	}
}
