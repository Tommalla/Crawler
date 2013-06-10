package pl.edu.mimuw.crawler.tz336079

import scala.util.matching.Regex;
import _root_.org.jsoup.nodes._;

/**
 * An object with frequently used functions / variables.
 */
object Methods {
	/**
	 * A [[Regex]] for external urls.
	 */
	val External = new Regex("""https?://([^/]*)(/.*){0,1}""", "domain", "localPath");
	/**
	 * A [[Regex]] for local urls.
	 */
	val Local = new Regex("""(.*)/([^/]*)""");

	/**
	 * Returns the base domain of the url.
	 *
	 * @return If the url matches [[Local]], the path to the highest directory
	 * above the file is returned. Otherwise, it's the domain on which the
	 * file is hosted.
	 */
	def getBaseDomain(url: String): String = url match {
		case External(domain, _) => domain;
		case Local(base, rest) => base;
		case _ => throw new Exception("Methods.getBaseDomain: " + url + " is a malformed url");
	}

	/**
	 * Returns true if the url is external (matches [[External]]).
	 */
	def isURLExternal(url: String): Boolean = url match {
		case External(_, _) => true;
		case _ => false;
	}

	/**
	 * Returns true if the url is local (matches [[Local]]).
	 */
	def isURLLocal(url: String): Boolean = url match {
		case Local(_, _) => true
		case _ => false
	}

	/**
	 * Returns a corrected url.
	 *
	 * @param url The url to be corrected.
	 * @param doc The url of the document on which url was found.
	 *
	 * @return If the url was a valid external url, it's returned without
	 * changing. Otherwise, it's fixed to become an absolute path to the file.
	 */
	def getCorrectUrl(url: String, doc: String): String = {
		if (isURLExternal(url))
			url;
		else doc match {
			case Local(base, _) => base + "/" + url;
			case _ => throw new Exception("Methods.getCorrectUrl: " + url + " is a malformed url");
		}
	}
}
