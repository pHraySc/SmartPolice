package cn.smartpolice.test;


import cn.smartpolice.tools.JsonAnalysis;
import net.sf.json.JSONArray;

public class Jsontest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Json="{DATA:[{'a':'111','b':'222','c':'333'},{'a':'1000','b':'2000','c':'3000'},{'a':'999','b':'300','c':'700'}]}";
		JsonAnalysis js = new JsonAnalysis();
		String json = js.getValue(Json, "DATA");
		JSONArray jsonArr = JSONArray.fromObject(json);
		String a[] = new String[jsonArr.size()];
        String b[] = new String[jsonArr.size()];
        String c[] = new String[jsonArr.size()];
        String user[] = new String[2];
        String g = "hah";
        user[0] = g;
        String s = user[0];
        System.out.println(s);
        String[] str = {"abc", "bcd", "def"};
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < str.length; i++){
         sb. append(str[i]);
        }
        String fs = str[0];
        System.out.println(fs);
        for (int i = 0; i < jsonArr.size(); i++) {
            a[i] = jsonArr.getJSONObject(i).getString("a");
            b[i] = jsonArr.getJSONObject(i).getString("b");
            c[i] = jsonArr.getJSONObject(i).getString("c");
       }
         
        for (int i = 0; i < c.length; i++) {
           System.out.print(a[i]+" ");
           System.out.print(b[i]+" ");
           System.out.print(c[i]);
           
           
	}

	}}
