package com.company.ChatTCP.Server;

import com.company.ChatTCP.util.CloseUtil;
import com.company.ChatTCP.util.Roomid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.*;
import static com.company.ChatTCP.util.Parameter.*;


public class Server {
    private static Logger logger = Logger.getLogger(Server.class);
    private  HashMap<String,MyServer> namesocket=new HashMap();//存放name-Myserver 的map
    private  HashMap<String,String> nameroom=new HashMap();//存放name-room 的map
    private  HashMap<String,String> roomname=new HashMap();//存放room-name 的map
    private List<MyServer> myroom=new ArrayList<MyServer>();//存放Myserver，方便快速发送。
     public static void main(String[] args) throws IOException {
        new Server().start();
    }
//可以随时换nio

    public void start() throws IOException {
        //创建一个服务器
        ServerSocket server=new ServerSocket(PORT);
        while (true) {
            Socket client = server.accept();
            MyServer ms=new MyServer(client);
            namesocket.put(ms.name,ms);//把我和我的链接放入namesocket中
            myroom.add(ms);//直接把我添加到myroom里面
            System.out.println(ms.name+"   init：nameroom："+nameroom.get(ms.name)+"  myroom:"+myroom.size()+"     0000roomname:"+roomname.get("0000") );//日志
            new Thread(ms).start();//开启线程
        }
    }


    private class MyServer implements Runnable{
        private DataInputStream dis;//可以换nio
        private DataOutputStream dos;
        private boolean isRunning=true;//控制线程死循环的变量
        private  String name;//线程名字



        public  MyServer(Socket client){
            logger.info("client address:"+client.getLocalSocketAddress());//打印端口
            try {
                dis=new DataInputStream(client.getInputStream());//打开读通道
                dos=new DataOutputStream(client.getOutputStream());//打开写通道
                this.name=dis.readUTF();//第一次过来的是我的名字
                nameroom.put(this.name,"0000");//初始化进来就是进0000房间，大众房间
                if (roomname.get("0000")!=null){//更新大众房间的信息。
                    roomname.put("0000",roomname.get("0000")+"|"+name);

                }else{
                    roomname.put("0000",this.name);
                }

                this.send(WELCOME_TO_CHAT);//发送欢迎信息
                sendOthers(this.name+COME_TO_CHAT);//发送给其他人谁谁谁进入房间
            } catch (IOException e) {
                System.out.println(this.name+"   MyServer   exception:   "+e.getMessage());
                isRunning=false;//控制死循环的变量
                CloseUtil.CloseAll(dis,dos);//关闭管道
            }
        }


        public String del(String string){//删除字符串中的一个name
            logger.info("This is "+this.name+"   del   "+string);
            String newString="";
            String[] people=string.split(SPLIT);
            for (String aaa:people) {
                if (aaa.equals(this.name)){
                    continue;
                }else {
                    if (newString.equals(""))newString=aaa;
                    else newString=newString+"|"+aaa;
                }
            }
            string=newString;
            return string;
        }
        public void updatemyroom(){//把我房间里的人直接放到myroom里面
            logger.info("This is "+this.name+"   updatemyroom   ");//日志
            String[] peoples=roomname.get(nameroom.get(this.name)).split(SPLIT);//我房间的所有人
            myroom.clear();//清空我的room列表
            for (String people:peoples) {//把我的房间放到myroom里面，下面直接从myroom里面拿出来发消息
                myroom.add(namesocket.get(people));
            }
        }
        public String addroom(String msg){//加入房间

            logger.info("This is "+this.name+"   addroom   "+msg);//日志
            String returnmsg="";
            String[] msgs=msg.split(SPLIT);//把msg分开

            if (msgs.length==2){//判断长度。规定传入格式
                msgs[1]= Roomid.InitRoomId(msgs[1]);
                System.out.println(msgs[1]+"==================");
                if (msgs[0].equals("+")){
                    if (roomname.get(msgs[1])==null||roomname.get(msgs[1]).equals("")){//判断是null或者是空。直接覆盖掉。否则进行后面加入。
                        String people=roomname.get(nameroom.get(this.name));//原始房间里所有人
                        roomname.put(nameroom.get(this.name),this.del(people));//把我从原始房间里面删除再放回去
                        roomname.put(msgs[1],this.name);//把我放到新房间里
                        nameroom.put(this.name,msgs[1]);//更新我的房间信息
                        updatemyroom();//把我房间所有人加入到myroom里面方便下次发送消息用
                        returnmsg= this.name+"come to: "+msgs[1]+"room";//返回消息
                    }else{
                        String people=roomname.get(nameroom.get(this.name));//原始房间里所有人
                        roomname.put(nameroom.get(this.name),this.del(people));//把我从原始房间里面删除再放回去
                        String roompeoples=roomname.get(msgs[1]);//拿出新房间里面所有人
                        roompeoples=roompeoples+"|"+this.name;//在后面加上我
                        roomname.put(msgs[1],roompeoples);//更新新房间里面人的信息
                        nameroom.put(this.name,msgs[1]);//更新我的房间信息
                        updatemyroom();//把我房间所有人加入到myroom里面方便下次发送消息用
                        returnmsg= this.name+"come to: "+msgs[1]+"room";//返回消息
                    }
                }
            }else {
                returnmsg= msg;
            }
            return returnmsg;
        }
        public String dell(String msg){
            //退出房间，做的就是加入到0000房间。
            String returnmsg="";
            if (msg.equals("-")){
               returnmsg= this.addroom("+|0000");
            }else returnmsg= msg;
            return returnmsg;

        }

        public String receive(){//接受消息
            logger.info("This is "+this.name+"   receive   ");
            String msg="";
            try {
                msg=dis.readUTF();//从键盘输入
                msg=addroom(msg);//加入房间
                msg=dell(msg);//退出房间


            } catch (IOException e) {
                logger.error("This is "+this.name+"   receive   exception:   "+e.getMessage());
                isRunning=false;//抛异常直接吧线程关了。
                CloseUtil.CloseAll(dis);//使用工具类关闭所有管道

            }
            return msg;//返回
        }


        public void send(String msg){//发送消息
            logger.info("This is "+this.name+"   send   "+msg);//日志

            if (null==msg || msg.equals("")){//空消息不选择发送
                return;

            }
            try {//
                dos.writeUTF(msg);//读
                dos.flush();//flush
            } catch (IOException e) {
                logger.error("This is "+this.name+"   send   exception:   "+e.getMessage());
                isRunning=false;//抛异常直接吧线程关了。
                CloseUtil.CloseAll(dos);//使用工具类关闭所有管道

            }
        }
        public void check(HashMap<String,String> roomname,HashMap<String,String> nameroom,List<MyServer> myroom){//主要根据nameroom做校验

            String room=  nameroom.get(this.name);

            boolean istrue1=false;//判断roomname里面有没有我自己，有的话就把他改为true
//            boolean istrue2=false;//判断myroom里面是否正确
//            boolean istrue3=false;//判断myroom里面是否正确
            if (roomname.get(room)==null);
            else {
                String[] peoples=roomname.get(room).split(SPLIT);
                for (String people:peoples) if (people.equals(this.name))istrue1=true;

            }
            if (istrue1==false){//如果没通过校验。那么就加入进去。
                if (roomname.get(room)!=null&&roomname.get(room).split(SPLIT).length>=1)//roomname里面必须不能为空且必须大于1个人
                roomname.put(room,roomname.get(room)+"|"+this.name);
                else if (roomname.get(room)==null)roomname.put(room,this.name);
            }
//            String[] peoname=roomname.get(room).split(SPLIT);
//            for (String name1:peoname) {
//                for (MyServer server:myroom) {
//                    if (server.name.equals(name1)){
//
//                        continue;
//                    }
//                }
//            }

        }
            //+|0000
        public void sendOthers(String msg){//发送给别人
//          加一个校验
            check(roomname,nameroom,myroom);
            logger.info("This is "+this.name+"   sendOthers   "+msg+"   update:"+this.name+"  my room name:"+nameroom.get(this.name)+"    my room have:"
                    + roomname.get(nameroom.get(this.name))+"     myroom big:"+myroom.size()+"   0000 room have:"+roomname.get("0000")
                    +"    1111 room have："+roomname.get("1111"));//日志
           if (msg.equals("")){//判断为空直接返回
                return;
            }else if (msg.startsWith("@") && msg.indexOf(":")>-1){//判断是否是@人
                String name=msg.substring(1,msg.indexOf(":"));//把name截取出来
                String content=msg.substring(msg.indexOf(":")+1);//把消息截取出来
                if(namesocket.get(name)!=null){//判断部位空
                    namesocket.get(name).send(this.name+SAY_YOU+content);
                }else {
                    this.send(NO_THIS_PEO);
                }
            }
            else {//发送给房间内所有人
                String myroompeoples=roomname.get(nameroom.get(this.name));//把房间里所有人拿出来
                String[] myroompeople=myroompeoples.split(SPLIT);
                for (int i=0;i<myroompeople.length;i++){//判断是否有我自己，有我自己就不发，其他的就把消息发出去
                    if (myroompeople[i].equals(this.name)){
                        continue;
                    }else {
                        if (namesocket.containsKey(myroompeople[i]))
                            namesocket.get(myroompeople[i]).send(this.name+SAY+msg);
                        else continue;

                    }
                }
            }
        }
        @Override
        public void run() {//主线程
            while (isRunning){//用isRunning来控制死循环的开始结束
//            send(receive());


                sendOthers(receive());
//                System.out.println();//为了我打印日志时候的方便观察

            }
        }
    }
}