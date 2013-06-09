package demos

import pl.edu.mimuw.crawler.tz336079._;
import java.lang.IllegalArgumentException;

object Demo1 {
	def main(args: Array[String]) = {
		if (args.length < 1)
			throw new IllegalArgumentException("Not enough arguments!");
		if (Methods.isURLValid(args(0)) == false)
			throw new IllegalArgumentException("Malformed url!");

		val params: NullParameters = new NullParameters();
		val action: DomainCounterAction = new DomainCounterAction(Methods.getBaseDomain(args(0)));
		val crawler: Crawler = new Crawler(action, params);

		crawler.addTargetURL(args(0));
		crawler.start();
		for ((site, qty) <- action.getResults()) {
			println(site + ": " + qty);
		}
		//optional:
		//println("Errors: " + action.getErrors());
	}
}
