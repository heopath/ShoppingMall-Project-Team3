package kr.co.springkmarketapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.co.springkmarketapp.dao")
public class SpringKmarketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKmarketAppApplication.class, args);
    }
}