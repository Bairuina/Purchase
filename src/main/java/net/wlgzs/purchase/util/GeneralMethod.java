package net.wlgzs.purchase.util;

import com.Enxi;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

/**
 * @Author HYStar
 * @Date 2019/10/3 21:06
 */
public class GeneralMethod {

    public static String queryContract(String ddbh,ReadProperties readProperties){
        //本地不存在的时候
        String result = "";
        String username = readProperties.getUsername();
        String pwd = readProperties.getPwd();
        String enPwd = Enxi.enPwd(username, pwd);
        try {
            Client client = new Client(new URL(readProperties.getUrl()));
            String json = "{\"username\": 7223, \"pwd\": \"" + enPwd + "\",\"ddbh\": \"" + ddbh + "\"}";
            Object[] rets = client.invoke(readProperties.getFindOrderHt(), new Object[]{json});
            result = (String) rets[0];
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
