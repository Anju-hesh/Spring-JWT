package lk.ijse.gdse72.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("lk.ijse.gdse72.bean")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
