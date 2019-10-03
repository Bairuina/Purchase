package net.wlgzs.purchase.util;


import net.sf.json.JSONObject;
import org.codehaus.xfire.client.Client;

import java.net.URL;

public class ClientUtil {
    public static JSONObject getJSONObject(String url,String wayName,String parameter){
        try {
            Client client=new Client(new URL(url));
            Object[] rets=client.invoke(url,new Object[]{parameter});
            String result=rets[0].toString();
            return JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}