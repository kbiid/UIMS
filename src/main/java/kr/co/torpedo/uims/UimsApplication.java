package kr.co.torpedo.uims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("application.properties")
public class UimsApplication{
	public static void main(String[] args) {
		SpringApplication.run(UimsApplication.class, args);
	}
}
