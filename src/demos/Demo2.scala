package demos

import pl.edu.mimuw.crawler.tz336079._;

object Demo2 {
	def main(args: Array[String]) = {
		if (args.length < 2)
			throw new IllegalArgumentException("Not enough arguments!");
		if (Methods.isURLLocal(args(0)) == false)
			throw new IllegalArgumentException("Malformed url!");

		val params: DepthParameters = new DepthParameters(0);
		
	}
}
