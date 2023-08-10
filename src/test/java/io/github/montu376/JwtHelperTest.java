package io.github.montu376;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.servlet.http.HttpServletRequest;


class JwtHelperTest {
    String secret = "jkdghkjsfdkgkhsldgiuehryiutnmvcvjslerip";
    String subject = "montu376";
    UserDetails user = User.withUsername("montu376").accountExpired(false).password("FUCKOFF").build();
    UserDetailsManager userDetailsManager;
    String token = "";
}