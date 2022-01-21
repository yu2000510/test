package com.bjpowernode.utils;

import java.util.Random;

public class SaltUtils {

    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            char c = chars[new Random().nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
