package com.battle.signature;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * URL解码工具
 * Created by Bing.Z on 2017/3/30.
 */
public class URLDecodeUtil {

    /**
     * 解码
     * @param str 需要解码的字符串
     * @return 解码后字符串
     */
    public static String decode(String str){

        return decoder(str,"UTF-8");
    }

    /**
     * 解码
     * @param str 需要解码的字符串
     * @param encoding 指定的字符集
     * @return 解码后的字符串
     */
    public static String decoder(String str, String encoding){
        if(StringUtils.isEmpty(str))return str;
        str = str.trim().replace("+", "%2B");
        try {
            return URLDecoder.decode(str,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
