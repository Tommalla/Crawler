package pl.edu.mimuw.crawler.tz336079

/**
 * A simple class providing 'Null' Parameters that do nothing.
 */
class NullParameters extends Parameters {
	def getChild(): Parameters = {
		this;
	}

	def canContinue(): Boolean = {
		true;
	}
}
