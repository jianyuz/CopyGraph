package memoryVisibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionService1 {
	private final ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	public void renderPage(CharSequence source){
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		CompletionService<ImageData> comletionService = new ExecutorCompletionService<ImageData>(executorService);

		List<ImageData> data = new ArrayList<ImageData>();
		for (final ImageInfo id : imageInfos) {
			comletionService.submit(new Callable<ImageData>() {

				@Override
				public ImageData call() throws Exception {
					// TODO Auto-generated method stub
					return id.downloadImage();
				}

			});
			data.add(id.downloadImage());
		}
			
			
		
		
		renderText(source);
		
		try {
			for(int t=0, n=imageInfos.size(); t<n; t++){
				Future<ImageData> f = comletionService.take();
				ImageData imageData = f.get();
				imageData.renderImage();
			}
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void renderText(CharSequence source){
		
	}
	private List<ImageInfo> scanForImageInfo(CharSequence source){
		return null;
	}

}


