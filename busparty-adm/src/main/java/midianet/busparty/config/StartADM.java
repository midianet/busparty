package midianet.busparty.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("midianet.busparty")
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@PropertySource("application.properties")
public class StartADM {

	public static void main(String[] args) {
		SpringApplication.run(StartADM.class, args);
	}

}