package yizhixinghash;

import javafx.beans.binding.ObjectExpression;

import java.util.HashMap;

/**
 * Created by weicong on 17-7-26.
 */
public class test {








    public static void main(String[] args) {
//        int r = 1;
//        long k = 0x02;
//        k = k >> r;
//        System.out.println(k);
//        int number = 10;
//                 //原始数二进制
//        System.out.println(number);
//                 number = number << 1;
//                //左移一位
//        System.out.println(number);
//        long aLong=153613513;
//        System.out.println(aLong);
////        string a=new string("123");
        System.out.println("192.168.0.0:111的哈希值：" + "19216800:1111".hashCode());
        System.out.println("192.168.0.0:111的哈希值：" + "192.168.0.0:1111".hashCode());

        System.out.println("192.168.0.1:111的哈希值：" + "19216801:1111".hashCode());
        System.out.println("192.168.0.2:111的哈希值：" + "192.168.1.0:1111".hashCode());
        System.out.println("192.168.0.3:111的哈希值：" + "192.168.0.3:1111".hashCode());
        System.out.println("192.168.0.4:111的哈希值：" + "192.168.0.4:1111".hashCode());
////        System.out.println(a.hashCode1());
//        System.out.println();
    }
}
