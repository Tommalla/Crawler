/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.mimuw.crawler.tz336079

import scala.util.matching.Regex;

object Methods {
	val External = new Regex("""https?://([^/]*)(/.*){0,1}""", "domain", "locPath");
	val Local = new Regex("""(.*)/([^/]*)""");

	def getBaseDomain(url: String): String = url match {
		case External(domain, _) => domain;
		case _ => throw new Exception("Methods.getBaseDomain: " + url + " is a malformed url");
	}

	def isURLValid(url: String): Boolean = url match {
		case External(_, _) => true;
		case _ => false;
	}

	def isURLLocal(url: String): Boolean = url match {
		case Local(_, _) => true
		case _ => false
	}
}
