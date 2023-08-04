package io.github.montu376;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.montu376.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtHelper {

    String secret;
    JWTVerifier verifier ;

    public JwtHelper(String secret) {
        this.secret = secret;
        verifier  =  JWT.require(Algorithm.HMAC512(this.secret)).build();
    }

    public String tokenParserFromRequest(HttpServletRequest request, String AUTHZTOKEN){
        String CHECKSTRING = (AUTHZTOKEN == null) ? Constant.AUTHORIZATION:AUTHZTOKEN;
        String tokenString = request.getHeader(CHECKSTRING);
        if(tokenString!=null){
            if(!tokenString.contains(Constant.BEARER)){
                return tokenString;
            }
            return tokenString.substring(7);
        }
        return  null;
    }

    public String getToken(String username){
        return JWT.create().withSubject(username)
              .withExpiresAt(new Date(System.currentTimeMillis()+60*1000*10)).sign(Algorithm.HMAC512(secret));
    }

    public Object retriveData(String token,Class type){
        DecodedJWT decodedJWT = this.verifier.verify(token);
        if(type.equals(Date.class)){
            return decodedJWT.getExpiresAt();
        }
        return decodedJWT.getSubject();
    }
    public  boolean validateToken(String token){
        Date expiry = (Date) retriveData(token,Date.class);
        String subject = (String) retriveData(token,String.class);
        return true;
    }

}
