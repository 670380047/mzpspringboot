package util.listtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/15 14:08
 * @File : ListAndSet
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class ListAndSet {

    public static void main(String args[]){
        listUnion();
    }

    public static void listUnion(){
        List aList = new ArrayList<>();
        aList.add("a");
        aList.add("b");
        System.out.println("aList:"+aList);

        List<Object> bList = new ArrayList<>();
        bList.add("b");
        bList.add("c");
        System.out.println("bList:"+bList);

        //求并集（适用于多个集合求并集）方法一：
        //1.把两个集合a和b相加（多个集合时，都相加）。
        bList.addAll(aList);
        //bList.addAll(bList);
        System.out.println("合并（相加）后的bList:"+bList);

        //2.把求和的结果直接放入set中
        Set set = new HashSet<>(bList);
        System.out.println("并集set："+set);

        System.out.println("===============================");
        List cList = new ArrayList<>();
        cList.add("a");
        cList.add("b");
        System.out.println("cList:"+cList);

        List<Object> dList = new ArrayList<>();
        dList.add("b");
        dList.add("c");
        System.out.println("dList:"+dList);
        //求并集（只适用于两个集合求并集）方法二：
        //1.先移除c集合中包含的d集合中的元素，即去重
        cList.removeAll(dList);
        //2.然后再把两个没有重复元素的集合加起来
        cList.addAll(dList);
        System.out.println("并集list"+cList);
    }
}
