package com.aquamate.Aquamate;

import com.aquamate.Aquamate.Principal.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AquamateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquamateApplication.class, args);

		// Criar uma instância de Principal e chamar o método TestePrincipal
		Principal principal = new Principal();
		principal.TestePrincipal();
	}
}
