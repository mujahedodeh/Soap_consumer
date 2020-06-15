package com.example.demo;
import com.example.consumingwebservice.wsdl.Country;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CountryController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/country")
    public Country country(@RequestParam(value = "name", defaultValue = "Spain") String name) {
        return new Country();
    }


    @PostMapping("/post")
    public Country greeting(@RequestBody Country country) {
        ConsumingWebServiceApplication.bool = true;
        ConsumingWebServiceApplication.posted = country.getName();
        System.out.println( ConsumingWebServiceApplication.posted + ConsumingWebServiceApplication.bool);
        return country;
    }
}