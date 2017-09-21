package testjava;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8885);
        Socket socket=new Socket("127.0.0.1",8885);
        System.out.println(Integer.parseInt("012"));
    }

}