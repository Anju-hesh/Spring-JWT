package lk.ijse.gdse72;

import lk.ijse.gdse72.bean.MyConnection;
import lk.ijse.gdse72.bean.SpringBean;
import lk.ijse.gdse72.test.TestBean1;
import lk.ijse.gdse72.bean.TestBean2;
import lk.ijse.gdse72.bean.TestBean3;
import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBean springBean1 = context.getBean(SpringBean.class);
        springBean1.test();
        System.out.println("SpringBean instance: " + springBean1);
//
//        SpringBean springBean2 = context.getBean(SpringBean.class);
//        springBean2.test();
//        System.out.println("SpringBean instance: " + springBean2);

//        TestBean1 testBean1 = context.getBean(TestBean1.class);
//        System.out.println("TestBean1 instance: " + testBean1);

        TestBean2 testBean2 = context.getBean(TestBean2.class);
        System.out.println("TestBean2 instance: " + testBean2);

//        context.close();

//        TestBean3 testBean3 = context.getBean(TestBean3.class);
//        System.out.println("TestBean3 instance: " + testBean3);

        // Using bean Id to get the bean instance
//        TestBean3 twstBean3 = (TestBean3) context.getBean("testBean3");
//        System.out.println("TestBean3 instance: " + twstBean3);

        // Using bean name and Id to get the bean instance
        TestBean3 testBean3 = context.getBean("testBean3", TestBean3.class);
        System.out.println("TestBean3 instance: " + testBean3);

        //usingClassName to get the bean instance
        MyConnection myConnection = context.getBean(MyConnection.class);
        System.out.println("MyConnection instance: " + myConnection);

        // Using beanId and ClassName to get the bean instance
        MyConnection myConnection2 = context.getBean("Anjana", MyConnection.class);
        System.out.println("MyConnection instance: " + myConnection2);

        // Using beanId to get the bean instance
        MyConnection myConnection3 = (MyConnection) context.getBean("Anjana");
        System.out.println("MyConnection instance: " + myConnection3);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("J V M is about to shutting down...");
//            context.close();
//        }));

        context.registerShutdownHook();
    }
}