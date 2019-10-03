package net.wlgzs.purchase.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;
    private int count;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 当前页数
     */
    private long current;

    public Result(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }
    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    public Result(ResultCode resultCode,String msg){
        this.code=resultCode.getCode();
        this.msg=msg;
    }
    public Result(ResultCode resultCode,String msg,Object data){
        this.code=resultCode.getCode();
        this.msg=msg;
        this.data=data;
    }
    public Result(ResultCode resultCode,String msg,int count,Object data){
        this.code=resultCode.getCode();
        this.msg=msg;
        this.data=data;
        this.count=count;
    }

    //分页数据
    public Result(ResultCode resultCode,String msg,Object data,long pages,long current) {
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
        this.pages = pages;
        this.current = current;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
