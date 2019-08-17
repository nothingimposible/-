package com.qst.studyingcourse.config;


import com.qst.studyingcourse.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
          public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/index.html").setViewName("main");
                registry.addViewController("/video/upvideo").setViewName("upview");
            }

            public void addInterceptors(InterceptorRegistry registry){
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/course/main","/user/login","/user/register","/jsoup");
            }
        };

        return adapter;
    }

}
