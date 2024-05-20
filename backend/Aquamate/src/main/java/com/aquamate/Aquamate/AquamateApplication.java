package com.aquamate.Aquamate;

import com.aquamate.Aquamate.principal.Principal;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AquamateApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private DadosUsuarioRepository dadosUsarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(AquamateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(userRepository, dadosUsarioRepository);
		principal.testePrincipal();
	}
}
