package org.file.ii;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by andilyliao on 16-4-23.
 */
public class FileOutputStreamTest2 {
    public static void main(String[] args) {
        OutputStream fos=null;
        try {
            fos=new FileOutputStream("/opt/b.txt/xxx");
            String str="测试程序sjklsjkls;kjsg;kjsjlgsd";
            fos.write(str.getBytes(),0,str.getBytes().length);
            fos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                System.out.println("aaaaaa");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("bbbbbb");
    }
}
