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
	def getNodeFor(url: String): Option[Node] = {
		map.get(url);
	}

	def addNode(node: Node): Unit = node match {
		case Node(url: String, _) => map += ((url, node));
	}
}
