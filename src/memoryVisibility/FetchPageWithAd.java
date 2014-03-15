package memoryVisibility;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * set timeout for fetch.
 * if timeout cancel it.
 * 
 * @author zhouzhou
 *
 */
public class FetchPageWithAd {
	final Ads DEFAULT_AD = new Ads();
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	Page renderPageWithAd() throws InterruptedException{
		long endNanos = System.nanoTime() + 2000000;
		Future<Ads> f = exec.submit(new FetchAdTask());
		Page page = renderPageBody();
		Ads ad;
		long timeLeft = endNanos - System.nanoTime(); //maybe negative.
		try {
			ad = f.get(timeLeft, TimeUnit.NANOSECONDS);
		}  catch (ExecutionException e) {
			// TODO Auto-generated catch block
			ad = DEFAULT_AD;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			ad = DEFAULT_AD;
			f.cancel(true);
		}
		
		page.setAd(ad);
		return page;
	}
	
	public Page renderPageBody(){
		return new Page();
	}	

}

class FetchAdTask implements Callable<Ads>{

	@Override
	public Ads call() throws Exception {
		return new Ads();
	}
	
}

class Ads{
	public Ads(){
		
	}
}
class Page{
	public void setAd(Ads ad){
		
	}
}
