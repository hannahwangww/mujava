package testjava;

import redis.clients.jedis.Jedis;

import java.util.Scanner;

public class main1 {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        while(true){
            Scanner scanner=new Scanner(System.in);
            String aaa=scanner.nextLine();
            jedis.publish("aaaaaaaa",aaa);
        }

    }
}
