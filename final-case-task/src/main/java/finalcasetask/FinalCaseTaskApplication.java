package finalcasetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinalCaseTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalCaseTaskApplication.class, args);
	}

}
