package com.cursodovitin.projetoJPA.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.repositories.UserRepository;

// o configuration diz q essa classe é um configuração especifica para o perfil de teste
@Configuration
// o spring consege indentificar que ele vai rodar essa aplicação somente quando voce estiver noi perfil de teste
@Profile("test")

/* essa classe vai serve para o nosso Databese seeding(popular o nosso banco)
 * tem que ter uma dependencia pro meu UserRepository que é a classe que salvaos dados no banco
 * onde terá o primeiro caso de injeção de dependencia
 */
public class TestConfig implements CommandLineRunner{

    // o Autowired consegue associar um instacia no TestConfig
    @Autowired
    private UserRepository userRepository;

    @Override
    // tudo que voce colocar nesse metodo vai ser executado quando o programa começar
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        // o saveAll pegar uma lista de objetos e salva no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
