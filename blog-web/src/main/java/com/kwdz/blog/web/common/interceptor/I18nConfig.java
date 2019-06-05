package com.kwdz.blog.web.common.interceptor;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * 国际化配置
 *
 * @author YT.Hu
 */
@Configuration
public class I18nConfig {

    @Bean
    public LocaleResolver localeResolver() {
        MyLocaleResolver myLocaleResolver = new MyLocaleResolver();
        myLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
     /*   CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("localeCookie"); // 将语言信息添加到Cookie中
        //设置默认区域
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE); // 默认简化汉语
        localeResolver.setCookieMaxAge(86400);//设置cookie有效期.24小时
        return localeResolver;*/
        return myLocaleResolver;

    }
}