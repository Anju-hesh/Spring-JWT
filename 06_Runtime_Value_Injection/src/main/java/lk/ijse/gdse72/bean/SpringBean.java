package lk.ijse.gdse72.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {
    @Value("Anjana Heshan")
    private String name;

    public SpringBean(){
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Name: " + name );
    }

//    @Autowired(required = false)
//    public SpringBean(@Value("Anjana Heshan") String name , @Value("21") int number ) {
//        System.out.println("Spring Bean Constructor: " + name);
//        System.out.println("Spring Bean Constructor: " + number);
//    }
//
//    @Autowired(required = false)
//    public SpringBean(@Value("Anjana Heshan") String name , @Value("true") boolean isCurrect) {
//        System.out.println("Spring Bean Constructor: " + name);
////        System.out.println("Spring Bean Constructor: " + number);
//        System.out.println("Spring Bean Constructor: " + isCurrect);
//    }


}
