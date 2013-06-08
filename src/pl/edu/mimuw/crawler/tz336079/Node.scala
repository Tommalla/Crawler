package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.Jsoup;
import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;

case class Node(url: String, params: Parameters) {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	var neighbours: List[Node] = Nil;

	def process(action: SiteAction): Unit = {

		try {
			val doc: Document = Jsoup.connect(url).get();
			action.process(doc, params);
			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) Graph.getNodeFor(anchor.attr("href")) match {
				case v: Option[Node] => /*been there, done that*/
				case None => {
					val v: Node = new Node(anchor.attr("href"), params.getChild());
					Graph.addNode(v);
					neighbours = v::neighbours;
					SitesQueue.addURL(url);
				}
			}
		} catch {
			case e: Exception => action.handleError(e.getMessage());
		}
	}
}
