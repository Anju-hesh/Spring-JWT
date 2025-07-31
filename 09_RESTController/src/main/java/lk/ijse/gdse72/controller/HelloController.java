package lk.ijse.gdse72.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    public HelloController(){
        System.out.println("Controller Hello Controller");
    }

    @GetMapping("two")
    public String sayHello2(){
        return "Hello ..! GET 2";
    }

    @GetMapping("two/three")
    public String sayHello3(){
        return "Hello ..! GET 3";
    }

    @GetMapping
    public String sayHello(){
        return "Hello ..! GET";
    }

    @PostMapping
    public String sayHelloPost(){
        return "Hello ..! POST";
    }

    @DeleteMapping
    public String sayHelloDelete(){
        return "Hello ..! DELETE";
    }

    @PutMapping
    public String sayHelloPut(){
        return "Hello ..! PUT";
    }

    @PatchMapping
    public String sayHelloPatch(){
        return "Hello ..! PATCH";
    }

    //Meka pennaha
    @RequestMapping(method = RequestMethod.HEAD)
    public String sayHelloHead(){
        return "Hello ..! HEAD";
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public String sayHelloOptions(){
        return "Hello ..! OPTIONS";
    }
}
