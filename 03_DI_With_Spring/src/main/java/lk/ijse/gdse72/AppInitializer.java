package lk.ijse.gdse72;

import lk.ijse.gdse72.bean.Boy;
import lk.ijse.gdse72.bean.SpringBean;
import lk.ijse.gdse72.config.AppConfig;
import lk.ijse.gdse72.di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        Boy boy = (Boy) context.getBean("boy");
//        boy.chatWithGirl();

        Test2 test2 = (Test2) context.getBean("test2");
        test2.calledHelloMethod();

        context.registerShutdownHook();
    }
}
