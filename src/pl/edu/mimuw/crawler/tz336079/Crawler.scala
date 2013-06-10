package pl.edu.mimuw.crawler.tz336079

/**
 * An automated web crawler.
 *
 * @constructor Create a new crawler.
 * @param action user-defined action to perform with every site
 * @param initialParams a set of user-defined parameters to change
 * as the sites graph expands. The parameters are passed to the action as well as
 * the current site's content (see [[SiteAction]]).
 */
import java.io.File

class Crawler(action: SiteAction, initialParams: Parameters) {

	private var isRunning: Boolean = false;

	/**
	 * Starts the crawler.
	 *
	 * This method blocks the instance until something calls [[Crawler.stop]]
	 * or the queue of the sites empties.
	 *
	 * It basically (indirectly) calls [[SiteAction.process]] with the next
	 * site from the queue.
	 */
	def start(): Unit = {
		isRunning = true;
		while (isRunning && !SitesQueue.empty()) {
			SitesQueue.getFront().process(action);
		}
	}

	/**
	 * Stops the crawler.
	 *
	 * See [[Crawler.start]].
	 */
	def stop(): Unit = {
		isRunning = false;
	}

	/**
	 * Adds a URL to the queue of the sites to be processed.
	 *
	 * @param url Can be both - a url starting with 'http://' or a [non-]relative
	 * path to a file.
	 */
	def addTargetURL(url: String): Unit = {
		val fixedUrl: String = if (Methods.isURLExternal(url)) url else
			(new File(url)).toURI.toString();

		if (Graph.getNodeFor(fixedUrl) == None)
			Graph.addNode(new Node(fixedUrl, this.initialParams));
		SitesQueue.addURL(fixedUrl);
	}
}
