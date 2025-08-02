package lk.ijse.gdse72;

import lk.ijse.gdse72.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        Access System Environment Variables
//        Map<String,String> getenv = System.getenv();
//        for (String key : getenv.keySet()){
//            System.out.println(key + ": " + getenv.get(key));
//        }
//
//        System.out.println("==================================================================================");
//
////        Access Java Environment Variables
//        Properties properties = System.getProperties();
//        for (String key : properties.stringPropertyNames()){
//            System.out.println(key + " = " + properties.getProperty(key));
//        }
//
//        System.out.println("=================================================================================");
//        String osName = System.getProperty("os.name");
//        System.out.println(osName);

//        String dbName = System.getProperty("db.name");
//        System.out.println(dbName);

        context.registerShutdownHook();
    }
}