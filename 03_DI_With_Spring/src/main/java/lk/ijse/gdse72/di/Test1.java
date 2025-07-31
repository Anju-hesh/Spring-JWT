package lk.ijse.gdse72.di;

import org.springframework.stereotype.Component;

@Component
public class Test1 implements DI{

    public Test1(){
        System.out.println(" Pareekshana Anka 1 ... Constructor Called ...!");
    }

    @Override
    public void sayHello() {
        System.out.println(" Pareekshana Anka 1 SayHello ..");
    }
}
