package com.battle.signature;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务提供者
 * Created by Bing.Z on 2017/3/30.
 */
public class ApiProvider {

    static Logger logger = Logger.getLogger(ApiProvider.class);
    private static final String BIZ_CODE = "ulewo";
    private static int TIMEOUT = 60 * 1000;  //1分钟
    private static final String SIGN_KEY = "www.ulewo.com";
    /**
     * 这里就是一个普通的controller方法，实际应用中，这里会返回一个json，或者xml，包含 状态码，错误信息，返回的接口信息等，这里演示，就简单的返回一个字符串
     */
    public static String createUser(String bizCode, Long timestamp, String sign, String userName, Integer userAge){
        //做接口，最好把请求参数都输出到日志，这样便于查询问题
        logger.info(String.format("[创建用户接口]请求参数信息bizCode:%s,timestamp:%s,sign:%s,userName:%s,userAge:%s", bizCode, timestamp, sign, userName, userAge));
        //URLDECODE参数
        bizCode = URLDecodeUtil.decode(bizCode);
        sign = URLDecodeUtil.decode(sign);
        userName = URLDecodeUtil.decode(userName);
        if (null == bizCode || null == timestamp || null == sign || null == userName) {
            return "请求参数不完整";
        }

        //判断业务线是否存在，一个接口肯定要给很多不同的业务线使用，一般业务线是存数据库的，通过bizCode查询业务线是否存在，这里演示，就直接写成变量了
        if (!BIZ_CODE.equals(bizCode)) {
            return "业务线不存在";
        }
        // 判断链接是否失效，生成一个请求链接，有个超时时间，防止链接呗劫持，反复请求，这里的超时时间根据自己业务设置不同的时间
//        if (System.currentTimeMillis() - timestamp.intValue() >= TIMEOUT) {
//            return "链接已失效";
//        }
        // 检查签名
        Map<String, String> signParams = new HashMap<String, String>();
        signParams.put("bizCode", bizCode);
        signParams.put("timestamp", String.valueOf(timestamp));
        signParams.put("userName", String.valueOf(userName));
        signParams.put("userAge", String.valueOf(userAge));
        String mysign = SignUtil.buildSign(signParams, SIGN_KEY);
        if (!sign.equals(mysign)) {
            return "数字签名有误";
        }
        //TODO 处理具体的接口逻辑
        return null;
    }
}
