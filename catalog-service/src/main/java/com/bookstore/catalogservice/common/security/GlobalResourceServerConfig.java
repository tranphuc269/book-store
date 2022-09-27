package com.bookstore.catalogservice.common.security;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.CharEncoding.UTF_8;

@Configuration
@EnableResourceServer
public class GlobalResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.jwt.public-key}")
    private Resource publicKey;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/api-docs/**", "/h2-console/**", "/signin", "/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
                .antMatchers("/**").authenticated();
    }


    @Bean
    @Primary
    public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
                OAuth2Authentication authentication = super.extractAuthentication(map);
                Authentication userAuthentication = authentication.getUserAuthentication();

                if (userAuthentication != null) {
                    String userId = (String) map.get("user_id");
                    String userName = (String) map.get("user_name");
                    if (userName != null) {
                        Map<String, String> extendedPrincipal = new HashMap<>();
                        extendedPrincipal.put("user_id", userId);
                        extendedPrincipal.put("user_name", userName);
                        ((UsernamePasswordAuthenticationToken) userAuthentication).setDetails(extendedPrincipal);
                    }
                }
                return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
            }
        };
        String publicKeyAsString = getPublicKeyAsString();
        converter.setVerifierKey(publicKeyAsString);
        return converter;
    }

    private String getPublicKeyAsString() {
        try {
            return IOUtils.toString(publicKey.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}