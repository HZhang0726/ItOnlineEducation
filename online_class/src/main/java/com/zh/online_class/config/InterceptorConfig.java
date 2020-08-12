package com.zh.online_class.config;

import com.zh.online_class.interceptor.CorsInterceptor;
import com.zh.online_class.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置(相当于spring-MVC中的xml配置文件)
 *
 * 不用权限可以进行访问url  /api/v1/pub/
 * 要登录可以进行访问url   /api/v1/pri/
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean  //在IOC容器中存在一个拦截器实例
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }


    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    /**
     * 配置拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");


        //拦截全部
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
