package lk.ijse.gdse72.bean;

import org.springframework.stereotype.Component;

@Component
//@Primary
//@Scope("prototype")
public class Girl1 implements Agreement{

    public Girl1(){
        System.out.println("Girl Constructor ...!");
    }

    public void chat(){
        System.out.println("Still Do You Want to chat ...?");
    }
}
