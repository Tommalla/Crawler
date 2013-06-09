package demos

import pl.edu.mimuw.crawler.tz336079._;

object Demo2 {
	def main(args: Array[String]) = {
		if (args.length < 2)
			throw new IllegalArgumentException("Not enough arguments!");
		if (Methods.isURLLocal(args(0)) == false)
			throw new IllegalArgumentException("Malformed url!");

		val params: DepthParameters = new DepthParameters(0);
		val action: ChainedExternalSitesCounterAction =
			new ChainedExternalSitesCounterAction(Methods.getBaseDomain(args(0)),
							      augmentString(args(1)).toInt );

		val crawler: Crawler = new Crawler(action, params);
		crawler.addTargetURL(args(0));
		crawler.start();
		println(action.getResult());
		println(action.getErrors());
	}
}
