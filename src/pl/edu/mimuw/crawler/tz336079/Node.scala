package pl.edu.mimuw.crawler.tz336079

class Node(url: String) {
	var neighbours: List[Node] = Nil;

	//processes the node (TODO: add action!)
	def process(/* Site action here */): Unit = {
		//TODO implement:
		//create the list of neighbours from urls,
		//use SiteAction to collect statistics
	}
}
