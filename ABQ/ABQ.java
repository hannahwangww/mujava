import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by weicong on 17-7-28.
 */
public class ABQ implements Runnable{
    private  final ArrayBlockingQueue queue;
    public ABQ(ArrayBlockingQueue arrayBlockingQueue){
        this.queue=arrayBlockingQueue;

    }


    @Override
    public void run() {
        while (true){
            produs();
        }

    }
    public void produs(){
        Bread bread=new Bread("123");
        try {
            queue.put(bread);
            System.out.println(bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
