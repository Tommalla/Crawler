package pl.edu.mimuw.crawler.tz336079

class Crawler {

	private var isRunning: Boolean = false;

	def start(): Unit = {
		isRunning = true;
		while (isRunning && !SitesQueue.empty())
			SitesQueue.getFront().get.process(/*Add SiteAction*/);
	}

	def stop(): Unit = {
		isRunning = false;
	}
}
