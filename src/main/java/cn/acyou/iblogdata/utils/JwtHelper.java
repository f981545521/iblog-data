package cn.acyou.iblogdata.utils;

import cn.acyou.iblogdata.entity.jjwt.Role;
import cn.acyou.iblogdata.entity.jjwt.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.SignatureException;
import java.util.UUID;

/**
 * https://github.com/jwtk/jjwt
 * @author youfang
 * @version [1.0.0, 2018-08-23 上午 09:10]
 **/
public class JwtHelper {

    public static void main(String[] args) {
        JWTUtility jwtUtility = new JWTUtility();
        Role role = new Role(110, "very good");
        User user = new User();
        user.setId("123");
        user.setRole(role);
        String token = jwtUtility.getToken(user);
        System.out.println("您的token - " + token);
        User truestUser = jwtUtility.getUser(token);
        System.out.println(truestUser);

    }

    public static void main2(String[] args) {
        //我们需要一个签名密钥，所以我们只为这个例子创建一个。通常//将从您的应用程序配置中读取密钥。
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        //System.out.println(jws);
        String jws = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.v7CWaS-FStxMVuXuz5SG8-3CaD5vLmiC_t90_ggXHcM";
        try {
            System.out.println("开始验证");
            Jws<Claims> claimsJws =  Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
            System.out.println("Signature" + claimsJws.getSignature());
            System.out.println("OK, we can trust this JWT");
        } catch (JwtException e) {
            System.out.println("don't trust the JWT!");
        }
        //断言是正确的
        //assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
    }

    class JwtToken {
        private String userId;
        private String userName;
        private String uuid = UUID.randomUUID().toString();
    }
}

