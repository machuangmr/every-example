package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义认证管理器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用内存的方式，只供测试使用，开发时候，我们使用 userDetailService 接口
        auth.inMemoryAuthentication().withUser("machd").password("{noop}1234").roles("USER");
    }

    /**
     * 可以自定义过滤器链
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 默认就是用父类的过滤器链
       // super.configure(http); 自定义登录页面
        http.authorizeRequests()
                .antMatchers("/tologin", "failure").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/tologin")
                .loginProcessingUrl("login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/failure")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/tologin")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
