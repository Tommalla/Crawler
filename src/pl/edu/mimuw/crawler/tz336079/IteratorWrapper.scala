package pl.edu.mimuw.crawler.tz336079

/**
 * An iterator wrapper for scala based on this:
 * http://stackoverflow.com/questions/495741/iterating-over-java-collections-in-scala
 */
class IteratorWrapper[A](iter:java.util.Iterator[A])
{
    def foreach(f: A => Unit): Unit = {
        while(iter.hasNext){
          f(iter.next)
        }
    }
}
