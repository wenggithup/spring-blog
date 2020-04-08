package com.banyuan.springdemo.config;

import com.banyuan.springdemo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("db")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf，跨越攻击防护
        http.csrf ().disable ()
                //设置静态资源允许所有访问
                .authorizeRequests ()
                //以下路径需要认证，其它路径通过
                .antMatchers ("/admin/**").authenticated ()
                .anyRequest ().permitAll ()
                .and ()
                    //登陆页面的getmapping
                    .formLogin ().loginPage ("/login")
                    //如果用户直接访问的login，应该跳转到什么url；如果用户访问的其它url，则跳转到之前的url地方去
                    .defaultSuccessUrl ("/admin")
                .and ()
                .logout ()
                    .invalidateHttpSession (true)
                    .clearAuthentication (true)
                    .logoutUrl ("/logout")
                    .logoutSuccessUrl ("/").permitAll ();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //dao层认证生产者
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider ();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

}
