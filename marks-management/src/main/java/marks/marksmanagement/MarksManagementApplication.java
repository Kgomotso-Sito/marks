package marks.marksmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MarksManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarksManagementApplication.class, args);
	}

}
