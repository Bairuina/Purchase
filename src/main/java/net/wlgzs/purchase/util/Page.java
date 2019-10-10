package net.wlgzs.purchase.util;
import java.util.ArrayList;
import java.util.List;


public class Page<T> {
    private List<T> date;      //全部数据

    private int length;          //每页长度

    private int size;            //总页数


    //总页数赋值
    public void setSize(){
        int datelength=date.size();
        this.size= datelength%length==0? datelength/length:datelength/length+1;
    }
    //获取当前页的数据
    public List<T> getDateByYs(int ys){
        int left=(ys-1)*length+1;
        int right=(ys*length)>date.size()? date.size():ys*length;
        List<T> list=new ArrayList();
        for (int i = left-1; i < right ; i++) {
            list.add(date.get(i));
        }
        return list;
    }

    public List<T> getDate() {
        return date;
    }

    public void setDate(List<T> date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSize() {
        return size;
    }
}
