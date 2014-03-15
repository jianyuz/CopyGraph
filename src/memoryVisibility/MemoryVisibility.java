package memoryVisibility;

public class MemoryVisibility {
	private volatile static boolean ready = false;
	private static int number = 0;
	
	private final static class Reader extends Thread{
		public void run(){
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	public static void main(String[] args){
		new Reader().start();
		//number = 42;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		number = 42;
		ready = true;
		
		
	}

}
