package com.nhs.individual.Config;

import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Utils.IUserDetail;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    AccountService service;

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }
//    @Bean
//    public TokenExceptionFilter tokenExceptionFilter(){
//        return new TokenExceptionFilter();
//    }
    @Bean
    public LogoutHandler logoutHandler(){
        return new LogoutHandlerCustomize();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(c->{
                    c.configurationSource(corsConfigurationSource());
                    c.configure(httpSecurity);

                })
                .logout(logout->logout.addLogoutHandler(logoutHandler()))
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(req->{
                    req.requestMatchers("/test").permitAll()
                            .requestMatchers("/login").anonymous()
                            .requestMatchers("/register").anonymous()
                            .requestMatchers("/refresh").anonymous()
                            .requestMatchers("/api/v1/product").permitAll()
                            .anyRequest().permitAll();
                })
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

//                .addFilterBefore(tokenExceptionFilter(), JwtFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return service.findByUsername(username)
                        .map(IUserDetail::parseFrom)
                        .orElseThrow(()->{
                            System.out.println("Find 0 match");
                            return new UsernameNotFoundException(username+"Not found");
                        });
            }
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
//    @Bean
//    public WebMvcConfigurer configure(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowCredentials(true)
//                        .allowedHeaders("*")
//                        .allowedMethods("*");
//            }
//        };
//    }
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://127.0.0.1:3000")); //or add * to allow all origins
    configuration.setAllowedMethods(List.of("*")); //to set allowed http methods
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
}
