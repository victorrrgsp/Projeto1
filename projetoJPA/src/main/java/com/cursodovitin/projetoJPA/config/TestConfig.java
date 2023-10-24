package com.cursodovitin.projetoJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursodovitin.projetoJPA.entities.Category;
import com.cursodovitin.projetoJPA.entities.Order;
import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.entities.enums.OrderStatus;
import com.cursodovitin.projetoJPA.repositories.CategoryRepository;
import com.cursodovitin.projetoJPA.repositories.OrderRepository;
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

    // isso acessa o bamco de dados
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    // tudo que voce colocar nesse metodo vai ser executado quando o programa começar
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,u2); 
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1); 

        Category cat1 = new Category(null, "Electronics"); 
        Category cat2 = new Category(null, "Books"); 
        Category cat3 = new Category(null, "Computers");

        // o saveAll pegar uma lista de objetos e salva no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    }
}
