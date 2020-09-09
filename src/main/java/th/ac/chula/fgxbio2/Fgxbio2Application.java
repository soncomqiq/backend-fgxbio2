package th.ac.chula.fgxbio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import th.ac.chula.fgxbio2.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class Fgxbio2Application {

	public static void main(String[] args) {
		SpringApplication.run(Fgxbio2Application.class, args);
	}

}
