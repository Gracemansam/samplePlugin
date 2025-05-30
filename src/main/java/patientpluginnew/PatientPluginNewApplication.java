package patientpluginnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"patientpluginnew"})
@EnableJpaRepositories(basePackages = {"patientpluginnew"})

public class PatientPluginNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientPluginNewApplication.class, args);
    }

}
