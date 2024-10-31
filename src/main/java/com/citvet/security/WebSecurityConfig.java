package com.citvet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import com.citvet.handler.CustomAuthenticationSuccessHandler;
import com.citvet.service.UserDetailsServiceImpl;




@Configuration
@EnableWebSecurity

public class WebSecurityConfig {
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
            	.requestMatchers("/", "/img/**","/js/**","/css/**", "/login", "/registrarUser").permitAll()
                .requestMatchers("/cliente-listado","/cliente-nuevo","/mascota-listado","/mascota-nueva",
                				"/servicio-listado","/servicio-nuevo","/veterinario-listado",
                				"/veterinario-nuevo").hasAnyAuthority("admin")
                
                .requestMatchers("/citas","/vercita").hasAnyAuthority("admin","cajero(a)")

                .requestMatchers("/home").hasAnyAuthority("admin","cajero(a)")
                
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("usuario")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler())
                .failureUrl("/login?error=true"))
            .logout(logout -> logout
            	.logoutSuccessUrl("/login?logout")  // Cambia la URL de éxito al registro de usuario
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID"));

        return http.build();
    }
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}