package lk.ijse.gdse72.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@RequiredArgsConstructor
@Component
public class Test2 {

// Property Injection
   @Autowired
    DI test1 ;

    public Test2(){
        System.out.println(" Pareekshana Anka 2 ... Constructor Called ...!");
    }

    public void calledHelloMethod() {
        test1.sayHello();
    }


//Constructor Throug Injection
  /*  DI test1;

    public Test2(DI t1){
        this.test1 = t1;
    }

    public void calledHelloMethod(){
        test1.sayHello();
    }  */


//Setter Method Through Injection
/*    DI test1;

    @Autowired
    public void setTest1(DI t1){
        this.test1 = t1;
    }

    public void calledHelloMethod(){
        test1.sayHello();
    }     */


//Interface Through Injection
    /*DI test1;

    @Autowired
    @Override
    public void inject(DI t1) {
        this.test1 = t1;
    }

    public void calledHelloMethod(){
        test1.sayHello();
    }*/
}
