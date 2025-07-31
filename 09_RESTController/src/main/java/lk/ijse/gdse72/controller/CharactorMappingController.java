package lk.ijse.gdse72.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/char")
public class CharactorMappingController {

    //IP12
    //I123

    @GetMapping(path = "item/I???")
    public String sayHello(){
        return "Hello World Item";
    }

    //1234/search
    //abcd/search

    @GetMapping(path = "????/search")
    public String sayHelloGet(){
        return "Hello World Search";
    }
}