package my.project.remember.me.remember_me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RememberMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RememberMeApplication.class, args);
	}

}
