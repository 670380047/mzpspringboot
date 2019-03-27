package util.listtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/15 14:17
 * @File : DateFormat
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class DateFormat {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date();
        Date date1 = new Date();
        String dateTime = "2019-03-22 16:10:30";
        int month =date.getMonth()+1; //（月份需要加1）
        System.out.println("date时间月份："+month+"月");
        System.out.println("d格式化时间："+d.format(date));
        System.out.println("df格式化时间："+df.format(date));
        try {
            date1 = df.parse(dateTime);
            System.out.println("字符串格式化为date类型（parse）："+date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("==========华丽的分割线===============");
        //Calendar（日期）
        Calendar calendar =Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Calendar年:"+year+"年");
        //（月份需要加1）
        int cMonth =calendar.get(Calendar.MONTH)+1;
        System.out.println("Calendar月份："+cMonth+"月");

        int day = calendar.get(Calendar.DATE);
        System.out.println("Claendar日期："+day+"日");
        String cDateStr =year+"年"+cMonth+"月"+day+"日";
        System.out.println("Calendar日期："+cDateStr);

        //HOUR_OF_DAY   24小时制。    HOUR是12小时制。
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Calendar小时："+hour);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println("Calendar分："+minute);
        int second = calendar.get(Calendar.SECOND);
        System.out.println("Calendar秒:"+second);
        String cTimeStr =hour+"时"+minute+"分"+second+"秒";
        System.out.println("Calendar时间："+cTimeStr);
    }

}
