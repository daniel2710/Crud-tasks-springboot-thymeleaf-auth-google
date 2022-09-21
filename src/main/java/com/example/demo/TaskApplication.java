package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@SpringBootApplication
// CLASE MAIN QUE EXTIENDE DE WEB SECURITY PARA LA CONFIGURACION
public class TaskApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	String[] resources = new String[]{
			"/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Permite configurar la seguridad basada en web para solicitudes http específicas.
		http
				.authorizeRequests(a -> {
							try {
								a
										//  El http.antMatchers establece que HttpSecurity solo se aplicará a las URL que comiencen con los siguientes enpoints
										.antMatchers("/", "/error", "/webjars/**").permitAll()
										.antMatchers(resources).permitAll()
										.anyRequest().authenticated()
										.and()
										.logout(l -> l
												.logoutSuccessUrl("/").permitAll()
										)
										.csrf(c -> c
												.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
										);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
				)
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.oauth2Login();
	}


}