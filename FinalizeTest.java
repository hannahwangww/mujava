package org.gc;

public class FinalizeTest {
    private static FinalizeTest ft = null;

    public void nfo() {
        System.out.println("xxxxxxxx");
    }

    public static void main(String[] args) throws Exception {
        FinalizeTest t=new FinalizeTest();
        t=null;
        System.gc();

//        Runtime.getRuntime().gc();
		Runtime.getRuntime().runFinalization();//尽量执行finalize方法

//        System.runFinalization();
//        System.in.read();
        ft.nfo();

    }

    public void finalize() {
        System.out.println("ok");
        ft = this;
    }
}

