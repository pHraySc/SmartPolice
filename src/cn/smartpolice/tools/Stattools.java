package cn.smartpolice.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("statTool")
@Transactional(readOnly = false)
public class Stattools {

	/*
	 * 获取特定格式的当前标准时间，用于图表数据处理
	 * 返回 int[3][3]
	 * 
	 * */
	public int[][] getNowtimeForChart(){
		//当前时间标准	
		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] nowtime1=df.format(new Date()).split(" ");//分割时间
		String[] nowtime0=nowtime1[0].split("-");//获得年月日
		
		//得到本周一
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		 day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		String[] monday=df1.format(c.getTime()).split("-");//本周一，日期格式yyyy-MM-dd
		//获得本周日 
		Calendar c1 = Calendar.getInstance();
		int day_of_week1 = c1.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week1 == 0){
			day_of_week1 = 7;
		}
		c1.add(Calendar.DATE, -day_of_week1 + 7);
		String[] sunday=df1.format(c1.getTime()).split("-");//本周日，日期格式yyyy-MM-dd
		
		int[][] nowtime=new int[3][3];//日期，第一行本日，第二行本周一，第三行周天
		for(int ii=0;ii<3;ii++){
			nowtime[0][ii]=Integer.parseInt(nowtime0[ii]);
			nowtime[1][ii]=Integer.parseInt(monday[ii]);
			nowtime[2][ii]=Integer.parseInt(sunday[ii]);
		}	
		return nowtime;

	}
	
	/*
	 * 获取特定格式的昨日、上周标准时间，用于图表数据处理
	 * 
	 * */
	public int[][] getLasttimeForChart(){
		
		//得到昨天
		SimpleDateFormat df= new SimpleDateFormat( "yyyy-MM-dd");
		Calendar  cal  =  Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday =df.format(cal.getTime());
		
		
		String[] yesterdaytime=yesterday.split("-");//获得年月日
		
		//得到今天是星期几
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(new Date());
		int todayOfWeek=cal0.get(Calendar.DAY_OF_WEEK)-1;//今天是星期几（星期天是0，星期一是1） 
		
		
		//获得上周一 
         Calendar cal1 = Calendar.getInstance();
         if(todayOfWeek==0){
        	 cal1.add(Calendar.DATE, -14);//这里是在今日的基础上倒退14天
         }else{
        	 cal1.add(Calendar.DATE, -7);//这里是在今日的基础上倒退7天
         }
         cal1.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
         String[] lastMonday= df.format(cal1.getTime()).split("-");
         
         
		//获得上周天   
         Calendar cal2 = Calendar.getInstance();
         if(todayOfWeek==0){
        	 cal2.add(Calendar.DATE, -7);
         }else{
        	 cal2.add(Calendar.DATE, 0);
         }
        
         cal2.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
         String[] lastSunday=df.format(cal2.getTime()).split("-");    
         
         
         int[][] lasttime=new int[3][3];//日期，第一行日，第二行周一，第三周天
 		 for(int i=0;i<3;i++){
 			lasttime[0][i]=Integer.parseInt(yesterdaytime[i]);
 			lasttime[1][i]=Integer.parseInt(lastMonday[i]);
 			lasttime[2][i]=Integer.parseInt(lastSunday[i]);
 		 }	
 		return lasttime;
			
	}
	
	
	
	
	/*
	 * 处理注册提交时间，获得标准格式数据
	 * 返回 int[4]
	 * 
	 * */
	public int[] getSubtimeForChart(Date st){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] st1=df.format(st).split(" ");
		String[] st2=st1[0].split("-");
		String[] st3=st1[1].split(":");
		int[] subtime=new int[5];//年、月、日、周几、小时
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		String t=dateFm.format(st);//星期几
		if(t.equals("星期一")){
			subtime[3]=1;
		}else if(t.equals("星期二")){
			subtime[3]=2;
		}else if(t.equals("星期三")){
			subtime[3]=3;
		}else if(t.equals("星期四")){
			subtime[3]=4;
		}else if(t.equals("星期五")){
			subtime[3]=5;
		}else if(t.equals("星期六")){
			subtime[3]=6;
		}else if(t.equals("星期日")){
			subtime[3]=7;
		}			
		subtime[0]=Integer.parseInt(st2[0]);//年
		subtime[1]=Integer.parseInt(st2[1]);//月
		subtime[2]=Integer.parseInt(st2[2]);//日
		subtime[4]=Integer.parseInt(st3[0]);//小时
		
		return subtime;	
	}
	
	/*
	 * 待审核数量统计
	 * 
	 * */
	public int getCheckingNum(int n,int state){
		
		if(state==0){
			n++;
		}
		return n;		
	}
	
	/*
	 * 统计本日数据
	 * 参数当前统计数据、现在时间、提交时间
	 * 返回处理过后的统计数据int[] data
	 * 
	 * */
	public int[] getDayChartData(int[] data,int[][] nowtime,int[] subtime){
		
		if (subtime[0] == nowtime[0][0] && subtime[1] == nowtime[0][1]
				&& subtime[2] == nowtime[0][2]) {// 是本日
			// 分时间段
			// data[0][0]=0;
			if (subtime[4] <= 4) {
				data[1]++;
			} else if (subtime[4] <= 8) {
				data[2]++;
			} else if (subtime[4] <= 12) {
				data[3]++;
			} else if (subtime[4] <= 16) {
				data[4]++;
			} else if (subtime[4] <= 20) {
				data[5]++;
			} else if (subtime[4] <= 24) {
				data[6]++;
			} else {
				// 异常数据
				System.out.println("异常数据：本日");
			}
		} else {
			// System.out.println("非本日");
		}
		return data;
		
	}
	
	/*
	 * 统计本周的数据
	 * 
	 * 
	 * */
	
	public int[] getWeekChartData(int[] data,int[][] nowtime,int[] subtime){
		
		if (subtime[0] >= nowtime[1][0] && subtime[1] >= nowtime[1][1]
				&& subtime[2] >= nowtime[1][2]) {
			if (subtime[0] <= nowtime[2][0]
					&& subtime[1] <= nowtime[2][1]
					&& subtime[2] <= nowtime[2][2]) {
				switch(subtime[3]){
					case 1:data[0]++;break;
					case 2:data[1]++;break;
					case 3:data[2]++;break;
					case 4:data[3]++;break;
					case 5:data[4]++;break;
					case 6:data[5]++;break;
					case 7:data[6]++;break;
				}		
			} else {
				// 非本周
			}
		} else {
			// 非本周
		}
		return data;	
	}
	
	/*
	 * 统计本月数据
	 * 
	 * */
	
	public int[] getMonthChartData(int[] data,int[][] nowtime,int[] subtime){
		
		if (subtime[0] == nowtime[0][0] && subtime[1] == nowtime[0][1]) {
			if (subtime[2] <= 5) {
				data[0]++;
			} else if (subtime[2] <= 10) {
				data[1]++;
			} else if (subtime[2] <= 15) {
				data[2]++;
			} else if (subtime[2] <= 20) {
				data[3]++;
			} else if (subtime[2] <= 25) {
				data[4]++;
			} else if (subtime[2] <= 30) {
				data[5]++;
			} else if (subtime[2] <= 31) {
				data[6]++;
			} else {
				// 异常数据
			}
		} else {
			// no this month
		}
		return data;
	}
	
	/*
	 * 总注册数
	 * 
	 * */
	public int getTotalNum(List list){
	
		return list.size();
		
		
	}
	
	/*
	 * 上月注册数
	 * 
	 * 
	 * */
	public int getLastmonthNum(int lastmonNum,int[][] nowtime,int[] subtime){
		
		 if (nowtime[0][1] == 1 && subtime[0] == nowtime[0][0] - 1
		 && subtime[1] == 12) {// 现在是一月，提交年份是去年，提交月份是12月
		 lastmonNum++;
		 } else if (subtime[0] == nowtime[0][0]
		 && subtime[1] == nowtime[0][1] - 1) {// 现在非一月，提交年份是今年，提交月份是上月
		 lastmonNum++;
		 } else {
		 System.out.println("不满足要求：上月");
		
		 }
		
		 return lastmonNum;
	}
	
	/*
	 * 本日相对增长量
	 * 
	 * */
	public int[] getRelativeDayGrowth(int[] lastday,int[][] nowtime,int[] subtime){
		return null;
		
		
		
	}
	
}
