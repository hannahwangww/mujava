import com.oracle.jrockit.jfr.Producer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by weicong on 17-7-28.
 */
public class Client {
    public static void main(String[] args) {
        int capacity = 1;
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<Bread>(capacity);

        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();
        new Thread(new ABQ(queue)).start();


        new Thread(new Consumer(queue)).start();
//        new Thread(new Consumer(queue)).start();
//        new Thread(new Consumer(queue)).start();
    }
}
