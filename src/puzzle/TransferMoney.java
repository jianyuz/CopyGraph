package puzzle;

public class TransferMoney {
	private static final Object tieLock = new Object();
	
	class Account{
		private double balance;
		
		public double getBalance(){
			return balance;
		}
		
		public void debit(double amount){
			balance -= amount;
		}
		
		public void credit(double amount){
			balance += amount;
		}
	}
	
	class InsufficientFundException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	
	public void transferMoney(final Account from, final Account to, final double amount) throws InsufficientFundException{
	
		class Helper{
			public void transfer() throws InsufficientFundException{
				if(from.balance < amount){
					throw new InsufficientFundException();
				}else{
					from.debit(amount);
					to.credit(amount);
				}
			}
		}
		
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);
		
		if(fromHash < toHash){
			synchronized(from){
				synchronized(to){
					new Helper().transfer();
				}
			}
		}else if(fromHash > toHash){
			synchronized(to){
				synchronized(from){
					new Helper().transfer();
				}
			}
		}else{
			synchronized(tieLock){
				synchronized(from){
					synchronized(to){
						new Helper().transfer();
					}
				}
			}
		}
		
	}
}
