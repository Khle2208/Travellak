package com.anhkhoa.travellak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravellakApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravellakApplication.class, args);
    }
}
