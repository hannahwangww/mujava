package com.company.ChatTCP.client;

import com.company.ChatTCP.util.CloseUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static com.company.ChatTCP.util.Parameter.*;
public class Receve implements Runnable {
    //输入流
    private DataInputStream dis;
    //线程标识
    private boolean isRunning=true;

    public Receve(){
    }
    public Receve(Socket client){
        try {
            dis=new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            isRunning=false;
            CloseUtil.CloseAll(dis);
        }
    }
    public String recieve(){
        String msg="";
        try {
            msg=dis.readUTF();
        } catch (IOException e) {
            isRunning=false;
            CloseUtil.CloseAll(dis);
        }
        return msg;
    }
    @Override
    public void run() {
        while (isRunning){
            System.out.println(recieve());
        }

    }
}