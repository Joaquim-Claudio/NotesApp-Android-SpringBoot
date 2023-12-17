package pt.iade.joaquimclaudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.iade.joaquimclaudio.server.models.repositories.PersonRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		PersonRepository.populate();
	}

}
