package com.z2.expandList;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListsIterator<T> implements Iterator<T>{

	private Iterator<? extends Collection <T>> masterIt;
	private Iterator<T> currentIt;
	private Iterator<T> removeFrom;
	
	public ListsIterator (Collection<? extends Collection<T>> nestedCollection){
		this.masterIt = nestedCollection.iterator();
	}
	
	@Override
	public boolean hasNext() {
		boolean currentHasNext = false;
		Collection<T> next;
		while((currentIt == null || 
				!(currentHasNext = currentIt.hasNext())) && masterIt.hasNext()){
			next=masterIt.next();
			if(next != null){
				currentIt = next.iterator();
			}
		}
		return currentHasNext;
	}

	@Override
	public T next() {
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		if(currentIt != null){
			removeFrom = currentIt;
			return currentIt.next();
		}
		return null;
	}

	@Override
	public void remove() {
		if(removeFrom == null) throw new IllegalStateException();
		assert(removeFrom != null);
		removeFrom.remove();
		removeFrom = null;
	}


}
