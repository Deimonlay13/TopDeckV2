package cl.gdl.ms_pokeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPokeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPokeapiApplication.class, args);
	}

}
