package pl.edu.mimuw.crawler.tz336079
import scala.collection.mutable.Queue

//TODO fiddle with private, package etc.

object SitesQueue {
	private var queue: Queue[Node] = new Queue[Node]();

	def addURL(url: String): Unit = {
		//TODO find URL in Graph
		//if present - append Node,
		//else, - create node in graph and append it
	}

	def getFront(): Option[Node] = {
		if (queue.size > 0)
			queue.dequeue().asInstanceOf[Option[Node]];
		else
			None;
	}
}
