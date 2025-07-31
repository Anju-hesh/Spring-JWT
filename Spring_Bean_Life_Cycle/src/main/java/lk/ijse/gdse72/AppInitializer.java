package lk.ijse.gdse72;

import lk.ijse.gdse72.bean.MyConnection;
import lk.ijse.gdse72.bean.SpringBean;
import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        SpringBean springBean1 = context.getBean(SpringBean.class);
//        System.out.println("SpringBean1: " + springBean1);

//        SpringBean springBean2 = context.getBean(SpringBean.class);
//        System.out.println("SpringBean2: " + springBean2);

//        MyConnection myConnection1 = context.getBean(MyConnection.class);
//        System.out.println("My Connection 1: " + myConnection1);

//        MyConnection myConnection2 = context.getBean(MyConnection.class);
//        System.out.println("My Connection 2: " + myConnection2);

        context.registerShutdownHook();
    }
}
