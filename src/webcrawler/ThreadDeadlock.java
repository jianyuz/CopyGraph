package webcrawler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadlock {
	ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public class RenderPageTask implements Callable<String>{

		@Override
		public String call() throws Exception {
			Future<String> header, footer;
			header = exec.submit(new LoadFileTask("header.html"));
			footer = exec.submit(new LoadFileTask("footer.html"));
			String page = renderPageBody();
			return header.get() + page + footer.get();
		}
		
		public String renderPageBody(){
			return null;
		}
		
	}
	
	class LoadFileTask implements Callable<String>{
		public LoadFileTask(String fileName){
			
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
