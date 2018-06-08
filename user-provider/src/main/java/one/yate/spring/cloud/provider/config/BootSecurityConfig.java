//package one.yate.spring.cloud.provider.config;
//
//import lombok.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.net.PasswordAuthentication;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class BootSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    CustomUserDetailService customUserDetailService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        DelegatingPasswordEncoder
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//    }
//
//    @Component
//    class CustomUserDetailService implements UserDetailsService {
//        public UserDetails loadUserByUsername(String username) {
//            switch (username) {
//                case "user": {
//                    return new SecurityUser(1L, "user", "password1", "user-role");
//                }
//                case "admin": {
//                    return new SecurityUser(2L, "admin", "password2", "admin-role");
//                }
//                default:
//                    return null;
//            }
//        }
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @RequiredArgsConstructor
//    class SecurityUser implements UserDetails {
//        @NonNull
//        private Long id;
//        @NonNull
//        private String username;
//        @NonNull
//        private String password;
//        @NonNull
//        private String role;
//
//        private boolean accountNonExpired = true;
//        private boolean accountNonLocked = true;
//        private boolean credentialsNonExpired = true;
//        private boolean enabled = true;
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);
//            auths.add(auth);
//            return auths;
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return accountNonExpired;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return accountNonLocked;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return credentialsNonExpired;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return enabled;
//        }
//    }
//}
