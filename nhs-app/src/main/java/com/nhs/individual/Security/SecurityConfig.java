package com.nhs.individual.Security;

import com.nhs.individual.Security.Filter.JwtFilter;
import com.nhs.individual.Security.Oauth2.Oauth2Service;
import com.nhs.individual.Security.Oauth2.Oauth2SuccessHandler;
import com.nhs.individual.service.AccountService;
import com.nhs.individual.secure.IUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    AccountService service;
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public LogoutHandler logoutHandler(){
        return new LogoutHandlerCustomize();
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return  new LogoutSuccessHandlerCustomize();
    }
    @Bean
    public SimpleUrlAuthenticationSuccessHandler oauthSuccessHandler(){
        return new Oauth2SuccessHandler();
    }
    @Bean
    DefaultOAuth2UserService oAuth2UserService(){
        return new Oauth2Service();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(c->{
                    c.configurationSource(corsConfigurationSource());
                    c.configure(httpSecurity);
                })
                .logout(logout-> {
                    logout.addLogoutHandler(logoutHandler());
                    logout.logoutSuccessHandler(logoutSuccessHandler());
                    try {
                        logout.configure(httpSecurity);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                })
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(req->{
                    req.requestMatchers("/test/**").permitAll()
                            .requestMatchers("/login").anonymous()
                            .requestMatchers("/register").anonymous()
                            .requestMatchers("/refresh").anonymous()
                            .requestMatchers("/logout").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/oauth2/**").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new RestAuthenticationEntryPoint());
                })
                .oauth2Login(customize->{
                    customize
                            .authorizationEndpoint(authorizationEndpointConfig -> {
                                authorizationEndpointConfig
                                        .baseUri("/oauth2/authorize");
                            })
                            .userInfoEndpoint(oauthUser->{
                                oauthUser.userService(oAuth2UserService());
                            })
                            .successHandler(oauthSuccessHandler());


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
                        .map(IUserDetail::new)
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*/**"); //or add * to allow all origins
        configuration.setAllowedMethods(List.of("*")); //to set allowed http methods
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowPrivateNetwork(true);
        configuration.setExposedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
