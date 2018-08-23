package cn.acyou.iblogdata.utils;

import cn.acyou.iblogdata.entity.jjwt.Role;
import cn.acyou.iblogdata.entity.jjwt.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-23 上午 09:35]
 **/
public class JWTUtility {

    private final static int EXPIRATION_HOURS = 1;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String getToken(User user) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(user.getId()) // Subject is the unique identifier, this is usually an user ID or unique username
                .signWith(key)
                .claim("roleId", user.getRole().getId())
                .claim("roleDesc", user.getRole().getDescription()) // Add more claims according to your model class
                .setIssuedAt(now)
                .setExpiration(getExpirationTime(now))
                .compact();
    }

    public User getUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Role role = new Role();
        role.setId((int)claims.get("roleId"));
        role.setDescription(claims.get("roleDesc").toString());

        /*
        You can insert a List as a claim if you need to, just cast it while getting it back. For Example:
        List<Permissions> permissions = (List) claims.get("permissions");
        */

        User user = new User();
        user.setId(claims.getSubject());
        user.setRole(role);

        return user;
    }

    private Date getExpirationTime(Date now) {
        Long expireInMillis = TimeUnit.HOURS.toMillis(EXPIRATION_HOURS);
        return new Date(expireInMillis + now.getTime());
    }
}
