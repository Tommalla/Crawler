package pl.edu.mimuw.crawler.tz336079;

/**
 * [[Parameters]] implementation that introduces depth to the search.
 *
 * @param depth The current depth.
 */
case class DepthParameters(depth: Int) extends Parameters {
	/**
	 * Returns new [[DepthParameters]] with depth + 1 .
	 */
	def getChild(): Parameters = {
		new DepthParameters(depth + 1);
	};
}
