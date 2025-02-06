package com.passwordgenerator.damiangrudzien.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@AllArgsConstructor
//@Slf4j
public class SecurityConfig {


    private AuthenticationEntryPointHandler authenticationEntryPointHandler;
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;


//    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }


//    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
//        log.info("inside cors");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://damiangrudzien.github.io/password-generator-react-app/"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept",
                "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "PUT", "DELETE", "GET", "OPTIONS"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.cors(c -> c.configurationSource(corsConfigurationSource()));
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(r ->
                            r.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/index.html")).hasRole("ADMIN")
                                    .requestMatchers(mvc.pattern(HttpMethod.POST,"/api/v1/user")).permitAll()
                                    .requestMatchers(mvc.pattern("/actuator/**")).permitAll()
                                    .requestMatchers(mvc.pattern("/api/v1/user/**")).authenticated()
                                    .requestMatchers(mvc.pattern("/api/v1/word/**")).authenticated()
                                    .requestMatchers(mvc.pattern("/api/v1/password/**")).authenticated()
                                    .requestMatchers(mvc.pattern("/h2-console/**")).hasRole("ADMIN")
                                    .anyRequest().authenticated())
                .formLogin(f -> f.usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(c -> c.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/actuator/**")))
                .httpBasic(b -> b.authenticationEntryPoint(authenticationEntryPointHandler));
        http.csrf(AbstractHttpConfigurer::disable).headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}
