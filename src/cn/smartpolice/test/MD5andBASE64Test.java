package cn.smartpolice.test;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

import javassist.compiler.ast.NewExpr;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class MD5andBASE64Test {
	public static void main(String[] args) {
		/*String string = "hello MD5";
		byte[] md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			md5 = md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("加密前:"+string+" \n加密后:"+md5+"\n");
		
		String string2 = "hello BASE64";
		String base64 = new BASE64Encoder().encode(string2.getBytes());
		System.out.println("加密前:"+string2+" \n加密后:"+base64+"\n");
		
		BASE64Decoder base64decode = new BASE64Decoder();
		try {
			String str = new String(base64decode.decodeBuffer(base64));
			System.out.println("解密前:"+base64+" \n解密后:"+str+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			Random rando = new Random();
			int random = rando.nextInt(99999) % 90000 + 10000; // 产生一个随机数
			System.out.print("随机数:"+random);
			byte [] str = String.valueOf(random).getBytes();
			Scanner scan = new Scanner(System.in); 
			System.out.println("请输入一个英文字符串或解密字符串:"); 
			String password = scan.nextLine();   //获取用户输入 
			byte[] array = password.getBytes(); //获取字符数组 a
			for(int i=0;i<array.length;i++) //遍历字符数组 
			{ 
			    str[i]=(byte)(array[i]^str[i]); //对每个数组元素进行异或运算 
			    System.out.println("加密的每个字符:"+array[i]); 
			}  
			System.out.println("加密或解密结果如下:"); 
			System.out.println(new String(array));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  } 
}
