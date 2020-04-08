package com.czkj.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author steven.sheng
 * @Date 2020/4/7/00714:53
 */
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().anyRequest().authenticated()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3")
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")//用户未登录时，访问任何资源都转跳到该路径，即登录页面
//                .defaultSuccessUrl("/welcome")//登录认证成功后默认转跳的路径
                .permitAll()
                .successHandler(loginSuccessHandler())
                .and()
                .logout().invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler())
        ;
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new UserDetailsServiceImpl()).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder(4);
    }

    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return (httpServletRequest, httpServletResponse, authentication) -> {
            try {
                User user = (User) authentication.getPrincipal();
                log.info("USER : " + user.getUsername()+ " LOGOUT SUCCESS !  ");
            } catch (Exception e) {
            }
            httpServletResponse.sendRedirect("/login");
        };
    }

    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, ServletException {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                log.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                response.sendRedirect("/welcome");
//                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    // 自定义认证规则
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("user1").password(passwordEncoder().encode("123")).roles("VIP1")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("123")).roles("VIP1", "VIP2")
//                .and()
//                .withUser("user3").password(passwordEncoder().encode("123")).roles("VIP1", "VIP2", "VIP3");
//
//
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源被拦截
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder(4).encode("123"));
    }

}
