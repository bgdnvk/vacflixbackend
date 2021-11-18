package com.vacflix.backend.vacflixbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VacflixbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacflixbackendApplication.class, args);
    }
    @GetMapping("/restcall")
    public String testcall(@RequestParam(value = "value1", defaultValue = "default value") String randomVar){
        return String.format("sup %s", randomVar);
    }

}
