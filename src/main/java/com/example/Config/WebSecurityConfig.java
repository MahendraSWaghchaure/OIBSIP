/*
 * package com.example.Config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import com.example.services.CustomUserDetailsService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private CustomUserDetailsService customUserDetailsService;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(customUserDetailsService)
 * .passwordEncoder(passwordEncoder()); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .csrf().disable() .authorizeRequests()
 * .antMatchers("/api/auth/**").permitAll() // Allow authentication endpoints
 * .antMatchers("/h2-console/**").permitAll() // Allow H2 console if needed
 * .anyRequest().authenticated() .and() .sessionManagement()
 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 * 
 * // Enable h2-console frame options if needed
 * http.headers().frameOptions().disable(); }
 * 
 * @Bean
 * 
 * @Override public AuthenticationManager authenticationManagerBean() throws
 * Exception { return super.authenticationManagerBean(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); } }
 */