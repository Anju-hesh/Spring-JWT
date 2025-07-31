package lk.ijse.gdse72;

import lk.ijse.gdse72.bean.SpringBean;
import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBean springBean = context.getBean(SpringBean.class);
        System.out.println("Spring Bean: " + springBean);

        context.registerShutdownHook();
    }
}