package io.github.montu376.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class ApplicationConfig  {

    private String ignoreantmatcher[];
    private String hasRolesMathcher[];

    public ApplicationConfig(String ignoreantmatcher[]){
        this.ignoreantmatcher = ignoreantmatcher;
    }


    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/h2-console/**");
    }


    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.disable();
        }).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(this.ignoreantmatcher).permitAll().anyRequest().authenticated();
        }).sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }).csrf(httpSecurityCsrfConfigurer -> {
            httpSecurityCsrfConfigurer.disable();
        });

        return http.build();
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
