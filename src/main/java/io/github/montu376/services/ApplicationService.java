package io.github.montu376.services;

import io.github.montu376.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ApplicationService  {

    private JwtHelper jwtHelper;

    public JwtHelper getJwtHelper() {
        return jwtHelper;
    }

    public void setJwtHelper(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    public boolean validateJwtToken(HttpServletRequest request,String AUTHZTOKEN, boolean bearerPresent){
        String token = jwtHelper.tokenParserFromRequest(request,AUTHZTOKEN,bearerPresent);
        return jwtHelper.validateToken(token) == true ? true: false;
    }

}
