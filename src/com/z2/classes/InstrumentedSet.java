package com.z2.classes;

import java.util.Collection;
import java.util.Set;

public class InstrumentedSet<E> extends ForwardingSet<E>{

	private int addCount = 0;
	/*
	public InstrumentedSet(){
		super(null);
	}*/
	
	public InstrumentedSet(Set<E> s){
		//implicit super constructor is undefined.
		//we must define a constructor here.
	    //and we must explicitly call the super's implicit constructor.
		super(s);
	}
	
	@Override public boolean add(E e){
		addCount ++;
		return super.add(e);//calls the wrappee's method.
		//doesn't know the implementation details.
	}
	
	@Override public boolean addAll(Collection<? extends E> c){
		addCount += c.size();
		return super.addAll(c);//call the wrappe's add.
		//it won't call the instrumentSet's add method any more.
	}
	
	public int getAddCount(){
		return addCount;
	}
}

