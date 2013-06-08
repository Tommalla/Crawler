package pl.edu.mimuw.crawler.tz336079

import scala.collection.mutable.HashMap

object Graph {
	var map: HashMap[String, Node] = new HashMap[String, Node]();

	def isURLPresent(url: String): Boolean = {
		map.contains(url);
	}

	/**
	 * @brief Returns a node for the given url
	 * If the node is not present, the method creates it.
	 */
	def getNodeFor(url: String): Node = {

		if (this.isURLPresent(url))
			map.get(url).get;
		else {
			map += ((url, new Node(url)));
			map.get(url).get;
		}
	}
}
