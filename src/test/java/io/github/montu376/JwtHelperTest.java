package io.github.montu376;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


class JwtHelperTest {

    String secret = "jkdghkjsfdkgkhsldgiuehryiutnmvcvjslerip";
    String subject = "montu376";

    String token = "";
    JwtHelper helper = new JwtHelper();

    Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeEach
    void setUp() {
        this.token = helper.getToken(subject,secret);

    }

    @Test
    void getToken() {
        Assertions.assertNotEquals("",this.token);
    }

    @Test
    void validateToken() {
        Assertions.assertNotEquals("",this.token);
        logger.info(this.token);
        Assertions.assertTrue(this.helper.validateToken(this.token,this.secret));
        logger.info("Validation : "+ this.helper.validateToken(this.token,this.secret));

    }

    @Test
    void tokenParserFromRequestTest() {






    }
}