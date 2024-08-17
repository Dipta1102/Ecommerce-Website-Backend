package com.business.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.business.demo.enc.Employee;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Employee emp1 = new Employee();
		emp1.setEmpID(1);
		emp1.setName("Dipta");
		emp1.setProjectID(11);
		System.out.println(emp1.getEmpID());
		System.out.println(emp1.getName());
		System.out.println(emp1.getProjectID());
	}

}
