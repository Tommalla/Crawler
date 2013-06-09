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

			//process the site and check if the user wants to go any further
			if (action.process(doc, params) == false)
				return;

			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) {
				val dst: String = anchor.absUrl("href");
				Graph.getNodeFor(dst) match {
					case None => {
						val v: Node = new Node(dst, params.getChild());
						Graph.addNode(v);
						neighbours = v::neighbours;
						SitesQueue.addURL(dst);
					}
					case _ => /* don't bother... */
				}
			}
		} catch {
			case e: Exception => action.handleError(e.getMessage());
		}
	}
}
