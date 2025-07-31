package lk.ijse.gdse72.config;

import lk.ijse.gdse72.bean.A;
import lk.ijse.gdse72.bean.B;
import lk.ijse.gdse72.bean.SpringBeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({AppConfig2.class})
//@ComponentScan("lk.ijse.gdse72.bean")
public class AppConfig1 {

//    @Bean
//    public SpringBeanOne getSpringBeanOne(){
//        return new SpringBeanOne();
//    }

    @Bean
    public A getA(){
        return new A();
    }

    @Bean
    public B getB(){
        return new B();
    }
}
