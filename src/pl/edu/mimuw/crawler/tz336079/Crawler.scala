package pl.edu.mimuw.crawler.tz336079

class Crawler(action: SiteAction, initialParams: Parameters) {

	private var isRunning: Boolean = false;

	def start(): Unit = {
		isRunning = true;
		while (isRunning && !SitesQueue.empty()) {
			SitesQueue.getFront().process(action);
		}
	}

	def stop(): Unit = {
		isRunning = false;
	}

	def addTargetURL(url: String): Unit = {
		if (Graph.getNodeFor(url) == None)
			Graph.addNode(new Node(url, this.initialParams));
		SitesQueue.addURL(url);
	}
}
