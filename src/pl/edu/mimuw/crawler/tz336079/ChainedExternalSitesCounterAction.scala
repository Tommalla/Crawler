package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;

/**
 * A [[SiteAction]] implementation designed to work with DepthParameters. Crawls
 * the sites up to depthLimit and counts the number of references to local sites.
 *
 *  @param baseDomain The base domain of the site.
 *  @param depthLimit The depth limit for the search.
 */
class ChainedExternalSitesCounterAction(baseDomain: String, depthLimit: Int) extends SiteAction {
	private var res: Int = 0;
	private var errors: List[String] = Nil;

	/**
	 * Returns false if the depthLimit has been reached or the site is external.
	 * Otherwise counts the site and returns true.
	 */
	def process(doc: Document, params: Parameters): Boolean = {
		res = res + 1;

		params match {
			case DepthParameters(depth) => if (depth + 1 > depthLimit) return false;
			case _ => return false;
		}

		true;
	}

	/**
	 * Returns true if url is local.
	 */
	def shouldProcess(url: String, params: Parameters): Boolean = {
		Methods.isURLExternal(url) == false
	}

	/**
	 * Adds the error to the internal list.
	 */
	def handleError(errorInfo: String): Unit = {
		errors = errorInfo::errors;
	}

	/**
	 * Returns the list of errors.
	 */
	def getErrors(): List[String] = {
		errors;
	}

	/**
	 * Returns the number of local sites visited.
	 */
	def getResult(): Int = {
		res;
	}
}
