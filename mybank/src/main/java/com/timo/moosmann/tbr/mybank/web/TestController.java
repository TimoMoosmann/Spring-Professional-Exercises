package com.timo.moosmann.tbr.mybank.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String index() {
        return "Hello from TestController";
    }
}
