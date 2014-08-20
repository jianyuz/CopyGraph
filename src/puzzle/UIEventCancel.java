package puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;

public class UIEventCancel {

	private static Future<?> runningTask = null;
	
	public static void main(String[] args){
		JButton startButton = new JButton("start");
		JButton cancelButton = new JButton("cancel");
		final ExecutorService backgroundExec = Executors.newFixedThreadPool(1);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(runningTask != null){
					runningTask = backgroundExec.submit(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(moreWork()){
								if(Thread.currentThread().isInterrupted()){
									cleanup();
									break;
								}
								dowork();
							}
						}
						
						private boolean moreWork(){
							return true;
						}
						
						private void dowork(){
							
						}
						
						private void cleanup(){
							
						}
						
					});
				}
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(runningTask != null){
					runningTask.cancel(true);
				}
			}});
	}
}
