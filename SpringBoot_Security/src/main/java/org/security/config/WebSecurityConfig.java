package org.security.config;
import org.security.service.CustomUserService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	protected UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				// 设置UserDetailsService
				.userDetailsService(customUserService())
				// 使用BCrypt进行密码的hash
				.passwordEncoder(passwordEncoder());
	}
	// 装载BCrypt密码编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		 添加JWT filter
//		http.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class);
//		http.authorizeRequests().antMatchers("/**").permitAll();
//		.antMatchers("/admin/**").hasIpAddress("127.0.0.1")
//		.antMatchers("/admin/**").access("hasAuthority('ROLE_ADMIN')")
//		.anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.failureUrl("/login?error").permitAll().and().logout().permitAll();

		httpSecurity
				// 由于使用的是JWT，我们这里不需要csrf
				.csrf().disable()
				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				//.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// 允许对于网站静态资源的无授权访问
				.antMatchers(
						HttpMethod.GET,
						"/",
						"/*.html",
						"/favicon.ico",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js"
				).permitAll()
				// 对于获取token的rest api要允许匿名访问
				.antMatchers("/auth/**").permitAll()
				// 除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();
		// 添加JWT filter
		httpSecurity
				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		// 禁用缓存
		httpSecurity.headers().cacheControl();
	}
}
