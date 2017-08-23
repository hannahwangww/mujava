package yizhixinghash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weicong on 17-7-26.
 */
public class  string {
//    int  hash;
//    private static char[] value1;
//    public string(String a){
//
//
//
//    }
//    public int hashCode1() {
//
//        int h = hash;
//        if (h == 0 && value1.length > 0) {
//            char val[] = value1;
//
//            for (int i = 0; i < value1.length; i++) {
//                h = 31 * h + val[i];
//            }
//            hash = h;
//        }
//        return h;
//    }

    public static void main(String[] args) {
        List a=new ArrayList(11231);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        lizi a1=new lizi(a);
        int q1=0;
        int q2=0;
        int q3=0;
        int q4=0;
        int q5=0;
        for (int i=0;i<=100000;i++) {
            switch (a1.gethuanInfo("192.168.56.101:" + i).toString()){
                case "1":
                    q1++;
                    break;
                case "2":
                    q2++;
                    break;
                case "3":
                    q3++;
                    break;
                case "4":
                    q4++;
                    break;
                case "5":
                    q5++;
                    break;


            }
        }
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
        System.out.println(q4);
        System.out.println(q5);

    }
}
