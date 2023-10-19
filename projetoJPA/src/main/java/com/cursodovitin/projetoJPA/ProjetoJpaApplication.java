package com.cursodovitin.projetoJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* uma anotacao para configurar esse projeto seja uma aplicação do Spring Boot,
a anotecion vai ser utilizada bastante por que eles configura o nosso codigo 
por de baixo do panos na hora de conpilar o seu codigo fonte, 
entao ela prepara essa class para ser uma aplicacao Spring
*/ 
@SpringBootApplication
public class ProjetoJpaApplication {

	public static void main(String[] args) {
		// comando que roda a aplicação no spring boot
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

}
