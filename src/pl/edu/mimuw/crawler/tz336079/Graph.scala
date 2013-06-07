package pl.edu.mimuw.crawler.tz336079

import scala.collection.mutable.HashMap

object Graph {
	var map: HashMap[String, Node] = new HashMap[String, Node]();

	def isURLPresent(url: String): Boolean = {
		map.contains(url);
	}

	def getNodeFor(url: String): Option[Node] = {
		map.get(url);
	}
}
