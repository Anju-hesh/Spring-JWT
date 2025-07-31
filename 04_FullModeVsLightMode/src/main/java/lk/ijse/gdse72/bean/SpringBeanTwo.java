package lk.ijse.gdse72.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class SpringBeanTwo implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    public SpringBeanTwo(){
        System.out.println("SpringBeanTwo - Constructor");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Bean Factory Aware is done ...!  - SprinBeanTwo" );
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware Is Done: - SprinBeanTwo");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean Is Destroyed ...! - SprinBeanTwo");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Successfull Initialized - SpringBeanTwo ...!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Context Awware Is Done ..! - SprinBeanTwo");
    }
}
