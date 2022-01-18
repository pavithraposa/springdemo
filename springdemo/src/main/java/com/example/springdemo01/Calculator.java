package com.example.springdemo01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Calculator {
    int number;

    public Calculator() {
        number = 0;
    }
    // Import to be able to write @RequestMapping further down
// Requires import via Maven (pom.xml)

    // En metod som är kopplad till Spring att lyssna på anrop till angiven adress med HTTP-metoden POST
    @RequestMapping(value = "/api/calculator/add_one", method = RequestMethod.POST)
    public void addOne() {

        number++;
    }

    @RequestMapping(value = "/api/calculator/get_number", method = RequestMethod.GET)
    public int getNumber() {
        return number;
    }

    // Om vi inte anger en metod, vad händer då..? Testa. Tror du denna fungerar?
    @RequestMapping(value = "/api/calculator/get_numbers")
    public List<Integer> getNumbers() {
        return List.of(number, number);
    }


}
