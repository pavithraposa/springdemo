package com.example.springdemo01;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeter {

    @RequestMapping(value = "/api/greeting/{name}", method = RequestMethod.GET)
    public String getGreeting(@PathVariable("name") String name) {
        return "Hej " + name + ".";
    }
}
