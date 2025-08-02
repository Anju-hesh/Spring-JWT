package lk.ijse.gdse72.config;

import lk.ijse.gdse72.bean.C;
import lk.ijse.gdse72.bean.D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {

//    @Bean
//    public SpringBeanTwo getSpringBeanTwo(){
//        return new SpringBeanTwo();
//    }

    @Bean
    public C getC(){
        return new C();
    }

    @Bean
    public D getD(){
        return new D();
    }
}
