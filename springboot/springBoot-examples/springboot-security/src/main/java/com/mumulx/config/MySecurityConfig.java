package com.mumulx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁止隧道 // 禁止跨域 // 禁止头部
        http.csrf().disable().cors().disable().headers().disable();

        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆

        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*        spring security 5.0 密码未加密报错
            使用spring security5.0后，配置文件中直接写普通的密码如：123456，会报错:

            java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"

            这是因为spring security5.0以后默认需要选择密码加密方式，如果还像之前版本直接配置未加密密码，就会报上面这个错误当然啦，如果还想用简单密码的话，spring security还是给了两个方案，一种是在配置文件中配置:

    <bean id="passwordEncoder" class="org.springframework.security.crypto.password.NoOpPasswordEncoder" factory-method="getInstance"/>

                    l另一种就是在你配置密码那里加上{noop}如：{noop}123456
                    <security:user name="zhangsan" password="{noop}zhangsan" authorities="ROLE_ADMIN"/>

            */

        //super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("{noop}123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("{noop}123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("{noop}123456").roles("VIP1", "VIP3");
        /*
          说明：
            1.这里采用的的是把用户角色保存在内存中，数据是写死的，当然数据可以从数据库中查出再写入内存中；
            2.随后定义的三个用户，没有用户定义了其用户名，密码和角色
            3.Security5默认要求密码使用加密，不加密的话就使用"{noop}123456"这样的写法，加密的话需要使用
                PasswordEncoder的实现类进行加密
         */


    }


}
