package net.wlgzs.purchase.util;


import net.sf.json.JSONObject;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class ClientUtil {
    static Logger logger= LoggerFactory.getLogger(ClientUtil.class);
    public static JSONObject getJSONObject(String url,String wayName,String parameter){
        try {
            Client client=new Client(new URL(url));
            Object[] rets=client.invoke(wayName,new Object[]{parameter});
            String result=rets[0].toString();
//            logger.info(result);
            return JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
