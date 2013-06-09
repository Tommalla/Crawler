package demos

import pl.edu.mimuw.crawler.tz336079._;

object Demo1 {
	def main(args: Array[String]) = {
		val params: NullParameters = new NullParameters();
		val action: DomainCounterAction = new DomainCounterAction();
		val crawler: Crawler = new Crawler(action, params);

		crawler.addTargetURL("http://www.mimuw.edu.pl/~kdr/test/1/");
		crawler.start();
		println(action.getResults());
	}
}
