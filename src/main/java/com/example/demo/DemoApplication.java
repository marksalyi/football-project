package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// TODO: implement new column to database specially match table with a result column
// this column should be home, draw or loss based on the match scores
// run new sql script include result column (update match table)
// update Match class with field result (enumerated to string)
// matchservice method check
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo.configurator", "com.example.demo.dao"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
