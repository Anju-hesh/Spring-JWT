package lk.ijse.gdse72.config;

import lk.ijse.gdse72.bean.A;
import lk.ijse.gdse72.bean.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
