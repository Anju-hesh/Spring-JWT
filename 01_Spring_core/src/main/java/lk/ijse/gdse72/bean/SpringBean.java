package lk.ijse.gdse72.bean;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {

    public SpringBean(){
        System.out.println("SpringBean instance created");
    }

    public void test(){
        System.out.println("SpringBean test method called");
    }
}
