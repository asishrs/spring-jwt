package org.yagna.samples.jwt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by asish on 3/28/16.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.yagna.samples.jwt")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private Logger LOG = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOG.info("Adding Resource **************************");
        System.out.println("Adding Resource **************************");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/spring-jwt").setViewName("index");
        registry.addViewController("/spring-jwt/test").setViewName("/resources/test.html");
    }



//    @Bean
//    public View jsonTemplate() {
//        MappingJackson2JsonView view = new MappingJackson2JsonView();
//        view.setPrettyPrint(true);
//        return view;
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/app/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping mappping = new RequestMappingHandlerMapping();
//        mappping.setUseSuffixPatternMatch(false);
//        return mappping;
//    }
}
