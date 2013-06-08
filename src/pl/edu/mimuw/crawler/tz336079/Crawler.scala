package pl.edu.mimuw.crawler.tz336079

class Crawler(action: SiteAction) {

	private var isRunning: Boolean = false;

	def start(): Unit = {
		isRunning = true;
		while (isRunning && !SitesQueue.empty())
			SitesQueue.getFront().get.process(action);
	}

	def stop(): Unit = {
		isRunning = false;
	}

	def addTargetURL(url: String): Unit = {
		SitesQueue.addURL(url);
	}
}
