package com.fans.fansrobot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * spring上下文工具类
 *
 * @author fans
 * @date 2021/12/31
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        log.info("初始化:{},通过该工具类可以实现获取其他组件", this.getClass().getSimpleName());
        SpringContextUtils.applicationContext = context;
    }

    /**
     * 获取指定名称对象
     *
     * @param name bean名称
     * @return bean对象
     */
    public static Object getBean(String name) {
        try {
            return applicationContext.getBean(name);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 获取指定类型，指定名称对象
     *
     * @param name  bean名称
     * @param requiredType  bean类对象
     * @param <T>   泛型
     * @return  bean对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        try {
            return applicationContext.getBean(name, requiredType);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 根据类型获取注册的bean
     *
     * @param requiredType bean类对象
     * @param <T>  泛型
     * @return  bean对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        try {
            return applicationContext.getBean(requiredType);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 是否存在指定名称的bean
     *
     * @param name bean名称
     * @return boolean
     */
    public static boolean containsBean(String name) {
        try {
            return applicationContext.containsBean(name);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    /**
     * 是否是单例模式
     *
     * @param name bean名称
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 根据bean名称获取bean类对象
     * @param name bean名称
     * @return class
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 移除bean定义
     * @param name bean名称
     */
    public static void removeBeanDefinition(String name) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) autowireCapableBeanFactory;
        defaultListableBeanFactory.removeBeanDefinition(name);
    }

    /**
     * 注册bean
     *
     * @param name bean名称
     * @param beanXmlDef bean的xml片段
     * @return boolean
     */
    public static boolean registerBean(String name, String beanXmlDef) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                + "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
                + "       \">";
        if (StringUtils.isEmpty(beanXmlDef)) {
            log.info("Bean的定义不能为空");
        }
        xml = xml + beanXmlDef;
        xml += "</beans>";
        XmlBeanFactory factory = new XmlBeanFactory(new ByteArrayResource(xml.getBytes(StandardCharsets.UTF_8)));
        try {
            if (containsBean(name)) {
                beanFactory.removeBeanDefinition(name);
            }
        } catch (NoSuchBeanDefinitionException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        try {
            beanFactory.registerBeanDefinition(name, factory.getMergedBeanDefinition(name));
            Object obj = applicationContext.getBean(name);
            log.info("注册bean:{}:成功", name);
            return true;
        } catch (Exception e) {
            log.error("注册bean:{}:失败", name);
            try {
                beanFactory.removeBeanDefinition(name);
            } catch (Exception e1) {
                log.error(ExceptionUtils.getStackTrace(e1));
            }
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }
}
