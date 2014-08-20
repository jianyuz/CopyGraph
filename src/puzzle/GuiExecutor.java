package puzzle;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiExecutor extends AbstractExecutorService{

	private static final GuiExecutor instance = new GuiExecutor();
	private GuiExecutor(){}
	public static GuiExecutor instance(){
		return instance;
	}
	
	public void execute(Runnable r){
		if(SwingUtilities.isEventDispatchThread()){
			r.run();
		}else{
			SwingUtilities.invokeLater(r);
		}
	}
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
