package lk.ijse.gdse72.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class SpringBeanOne implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

    public SpringBeanOne(){
        System.out.println("SpringBeanOne - Constructor");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Bean Factory Aware is done ...!  - SprinBeanOne" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware Is Done: - SprinBeanOne" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean Is Destroyed ...! - SprinBeanOne");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Successfull Initialized - SpringBeanOne ...!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Context Awware Is Done ..! - SprinBeanOne");
    }
}
