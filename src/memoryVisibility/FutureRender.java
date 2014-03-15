package memoryVisibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureRender {
	private final ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	public void renderPage(CharSequence source){
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		
		Callable<List<ImageData>> future = new Callable<List<ImageData>>(){

			@Override
			public List<ImageData> call() throws Exception {
				List<ImageData> data = new ArrayList<ImageData>();
				for(ImageInfo id : imageInfos){
					data.add(id.downloadImage());
				}
				return data;
			}
			
		};
		
		Future<List<ImageData>> fTask = executorService.submit(future);
		renderText(source);
		
		try {
			List<ImageData> imageData = fTask.get();
			for(ImageData iData : imageData){
				iData.renderImage();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			fTask.cancel(true);
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
class ImageData{
	public void renderImage(){
		
	}
}

class ImageInfo{
	public ImageData downloadImage(){
		return null;
	}
}
