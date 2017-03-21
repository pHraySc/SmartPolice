package cn.smartpolice.test;

import java.util.Random;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class javatest {

	public static void main(String[] args) {
		
		String string = "98756";
//		byte[] cha = string.getBytes();
//		String s = Base64.encode(cha);
		System.out.print(reverse(string));
		
	}
	public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1) 
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
}
