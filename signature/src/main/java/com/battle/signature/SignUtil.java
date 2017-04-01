package com.battle.signature;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * 生成签名工具
 * Created by Bing.Z on 2017/3/30.
 */
public class SignUtil {

    private static final Logger logger = Logger.getLogger("logger");

    /**
     * 生成数字签名字符串。
     * @param signParams
     * @param primaryKey
     * @return
     */

    public static String buildSign(Map<String, String> signParams, String primaryKey) {
        
        if (signParams == null || signParams.isEmpty()) {
            return "";
        }
        // 过滤签名参数中的空值参数，按照参数名称排序后拼装签名字符串
        String signString = createLinkString(signParams);
        System.out.println(signString);
        // 签名字符串拼接安全秘钥
        signString = signString + primaryKey;
        logger.info("参与加密的串：" + signString);
        // 将签名字符串进行MD5加密后返回
        signString = Md5Encrypt.md5(signString, "UTF-8");
        return signString;
    }

    /**
     * 创建签名字符串 把map的key按照a-z排序，去除空值，并且按照 参数=参数值的模式用“&”字符拼接成字符串
     * @param params
     * @return
     */
    private static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList(params.keySet());
        //参数排序，从a-z排序
        Collections.sort(keys);
        String prestr = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            //空值，不参与签名
            if (!isEmpty(value)) {
                prestr = prestr + entry.getKey() + "=" + value + "&";
            }
        }
        //去除最后一个&
        prestr = prestr.substring(0, prestr.lastIndexOf("&"));
        return prestr;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || "null".equals(str) || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String signKey = "key123";
        Map<String, String> params = new HashMap<>();
        params.put("key", "aaa");
        params.put("bas", "df");
        String sign = buildSign(params, signKey);
        logger.info(sign);
    }
}
