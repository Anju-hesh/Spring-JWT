package lk.ijse.gdse72;

import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInnitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(AppConfig1.class);
//        context.register(AppConfig2.class);
        context.register(AppConfig.class);
        context.refresh();

//        SpringBeanOne springBeanOne1 = context.getBean(SpringBeanOne.class);
//        SpringBeanOne springBeanOne2 = context.getBean(SpringBeanOne.class);
//        System.out.println("Spring Bean1: " + springBeanOne1);
//        System.out.println("Spring Bean2: " + springBeanOne2);
//
//        SpringBeanTwo springBeanTwo1 = context.getBean(SpringBeanTwo.class);
//        SpringBeanTwo springBeanTwo2 = context.getBean(SpringBeanTwo.class);
//        System.out.println("Spring Bean2: " + springBeanTwo1);
//        System.out.println("Spring Bean1: " + springBeanTwo2);



        context.registerShutdownHook();
    }
}
