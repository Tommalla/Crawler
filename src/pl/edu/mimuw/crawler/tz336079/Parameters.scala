package pl.edu.mimuw.crawler.tz336079

/**
 * A trait the user has to implement in order to have own parameters for the
 * Nodes that could limit the graph search and building in any way.
 *
 * See [[SiteAction]].
 */
trait Parameters {
	/**
	 * Returns parameters for the child node
	 */
	def getChild(): Parameters;
}
