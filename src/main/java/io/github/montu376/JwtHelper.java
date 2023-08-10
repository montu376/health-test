package io.github.montu376;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.montu376.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtHelper {
    private Logger logger;

    String secret;

    public JwtHelper(String secret){
        this.logger = LoggerFactory.getLogger(getClass());
        this.secret= secret;
    }

    public String tokenParserFromRequest(HttpServletRequest request, String AUTHZTOKEN, boolean bearerPresent){
        String CHECKSTRING = (AUTHZTOKEN == null) ? Constant.AUTHORIZATION:AUTHZTOKEN;
        String tokenString = request.getHeader(CHECKSTRING);
        this.logger.info("TokenString " + tokenString);
        if(tokenString!=null){
            if(tokenString.contains(Constant.BEARER) == false && bearerPresent){
                return  tokenString;
            }
            return tokenString.substring(7);
        }
        return  null;
    }

    public String getToken(String username){
        return JWT.create().withSubject(username)
              .withExpiresAt(new Date(System.currentTimeMillis()+60*1000*10)).sign(Algorithm.HMAC512(secret));
    }

    public  Object retriveData(String token, Class type){
        JWTVerifier verifier =  JWT.require(Algorithm.HMAC512(secret)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        if(type.equals(Date.class)){
            return decodedJWT.getExpiresAt();
        }
        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token){
        Date expiry = (Date) retriveData(token,Date.class);
        String subject = (String) retriveData(token,String.class);
        return expiry.before(new Date(System.currentTimeMillis())) &&
                subject != null ? true : false;
    }


}
