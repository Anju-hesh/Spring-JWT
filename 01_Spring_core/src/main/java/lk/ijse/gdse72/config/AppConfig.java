package lk.ijse.gdse72.config;

import lk.ijse.gdse72.bean.MyConnection;
import lk.ijse.gdse72.test.TestBean1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse72.bean")
//@ComponentScan(basePackageClasses = TestBean1.class)
public class AppConfig {

    @Bean("Anjana")
    MyConnection myConnection(){
        return new MyConnection();
    }
}
