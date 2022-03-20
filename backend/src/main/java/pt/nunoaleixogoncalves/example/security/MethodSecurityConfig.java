package pt.nunoaleixogoncalves.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true)
public class MethodSecurityConfig
        extends GlobalMethodSecurityConfiguration {
}
