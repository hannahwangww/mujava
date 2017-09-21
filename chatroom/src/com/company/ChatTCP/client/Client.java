package com.company.ChatTCP.client;


import javax.sound.sampled.Port;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static com.company.ChatTCP.util.Parameter.*;


public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println(ENTER_NICKNAME);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String name=br.readLine();
        if (name.equals("")){
            return;

        }
        //创建一个客户端
        Socket client=new Socket(IPADDRESS, PORT);
//        Thread t1=new Thread(new Send(client));
//
//        t1.getId();
//        t1.start();

        new Thread(new Send(client,name)).start();

        new Thread(new Receve(client)).start();

    }
}