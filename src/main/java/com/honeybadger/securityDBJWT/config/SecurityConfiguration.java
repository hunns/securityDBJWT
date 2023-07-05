package com.honeybadger.securityDBJWT.config;

import com.honeybadger.securityDBJWT.security.JWTAuthenticationEntryPoint;
import com.honeybadger.securityDBJWT.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private  AuthenticationProvider authenticationProvider;
   // private  LogoutHandler logoutHandler;
    @Autowired
    private JWTAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    private final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////    http
//        .csrf(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests()
//        .requestMatchers(
//                "/api/v1/auth/**",
//                "/v2/api-docs",
//                "/v3/api-docs",
//                "/v3/api-docs/**",
//                "/swagger-resources",
//                "/swagger-resources/**",
//                "/configuration/ui",
//                "/configuration/security",
//                "/swagger-ui/**",
//                "/webjars/**",
//                "/swagger-ui.html"
//        )
//          .permitAll()
//
//
//        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
//        .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
//        .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
//
//
//       /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//
//        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//        .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//        .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//        .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/
//
//
//        .anyRequest()
//          .authenticated()
//        .and()
//          .sessionManagement()
//          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authenticationProvider(authenticationProvider)
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//        .logout()
//        .logoutUrl("/api/v1/auth/logout")
//        .addLogoutHandler(logoutHandler)
//        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//    ;
//
//    return http.build();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
logger.info("securityFilterChain is called..");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/auth/**")
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))

                .sessionManagement(
                        session -> session.sessionCreationPolicy
                                (SessionCreationPolicy.STATELESS
                                )
                );
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

