package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;
import _root_.org.jsoup.select._;

/**
 * The trait that user has to implement and pass to the [[Crawler]] in order to
 * perform any action on the sites.
 */
trait SiteAction {
	/**
	 * A necessary java collections iterator wrapper for scala.
	 */
	protected implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter);

	/**
	 * This method is called by the [[Node]] on every site it decides to
	 * visit.
	 *
	 * @param doc The full [[Document]] that is being parsed.
	 * @param params Current node [[Parameters]]
	 *
	 * @return It's up to the implementation of [[SiteAction]] to decide
	 * whether it wants to go deeper into the links of this document or not.
	 * Returning false will stop the process.
	 */
	def process(doc: Document, params: Parameters): Boolean;
	/**
	 * Checks if the search should continue based on the url and parameters.
	 */
	def shouldProcess(url: String, params: Parameters): Boolean;
	/**
	 * Whenever an error occurs, the [[Node]] will call this method with the
	 * exception message.
	 */
	def handleError(errorInfo: String): Unit;

	/**
	 * Returns all the urls introduced by the <a> tag from the [[Document]]
	 */
	protected def getAllURLs(doc: Document): List[String] = {
		val anchors: Elements = doc.select("a");
		var res: List[String] = Nil;
		for (anchor <- anchors.iterator()) {
			res = anchor.absUrl("href")::res;
		}
		res;
	}
}
