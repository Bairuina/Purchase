package net.wlgzs.purchase;


import net.sf.json.JSONObject;
import net.wlgzs.purchase.util.ClientUtil;


public class Demo {


    public static void main(String[] args) {
        String str="{\n" +
                "\"username\": 7223, \n" +
                "\"pwd\": \"ff8080814a1353ac014a139496110049\", (加密后密码)\n" +
                "\"ddbh\": \"WSCG000074\", \n" +
                "\"sfcd\": 0, \n" +
                "\"fczddbh\": \"fc0001\", \n" +
                "\"kdgs\": \"顺丰速运\", \n" +
                "\"kddh\": \"0000001\", \n" +
                "\"ms\": \"离开濮阳县速递物流分公司 发往濮阳县邮政局速递公司\",\n" +
                "\"kdsj\": 20160120142700\n" +
                "}\n";
        JSONObject d=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl", "exeLogistics",str);
        System.out.println(d+"====================================================");
    }

}
