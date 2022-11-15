package com.example.cicdexample;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CICDController {
    @GetMapping("/")
    public String helloWorld() {
        return  "Hello world";
    }
}
