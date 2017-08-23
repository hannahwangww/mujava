package com.company.suanfa;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by weicong on 17-8-5.
 */
public  class  PKsuanfa {
    private bubing ab;
    private bubing bb;
    String jieguo="";
    private int aliveTimeA;
    private int aliveTimeB;
    private int resist;
    public PKsuanfa(bubing a,bubing b){
        this.bb=b;
        this.ab=a;
        init();
    }
    public void init(){
        aliveTimeA=ab.getnumber()*6;
        aliveTimeB=bb.getnumber()*6;
    }

    public static void main(String[] args) {

//        System.out.println(4/5);
        bubing a = new bubing();
        bubing b = new bubing();
        a.setnumber(10);
        b.setnumber(20);
        PKsuanfa pKsuanfa=new PKsuanfa(a,b);

        System.out.println(pKsuanfa.jisuan());
    }
    public String jisuan(){
        if (aliveTimeA>aliveTimeB){
            resist=(aliveTimeA-aliveTimeB)/6;
            jieguo="A|"+resist;
            return jieguo;
        }else if (aliveTimeA<aliveTimeB){
            resist=(aliveTimeB-aliveTimeA)/6;
            jieguo="B|"+resist;
            return jieguo;
        }else {
            jieguo="P";
            return jieguo;
        }


    }
    public String zengyuan(bubing ab)

    {
        return jieguo;
    }
}
