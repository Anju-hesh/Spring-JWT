package lk.ijse.gdse72.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

    //The State Of The Object Creation
    public MyConnection(){
        System.out.println("My Connection Constructor ...");       // 1.Instansiation
    }

    //There Is No Method To Find State Of The Properties              2.Populate Aware


    //Bean Name Aware
    @Override
    public void setBeanName(String name) {
        System.out.println("My Connection setBeanName Called ...!");            //3.Bean Name Aware
    }

    //Bean Factory Aware  (Dependency Injection)
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("My Connection setBeanFactory Called ...!");             //4.Bean Factory Aware
    }

    //AplicationContext Aware (AOP & DP)
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("My Connection setApplicationContext Called ...!");      //5.ApplicationContextAware
    }

    //Initializing Bean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("My Connection afterPropertiesSet ..!");                //6.Initialization
    }

    //Disposable Bean
    @Override
    public void destroy() throws Exception {
        System.out.println("My Connection Is Destroyed ...");                     //7.Disposabale Bean
    }
}
