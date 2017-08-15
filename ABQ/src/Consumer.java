import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by weicong on 17-7-28.
 */
public class Consumer implements Runnable {
    private final ArrayBlockingQueue<Bread> queue;

    public Consumer(ArrayBlockingQueue<Bread> queue){
        this.queue = queue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while(true){
            consume();
        }
    }

    public void consume(){

        try {
            Bread bread = queue.take();
            System.out.println("consumer:"+bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
