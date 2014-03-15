import java.util.ArrayList;
import java.util.List;


public class FillListTask implements Runnable {
    private final int size;
    private List<String> strings;
    public FillListTask(int size) {
        this.size = size;
    }
    public synchronized boolean isFinished() {
        return null != strings;
    }
    public synchronized List<String> getList() {
        return strings;
    }
    @Override
    public void run() {
        List<String> strs = new ArrayList<String>(size);
        try {
            for (int i = 0; i < size; i++ ) {
                Thread.sleep(2000);
                System.out.println("Ading element " + i);
                strs.add("element " + String.valueOf(i));
            }
            synchronized (this) {
                strings = strs;
                this.notify();
            }
        }
        catch (InterruptedException e) {
             // catch interrupted exception outside loop,
             // since interrupted exception is a sign that
             // the thread should quit.
        }
    }
    /**
     * Waits for the fill list task to complete
     */
    public static void main(String[] args)
        throws InterruptedException
    {
        FillListTask task = new FillListTask(7);
        new Thread(task).start();
     // The call to wait() releases the lock
        // on task and suspends the thread until
        // it receives a notification
        synchronized (task) {
            while (!task.isFinished()) {
                task.wait();
            }
        }
        System.out.println("Array full: " + task.getList());
    }
}
