package com.romeone.springapp.config;

import com.romeone.springapp.security.UrlAuthenticationSuccessHandler;
import com.romeone.springapp.security.jwt.AuthEntryPointJwt;
import com.romeone.springapp.security.jwt.AuthTokenFilter;
import com.romeone.springapp.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(
        // securedEnabled = true, // enables @Secured annotation eg. @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
        // jsr250Enabled = true,
        prePostEnabled = true) // enable @PreAuthorize and @PostAuthorize
public class SecurityConfiguration {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    private static Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Init SecurityFilterChain");

        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set exception handling and session management
        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and();

        http.sessionManagement()
                .invalidSessionUrl("/login")
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(2);

        http.authorizeRequests()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/", "/src/**/*", "/greeting", "/api/auth/**", "/api/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/greeting", true)
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .httpBasic();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
            return new UrlAuthenticationSuccessHandler();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
}