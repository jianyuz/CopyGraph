package memoryVisibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Size{
	small, medium, large;
	
	
}

public class LockerTest{
	public static void main(String[] args){
		int[] numOfLockers = new int[] {2, 3, 4};
		LockerManager lockerManager = new LockerManager(numOfLockers);
		Bag b = new Bag("smallbag", Size.small);
		Ticket t = lockerManager.findLockerForBag(b);
		Bag b1 = new Bag("smallbag1", Size.small);
		Ticket t1 = lockerManager.findLockerForBag(b1);
		Bag b2 = new Bag("smallbag2", Size.small);
		Ticket t2 = lockerManager.findLockerForBag(b2);
		
		Bag b3 = new Bag("smallbag3", Size.small);
		Ticket t3 = lockerManager.findLockerForBag(b3);
		
		System.out.println(lockerManager.getBagByTicket(t3));
		System.out.println(lockerManager.getBagByTicket(t1));
		
		Ticket t4 = lockerManager.findLockerForBag(b3);
		Bag b4 = new Bag("mediumbag1", Size.medium);
		Ticket t5 = lockerManager.findLockerForBag(b4);
		System.out.println(lockerManager.getBagByTicket(t4));
		System.out.println(lockerManager.getBagByTicket(t5));
	}
}

class Locker<T> {
	private int id;
	private Size size;
	private T item;
	
	public Locker(int id, Size size){
		this.id = id;
		this.size = size;
	}
	
	public Size getSize(){
		return this.size;
	}
	
	public Ticket store(T item){
		this.item = item;
		return new Ticket(this.id, this.size);
	}
	
	public T retrieve(Ticket t){
		if(t.getLockerNumber() == this.id){
			T tmp =  this.item;
			this.item = null;
			return tmp;
		}
		return null;
	}
	
	public boolean isEmpty(){
		return item == null;
	}
	
}

class Ticket{
	private static int baseId= 0;
	private int id;	
	private int lockerNumber;
	private Size size;
	
	public Ticket(int lockerNumber, Size size){
		id = ++ baseId;
		this.lockerNumber = lockerNumber;
		this.size = size;
	}
	
	public int getLockerNumber(){
		return this.lockerNumber;
	}
	public Size getLockerSize(){
		return this.size;
	}
}

class Bag{
	private Size size;
	private String label;
	public Bag(String label, Size size){
		this.size = size;
		this.label = label;
	}
	public Size getSize(){
		return size;
	}
	
	public String toString(){
		return "[label=" + this.label + "]"; 
	}
}

class LockerManager{
	
	private Map<Size, Locker<Bag>[]> lockers = new HashMap<Size, Locker<Bag>[]>();
	private List<Integer>[]  emptyLockers = new ArrayList[3];
	
	public LockerManager(int[] numOfLockers){
		for(int i=0; i< numOfLockers.length; i++){
			Size size = Size.values()[i];
			
			Locker<Bag>[] lockerArray = new Locker[numOfLockers[i]];
			for(int j=0; j< lockerArray.length; j++){
				lockerArray[j] = new Locker<Bag>(j, size);
			}
			lockers.put(size, lockerArray);
			
			List<Integer> idList = new ArrayList<Integer>(); 
			for(int j=0; j< numOfLockers[i]; j++){
				idList.add(j);
			}
			emptyLockers[i] = idList;
		}
		
		
	}
	
	public Ticket findLockerForBag(Bag b){
		Size sb = b.getSize();
		int sbIndex = sb.ordinal();
		
		for(int i=sbIndex; i<emptyLockers.length; i++){
			Size size = Size.values()[i];
			List<Integer> emptyLockerList = emptyLockers[i];
			if(emptyLockerList.size() ==0 ) continue;
			else{
				int lockerNumber = emptyLockerList.get(0);
				emptyLockerList.remove(0);
				return lockers.get(size)[lockerNumber].store(b);
			}
		}
		return null;
		
	}
	
	public Bag getBagByTicket(Ticket t){
		int lockerNumber = t.getLockerNumber();
		Size size = t.getLockerSize();
		
		Locker<Bag> locker = lockers.get(size)[lockerNumber];
		Bag b = locker.retrieve(t);
		emptyLockers[size.ordinal()].add(lockerNumber);
		return b;
	}
	
	
	
}



