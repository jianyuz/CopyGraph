package memoryVisibility;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Deque1
{
public static void main( String[] args )
  {
  ArrayDeque deque = new ArrayDeque();
  deque.addLast( "bat" );
  deque.add( "cat" );
  deque.addFirst( "ape" );
  System.out.println( deque );
  System.out.println();

  System.out.println( "Enumerate the Deque" );
  Iterator e = deque.iterator();
  while ( e.hasNext() )
    System.out.println( e.next() );
  System.out.println();

  System.out.println( "Iterate through the Deque" );

  System.out.println( "Demonstrate access" );
  System.out.println( "deque.front() = " + deque.getFirst() );
  System.out.println( "deque.back() = " + deque.getLast());
  System.out.println();

  System.out.println( "Demonstrate modification" );
  deque.push("fox" );
  System.out.println( deque );

  deque.pollFirst();
  System.out.println( "After popFront() = " + deque );

  deque.pollLast();
  System.out.println( "After popBack() = " + deque );
  }
}