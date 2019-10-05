package net.wlgzs.purchase;


import org.joda.time.DateTime;

public class Demo {


    public static void main(String[] args) {
        DateTime dateTime=new DateTime();
        String jssj =dateTime.toString("yyyy-MM-d  HH：mm：ss") ;
        System.out.println(jssj);

    }
}
