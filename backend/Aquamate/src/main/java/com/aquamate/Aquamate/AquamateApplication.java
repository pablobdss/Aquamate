package com.aquamate.Aquamate;

import com.aquamate.Aquamate.principal.Principal;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AquamateApplication{

	public static void main(String[] args) {
		SpringApplication.run(AquamateApplication.class, args);
	}
}
