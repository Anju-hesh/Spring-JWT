package lk.ijse.gdse72.config;

import lk.ijse.gdse72.bean.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({AppConfig1.class , AppConfig2.class})
//If Config File In The Root Folder
//@ImportResource("classpath:hibernate.cfg.xml")

//If Not In The Root
//@ImportResource("file:absolute-path-of-hibernate.cfg.xml")
public class AppConfig {

    @Bean
    public SpringBean getSpringBean(){
        return new SpringBean();
    }
}
