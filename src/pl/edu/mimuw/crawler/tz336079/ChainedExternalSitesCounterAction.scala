package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;

class ChainedExternalSitesCounterAction(baseDomain: String, depthLimit: Int) extends SiteAction {
	private var res: Int = 0;
	private var errors: List[String] = Nil;

	def process(doc: Document, params: Parameters): Boolean = {
		params match {
			case DepthParameters(depth) => if (depth > depthLimit) return false;
			case _ => return false;
		}

		if (Methods.isURLExternal(doc.baseUri()))
			return false
		/*	doc.baseUri() match {
		 case Methods.External(_, _) => return false
		 case _ => /*don't bother */
		 }*/

		res = res + 1;

		true;
	}

	def handleError(errorInfo: String): Unit = {
		errors = errorInfo::errors;
	}

	def getErrors(): List[String] = {
		errors;
	}

	def getResult(): Int = {
		res;
	}
}
