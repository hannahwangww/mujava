package com.company.Businesslogic;

import java.util.Random;

/**
 * Created by weicong on 17-8-15.
 */
public class VerificationCode {
    public String getVerificationCode(){
        int i= new Random().nextInt(9999);
        return String.valueOf(i);
    }

    public static void main(String[] args) {
        VerificationCode verificationCode=new VerificationCode();
//        verificationCode.getVerificationCode();
        System.out.println(verificationCode.getVerificationCode());
        System.out.println(verificationCode.getVerificationCode());
    }

}
