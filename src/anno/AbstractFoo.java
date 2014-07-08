package anno;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractFoo {

	private int x, y;
	
	private enum State{NEW, INITIALIZING, INITIALIZED};
	private AtomicReference<State> stateHolder = new AtomicReference<State>(State.NEW);
	
	public AbstractFoo(int x, int y){
		initialize(x, y);
	}
	
	protected AbstractFoo(){
		
	}
	
	protected final void initialize(int x, int y){
		if(!stateHolder.compareAndSet(State.NEW, State.INITIALIZING)){
			throw new IllegalStateException("invalid state");
		}
		this.x = x;
		this.y = y;
		stateHolder.set(State.INITIALIZED);
	}
	
	protected int getX(){
		this.checkInit();
		return this.x;
	}
	
	protected int getY(){
		this.checkInit();
		return this.y;
	}
	
	private void checkInit(){
		if(stateHolder.get() == State.INITIALIZED){
			throw new IllegalStateException("not init");
		}
	}
}
