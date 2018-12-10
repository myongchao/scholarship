package com.myc.scholarship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan( value = "com.myc.*.mapper")
@SpringBootApplication
public class ScholarshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScholarshipApplication.class, args);
    }
}
