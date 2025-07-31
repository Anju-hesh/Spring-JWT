package lk.ijse.gdse72.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanThree {

    @Bean
    public SpringBeanOne getSpringBeanOne(){

//      InterBean Dependency not supported
        SpringBeanTwo springBeanTwo1 = getSpringBeanTwo();
        SpringBeanTwo springBeanTwo2 = getSpringBeanTwo();

        System.out.println("SpringBeanTwo 1: " + springBeanTwo1);
        System.out.println("SpringBeanTwo 2: " + springBeanTwo2);

        return new SpringBeanOne();
    }

    @Bean
    public SpringBeanTwo getSpringBeanTwo(){
        return new SpringBeanTwo();
    }
}
