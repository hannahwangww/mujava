package org.gc;

import java.io.IOException;

//java -cp xxx.jar -XX:+PrintGCDetails org.gc.GcTest
public class GcTest {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new GcTest();
            Runtime.getRuntime().gc();
//            System.gc();
            Runtime.getRuntime().runFinalization();
        }
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void finalize() {
        System.out.println("full gc running");
    }
}
