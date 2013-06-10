package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.Jsoup;
import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;
import java.io.File;import java.net.URI



private[tz336079] case class Node(url: String, params: Parameters) {
	implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	def process(action: SiteAction): Unit = {
		if (action.shouldProcess(url, params) == false)
			return;

		//println("Processing " + url);
		try {
			val doc: Document = if (Methods.isURLExternal(url)) Jsoup.connect(url).get()
			else {
				val uri: URI = new URI(url);
				Jsoup.parse(new File(uri), "UTF-8", uri.toString());
			}

			//process the site and check if the user wants to go any further
			if (action.process(doc, params) == false)
				return;

			//create neighbours:
			val anchors: Elements = doc.select("a");
			for (anchor <- anchors.iterator()) {
				val dst: String = anchor.absUrl("href");

				Graph.getNodeFor(dst) match {
					case None => {	//not present in the graph
						Graph.addNode(new Node(dst, params.getChild()));
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
