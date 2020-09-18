package com.grofers.ordercart;

import io.swagger.annotations.Scope;
//import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OrdercartApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrdercartApplication.class, args);
	}

//	@Bean // Want a new obj every time
//	@Scope("prototype")
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

}
