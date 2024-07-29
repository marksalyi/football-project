package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: implement new column to database specially match table with a result column
// this column should be home, draw or loss based on the match scores
// run new sql script include result column (update match table)
// update Match class with field result (enumerated to string)
// matchservice method check
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
