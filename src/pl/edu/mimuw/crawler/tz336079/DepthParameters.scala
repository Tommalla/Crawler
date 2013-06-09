package pl.edu.mimuw.crawler.tz336079;

case class DepthParameters(depth: Int) extends Parameters {
	def getChild(): Parameters = {
		new DepthParameters(depth + 1);
	};
}
