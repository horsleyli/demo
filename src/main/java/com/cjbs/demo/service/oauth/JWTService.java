package com.cjbs.demo.service.oauth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shj
 */
@Service
public class JWTService {

    private final Logger logger = LoggerFactory.getLogger(JWTService.class);


    public String getToken() {
        logger.debug("JWTService getToken");

        String secret = "secret";// token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        // 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        nowDate.setTime(System.currentTimeMillis());

        Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);// 2小过期

        String token = JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withIssuer("SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("this is test token")//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(algorithm);//签名 Signature

        return token;

    }

    private static Date getAfterDate(Date nowDate, int year, int month, int day, int hour, int minute, int second) {

        nowDate.setTime(nowDate.getTime() + year * 365*24*3600 + month * 30*24*3600 + day * 24*3600 + hour * 3600 + minute * 60 + second);
        return nowDate;
    }
}
