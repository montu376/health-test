package io.github.montu376.config;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.stream.Stream;

public class ApplicationConfig  extends WebSecurityConfigurerAdapter {

    private String ignoreantmatcher[];
    private String hasRolesMathcher[];

    public ApplicationConfig(){}

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.antMatcher("/h2-console/**").headers().frameOptions().disable();
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.authorizeRequests().antMatchers(ignoreantmatcher).permitAll().anyRequest().authenticated();

    }


    public String[] getIgnoreantmatcher() {
        return ignoreantmatcher;
    }

    public void setIgnoreantmatcher(String[] ignoreantmatcher) {
        this.ignoreantmatcher = ignoreantmatcher;
    }

    public String[] getHasRolesMathcher() {
        return hasRolesMathcher;
    }

    public void setHasRolesMathcher(String[] hasRolesMathcher) {
        this.hasRolesMathcher = hasRolesMathcher;
    }
}
