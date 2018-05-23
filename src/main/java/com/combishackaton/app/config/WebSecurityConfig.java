package com.combishackaton.app.config;

import com.combishackaton.app.common.Constants;
import com.combishackaton.app.common.util.PropertyResolver;
import com.combishackaton.app.security.auth.jwt.JwtAuthenticationFilter;
import com.combishackaton.app.security.auth.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public WebSecurityConfig(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response
                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String storageRoot = String.format("/%s/**",
                (String) PropertyResolver.getInstance().getProperty(Constants.STORAGE_ROOT_FOLDER));

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint()).and().authorizeRequests()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/configuration/ui/**",
                    "/swagger-resources/**", "/configuration/security/**").permitAll()
            .antMatchers(HttpMethod.GET, "/bootstrap/**", "/**.js", "/**.css", "/exceptions/**").permitAll()
            .antMatchers(HttpMethod.GET, "/confirm", "/password/**", "/users/email/exists").permitAll()
            .antMatchers(HttpMethod.POST, "/password/**", "/security/**", "/users").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/**", "/facebook/oauth", "/google/oauth").permitAll()
            .antMatchers(HttpMethod.GET, storageRoot).permitAll()
            .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl().disable();
    }
}
