package com.company.ChatTCP.util;

public  class Roomid {
    public static String InitRoomId(String ROOMID ){
        int roomid1=Integer.parseInt(ROOMID);
        String roomid = "";
        if (roomid1 < 10) {
            roomid = "000" + roomid1;
        } else if (roomid1 < 100 && roomid1 >= 10) {
            roomid = "00" + roomid1;
        } else {
            roomid = "0" +roomid1;
        }
     return roomid;
    }

    public static void main(String[] args) {
        System.out.println( Roomid.InitRoomId("-10"));
        System.out.println( Roomid.InitRoomId("012"));
        System.out.println( Roomid.InitRoomId("0012"));
        System.out.println( Roomid.InitRoomId("012"));
    }
}
