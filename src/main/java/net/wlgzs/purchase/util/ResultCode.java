package net.wlgzs.purchase.util;

public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 操作失败
     */
    FAIL(-1,"操作失败");
    /**
     * 状态码
     */
    public int code;
    /**
     * 信息
     */
    private String msg;

    ResultCode(int code, String msg) {
     this.code = code;
     this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
