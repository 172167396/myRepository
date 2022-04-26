package com.dailu.nettyclient.utils;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationContextHolder implements ApplicationContextAware, InitializingBean {
    public static ApplicationContext applicationContext;

    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        if (ApplicationContextHolder.applicationContext == null) {
            ApplicationContextHolder.applicationContext = applicationContext;
        }
    }

    public static <T> Optional<T> getBean(Class<T> tClass){
        return Optional.of(applicationContext.getBean(tClass));
    }

    public static ObjectMapper getObjectMapper(){
        return getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("applicationContextHolder初始化......");
    }
}