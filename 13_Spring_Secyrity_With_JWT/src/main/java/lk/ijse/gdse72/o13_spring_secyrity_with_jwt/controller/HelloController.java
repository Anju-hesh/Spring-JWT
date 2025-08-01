package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin
public class HelloController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {
        return "Hello, User!";
    }
}
