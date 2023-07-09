package com.example.lv.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description token 工具类
 * @date 2023/6/18 09:33:46
 */
public class JwtUtil {

    /**
     * 有效期 1小时
     */
    public static final Long JWT_TTL = 60*60*1000L;
    /**
     * 密钥明文
     */
    public static final String JWT_KEY = "xiaobai";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }

    /**
     * 生成jwt
     * @param subject token中存放的数据(json)
     * @param ttlMillis 有效期
     * @return
     */
    public static String createJWT(String subject,Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject,ttlMillis,getUUID());
        return builder.compact();
    }

    /**
     * 默认时间 null
     * @param subject
     * @return
     */
    public static String createJWT(String subject){
        JwtBuilder builder = getJwtBuilder(subject,null,getUUID());
        return builder.compact();
    }

    /**
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id,String subject,Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject,ttlMillis,id);
        return builder.compact();
    }
    /**
     *
     * @param subject
     * @param ttlMillis
     * @param uuid
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ObjectUtils.isEmpty(ttlMillis)){
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis+ttlMillis;
        Date expDate = new Date(expMillis);
        JwtBuilder builder = Jwts.builder()
                //唯一id
                .setId(uuid)
                //数据
                .setSubject(subject)
                //签发者
                .setIssuer("xb")
                //使用HS256对称加密算法签名
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
        return builder;
    }

    /**
     * 生成加密后的密钥
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
        return key;
    }

    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }
}
