package com.battle.signature;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * URL编码工具
 * Created by Bing.Z on 2017/3/30.
 */
public class URLEncoderUtil {

    public static String encode(String str){
        return encoder(str,"UTF-8");
    }

    /**
     * 编码
     * @param str 需要编码的字符串
     * @param encoding 字符集
     * @return
     */
    public static String encoder(String str, String encoding) {
        if (StringUtils.isEmpty(str)) return null;
        try {
            return URLEncoder.encode(str, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void encodeMap(Map<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map.put(entry.getKey(), encode(entry.getValue()));
        }
    }
}
