package pl.edu.mimuw.crawler.tz336079

import _root_.org.jsoup.nodes._;

trait SiteAction {
	def process(doc: Document): Unit;
	def error(errorInfo: String): Unit;
}
