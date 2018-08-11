package com.starter.template.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.any())              
		          .paths(PathSelectors.any())                          
		          .build();  
    }
	
	private ApiInfo metaData() {
        return new ApiInfo(
                "Spring Hibernate Integration",
                "Spring Hibernate Integration",
                "1.0",
                "",
                new Contact("Raghu", "", "raghukumar10.m@gmail.com"),
               "",
                "", null);
    }
}
