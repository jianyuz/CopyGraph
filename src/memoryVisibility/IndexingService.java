package memoryVisibility;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IndexingService {
	private static final File POISON = new File("");
	private final IndexerThread consumer = new IndexerThread();
	private final CrawlerThread producer = new CrawlerThread();
	private final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(100);
	private final FileFilter fileFilter = new FileFilter(){

		@Override
		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			return true;
		}
		
	};
	private final File root = new File("c://");

	class CrawlerThread extends Thread { /* Listing 7.18 */
		public void run(){
			try{
				crawl(root);
			}finally{
				while(true)
				{
					try{
						queue.put(POISON);//must put in poison, don't care about interruption.
						break;
					}catch(InterruptedException e){}
				}
			}
		}

		private void crawl(File root) {
			// TODO Auto-generated method stub
			
		}
			
	}

	class IndexerThread extends Thread { /* Listing 7.19 */
		public void run(){
			while(true){
				File file;
				try {
					file = queue.take();
				
				if(file == POISON){
					break;
				}
				else{
					indexFile(file);
				}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private void indexFile(File file) {
			// TODO Auto-generated method stub
			
		}
	}

	public void start() {
		producer.start();
		consumer.start();
	}

	public void stop() {
		producer.interrupt();//interrupt producer to stop ther serivce.
	}

	public void awaitTermination() throws InterruptedException {
		consumer.join();
	}
}
