package ar.edu.unnoba.poo2024.allmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ar.edu.unnoba.poo2024.allmusic.util.PasswordEncoder;


@SpringBootApplication
public class DemoApplication {

	@Bean
		public PasswordEncoder createPasswordEncoder(){
			return new PasswordEncoder();
		}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
