package lk.ijse.gdse72.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;

@RequestMapping("one")
@RestController
public class WildCardMappingController {

    //one/test/hello        ->  not matching
    //one/hello/            ->  not matching
    //one/123/hello         ->  not matching
    //one/test/123/hello    ->    matching
    //one/test/1234567asdasdadasdad/hello       ->  matching

    @GetMapping("test/*/hello")
    public String sayHello(){
        return "Hello World ..?";
    }

    @GetMapping("hello/{id}/ijse")
    public String sayHelloPostIjse(@PathVariable String id){
        return "Hello Ijse" + id;
    }

//    one/hello/ sddkkokmkadsm /askjkjnkjxnkkLmlkMlxLxmKMxZxxxklNxZX

    @GetMapping("hello/*/*")
    public String sayHelloPost(){
        return "Hello World 2";
    }
}
