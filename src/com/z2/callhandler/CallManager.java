package com.z2.callhandler;

import java.util.LinkedList;
import java.util.Queue;


public class CallManager {

	private static final int levels = 3;
	Employee[][] lEmployees = new Employee[levels][];
	Queue<Call>[] callq = new LinkedList[levels];
	
	public CallManager(){//make it singleton
		lEmployees[0] = new Employee[10];
		lEmployees[1] = new Employee[1];
		lEmployees[2] = new Employee[2];
		callq[0] = new LinkedList<Call>();
		callq[1] = new LinkedList<Call>();
		callq[2] = new LinkedList<Call>();
	}
	
	public Employee getCallHandler(Call c) {
		for(int level = c.getRank(); level< levels-1; level++){
			Employee[] es = lEmployees[level];
			for(Employee e: es){
				if(e.isFree()){
					return e;
				}
			}
		}
		return null;
	}
	
	public void dispatchCall(Call c){
		Employee e = getCallHandler(c);
		if(e != null){
			e.receiveCall(c);
		}else{
			callq[c.getRank()].add(c);
		}
	}


}
