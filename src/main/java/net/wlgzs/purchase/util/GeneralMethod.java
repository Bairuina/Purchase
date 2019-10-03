package net.wlgzs.purchase.util;

import com.Enxi;
import org.codehaus.xfire.client.Client;

import java.net.URL;

/**
 * @Author HYStar
 * @Date 2019/10/3 21:06
 */
public class GeneralMethod {

    public static String queryContract(String ddbh){
        //本地不存在的时候
        String result = "";
        String username = "7223";
        String pwd = "ff8080814a1353ac014a139496110049";
        String enPwd = Enxi.enPwd(username, pwd);
        try {
            Client client = new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            String json = "{\"username\": 7223, \"pwd\": \"" + enPwd + "\",\"ddbh\": \"" + ddbh + "}";
            Object[] rets = client.invoke("findSprkandParam", new Object[]{json});
            result = (String) rets[0];
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
