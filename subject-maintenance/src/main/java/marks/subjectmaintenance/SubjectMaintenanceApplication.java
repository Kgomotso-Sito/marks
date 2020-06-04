package marks.subjectmaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SubjectMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectMaintenanceApplication.class, args);
	}

}
