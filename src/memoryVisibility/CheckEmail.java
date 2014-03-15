package memoryVisibility;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

//check email from multi host in parallet and await for finish.
public class CheckEmail {
	public boolean checkEmail(Set<String> hosts) throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		final AtomicBoolean hasNewEmail = new AtomicBoolean(false);
		try{
		for(final String host: hosts){
			exec.execute(new Runnable(){

				@Override
				public void run() {
					if(checkMail(host)){
						hasNewEmail.set(true);
					}
				}

				private boolean checkMail(String host) {
					// TODO Auto-generated method stub
					return false;
				}
				
			});
		}}finally{
			exec.shutdown();
			exec.awaitTermination(100, TimeUnit.MINUTES);
		}
		
		return hasNewEmail.get();
	}
}
