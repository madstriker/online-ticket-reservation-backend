package com.cotivity.online_ticket_reservation_system.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties("spring")
@Data
public class SecurityProperties {

    private JwtProperties jwt;

    @Data
    public static class JwtProperties{

        private Resource publicKeyLocation;
    }
}
