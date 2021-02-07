package com.myz.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.KeyPair;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author maoyz
 */
public class JwtUtils {

    /**
     * 官方字段
     * iss (issuer)：签发人
     * exp (expiration time)：过期时间
     * sub (subject)：主题
     * aud (audience)：受众
     * nbf (Not Before)：生效时间
     * iat (Issued At)：签发时间
     * jti (JWT ID)：编号
     */
    public static void main(String[] args) {
        Header header0 = Jwts.header();
        Set set = header0.entrySet();
        System.out.println(set);
        Set keySet = header0.keySet();
        System.out.println(keySet);

        //////////////////////////////////////////////////////////////////////////////

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "abc");
        claims.put("role", "admin");

        // 对称秘钥
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // 非对称秘钥
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        // 生成
        String jwt = Jwts.builder()
                .setSubject("maoyz")
                .setAudience("123")
                // map参数
                .addClaims(claims)
                // k-v
                .claim("password", 123456)
                // 过期时间
                .setExpiration(expirate())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        System.out.println(jwt);


        Jwt parse = Jwts.parser()
                .setSigningKey(key)
                .parse(jwt);
        // header={alg=HS512},
        // body={sub=maoyz, aud=123, role=admin, username=abc, password=123456, exp=1576484826},
        // signature=DiGys6eviiq9D1Vd1JkBFwFE_TsQ99vFe5k927JUNuWr_T_kQyoQoTFFvBL0kyVz5Owug9m9cOlqdV8PXwqlpQ
        System.out.println(parse);

        Header header = parse.getHeader();
        System.out.println("Header => " + header);

        Object body = parse.getBody();
        System.out.println("Body => " + body);

        // 解析, 直接获取payload参数
        Claims parseClaims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(parseClaims);
    }

    /**
     * 设置过期时间， 默认2H
     *
     * @return
     */
    private static Date expirate() {
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(2);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 设置过期时间， 默认2H
     *
     * @return
     */
    private static Date expirate(long hours) {
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(hours);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
