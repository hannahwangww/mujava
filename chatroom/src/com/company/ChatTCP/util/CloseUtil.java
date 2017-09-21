package com.company.ChatTCP.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {//关闭所有管道
    public static void CloseAll(Closeable...io){
        for (Closeable Io:io){
            try {
                if (Io!=null){
                    Io.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}