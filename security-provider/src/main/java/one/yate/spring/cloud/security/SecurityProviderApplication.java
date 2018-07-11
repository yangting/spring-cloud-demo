package one.yate.spring.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityProviderApplication {
    public static void main(String ... as){
        SpringApplication.run(SecurityProviderApplication.class,as);
    }
}
