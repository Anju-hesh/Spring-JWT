package lk.ijse.gdse72.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("lk.ijse.gdse72.bean")
public class AppConfig {

//    @Bean
//    public SpringBeanOne getSpringBeanOne(){
//
////      InterBean Dependency
//
//        SpringBeanTwo springBeanTwo1 = getSpringBeanTwo();
//        SpringBeanTwo springBeanTwo2 = getSpringBeanTwo();
//
//        System.out.println("SpringBeanTwo 1: " + springBeanTwo1);
//        System.out.println("SpringBeanTwo 2: " + springBeanTwo2);
//
//        return new SpringBeanOne();
//    }
//
//    @Bean
//    public SpringBeanTwo getSpringBeanTwo(){
//        return new SpringBeanTwo();
//    }
}
