package org.security.jwt;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.security.entity.AccessToken;
import org.security.util.MyUtils;

public class JwtHelper {
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String createJWT(String name, String userId, String role,
                                   String audience, String issuer, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    public static void main(String[] args) {
        System.out.println("login................");
        String resultMsg;
        String salt = "123456";
        String name = "admin";
        String role = "SYS_ADMIN";
        String clientID = "123";
        String entityName = "entityName";
        String loginName = "loginAdmin";
        String loginPassword = "loginPassword";
        String md5Password = MyUtils.getMD5(loginName + salt);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(loginPassword, String.valueOf(name),
                role, clientID, entityName,
                604800, "mySecret");
        System.out.println("accessToken:" + accessToken);
    }
}