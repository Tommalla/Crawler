package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.Jsoup;
import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;import java.io.File


private[tz336079] case class Node(url: String, params: Parameters) {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	private var neighbours: List[Node] = Nil;

	def process(action: SiteAction): Unit = {
		//println("Processing " + url);
		try {
			val doc: Document = if (Methods.isURLExternal(url)) Jsoup.connect(url).get()
						else Jsoup.parse(new File(url), "UTF-8");

			//process the site and check if the user wants to go any further
			if (action.process(doc, params) == false)
				return;

			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) {
				val dst: String = {
					if (Methods.isURLExternal(anchor.absUrl("href")))
						anchor.absUrl("href")
					else
						Methods.getCorrectUrl(anchor.attr("href"), doc) };

				Graph.getNodeFor(dst) match {
					case None => {	//not present in the graph
						val v: Node = new Node(dst, params.getChild());
						Graph.addNode(v);
						neighbours = v::neighbours;
						SitesQueue.addURL(dst);
					}
					case _ => /* don't bother - already in the graph and queued/done */
				}
			}
		} catch {
			case e: Exception => action.handleError(e.getMessage());
		}
	}
}
