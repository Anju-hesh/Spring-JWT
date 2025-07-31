package lk.ijse.gdse72;

import lk.ijse.gdse72.bean.SpringBeanOne;
import lk.ijse.gdse72.bean.SpringBeanTwo;
import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        context.getBean(SpringBeanOne.class);
//        context.getBean(SpringBeanTwo.class);

        context.registerShutdownHook();
    }
}