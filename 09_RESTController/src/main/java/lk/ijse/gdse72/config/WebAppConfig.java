package lk.ijse.gdse72.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("lk.ijse.gdse72.controller")
@EnableWebMvc
public class WebAppConfig {

}
