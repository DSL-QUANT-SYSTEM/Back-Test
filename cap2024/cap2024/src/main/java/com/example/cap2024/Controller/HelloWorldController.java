package com.example.cap2024.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloWorldController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/hello")
    public String test(){
        return "Hello, world!";
    }
}
