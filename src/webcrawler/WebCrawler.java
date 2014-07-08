package webcrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebCrawler {

	private volatile TrackingExecutor exec;
	private final Set<URL> urls = new HashSet<URL>();
	
	public WebCrawler(URL url){
		urls.add(url);
	}
	
	public synchronized void start(){
		exec = new TrackingExecutor(Executors.newFixedThreadPool(2));
		for(URL url : urls){
			submitCrawlTask(new CrawlTask(url));
		}
	}
	
	public synchronized void stop() throws InterruptedException{
		try{
		saveUncrawled(exec.shutdownNow());//hasn't stared.
		if(exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
			saveUncrawled(exec.getTaskCancelled()); //add started but interrupted.
			//get url from the submitted tasks.
		}}
		finally{
			exec = null;
		}
	}
	
	private void saveUncrawled(List<Runnable> uncrawled){
		for(Runnable task: uncrawled){
			URL url = ((CrawlTask)task).getPage();//
			urls.add(url);
		}
	}
	
	public void submitCrawlTask(CrawlTask task){
		exec.submit(task);
	}
	
	public List<URL> processPage(URL url){
		return new ArrayList<URL>();
	}
	
	private class CrawlTask implements Runnable{

		private URL url;
		
		public CrawlTask(URL url){
			this.url = url;
		}
		
		@Override
		public void run() {
			List<URL> urlsInPage = processPage(url);//find urls on the page.
			for(URL theUrl : urlsInPage){
				submitCrawlTask(new CrawlTask(theUrl));//crawl again.
			}
			
		}
		
		public URL getPage(){
			return url;
		}
		
	}
}
