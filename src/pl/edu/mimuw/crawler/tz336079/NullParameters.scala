package pl.edu.mimuw.crawler.tz336079

/**
 * A simple class providing 'Null' Parameters that do nothing.
 */
case class NullParameters extends Parameters {
	/**
	 * Returns self.
	 */
	def getChild(): Parameters = {
		this;
	}

	/**
	 * Returns true.
	 */
	def canContinue(): Boolean = {
		true;
	}
}
