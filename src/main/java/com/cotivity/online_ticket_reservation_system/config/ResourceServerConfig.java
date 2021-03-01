package com.cotivity.online_ticket_reservation_system.config;

import com.cotivity.online_ticket_reservation_system.exception.ErrorResponse;
import com.cotivity.online_ticket_reservation_system.props.SecurityProperties;
import com.cotivity.online_ticket_reservation_system.utils.RequiredConstant;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@EnableResourceServer
@Configuration
@AllArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final SecurityProperties securityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)  {
        resources.resourceId("resource-server-rest-api").tokenStore(tokenStore())
                .authenticationEntryPoint(new AuthenticationEntryPoint(){
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
                        ObjectMapper mapper = new ObjectMapper();
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.getWriter().write(mapper.writeValueAsString(new ErrorResponse(RequiredConstant.MessageConstant.UNAUTHORIZED_USER,request.getRequestURI())));
                    }
                });
    }

    @Bean
    public TokenStore tokenStore(){
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(getPublicKeyAsString());
        jwtAccessTokenConverter.setAccessTokenConverter(new DefaultAccessTokenConverter(){
            @Override
            public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
                OAuth2Authentication auth = super.extractAuthentication(map);
                auth.setDetails(map);
                return auth;
            }
        });
        return jwtAccessTokenConverter;
    }

    private String getPublicKeyAsString(){
        try{
            String publicKey = IOUtils.toString(securityProperties.getJwt()
                    .getPublicKeyLocation().getInputStream(), StandardCharsets.UTF_8);
            return publicKey;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
