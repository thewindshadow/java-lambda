package com.battle.signature;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bing.Z on 2017/3/31.
 */
public class ApiCall {
    static Logger logger = Logger.getLogger("ApiCall");
    private static final String BIZ_CODE = "ulewo";
    private static final String SIGN_KEY = "www.ulewo.com";

    public static String initParamStr(Map<String, String> params) {
        String paramString = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            paramString = paramString + entry.getKey() + "=" + value + "&";
        }
        //去除最后一个&
        paramString = paramString.substring(0, paramString.lastIndexOf("&"));
        return paramString;
    }


    public static String getAddUserUrl() {
        String url = "http://www.ulewo.com/xxx/xxx";
        Map<String, String> params = new HashMap<>();
        params.put("bizCode", BIZ_CODE);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("userName", "张三");
        params.put("userAge", "20");
        //签名字符串拼接安全秘钥进行MD5加密
        String sign = SignUtil.buildSign(params, SIGN_KEY);
        params.put("sign", sign);
        //将参数进行编码
        URLEncoderUtil.encodeMap(params);
        String paramsForQuery = initParamStr(params);
        url = url + "?" + paramsForQuery;
        return url;
    }

    public static void main(String[] args) {
        logger.info(String.format("请求的地址：%s", getAddUserUrl()));
        String user = ApiProvider.createUser("ulewo", 1491031648500L, "eec25a86a9ecbf8c07407f85434fafe8", "%E5%BC%A0%E4%B8%89", 20);
        System.out.println(user);
    }
}
