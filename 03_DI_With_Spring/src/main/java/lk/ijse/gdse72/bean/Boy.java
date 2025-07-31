package lk.ijse.gdse72.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {

//Property Injection ....
   /* Girl girl = new Girl();

    public void chatWithGirl(){
        girl.chat();
    } */

//Constructor Through Injection
    /*Girl girl;

    public Boy(Girl girl){
        this.girl = girl;
    }

    public void chatWithGirl(){
        Boy boy = new Boy(new Girl());
        boy.chat();
    }*/

//Setter Method Through Injection

    /*Girl girl;

    public void setterMethod(Girl girl){
        this.girl = girl;
    }

    public void chatWithGirl(){
        Boy boy = new Boy();
        boy.setterMethod(new Girl());
        boy.chat();
    }*/

    @Autowired
    @Qualifier("girl1")
    Agreement girl ;

    public Boy(){
        System.out.println("Boy Constructor ...!");
    }

    public void chatWithGirl(){

        girl.chat();
    }
}
