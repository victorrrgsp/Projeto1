package com.cursodovitin.projetoJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursodovitin.projetoJPA.entities.Category;
import com.cursodovitin.projetoJPA.entities.Order;
import com.cursodovitin.projetoJPA.entities.OrderItem;
import com.cursodovitin.projetoJPA.entities.Payment;
import com.cursodovitin.projetoJPA.entities.Product;
import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.entities.enums.OrderStatus;
import com.cursodovitin.projetoJPA.repositories.CategoryRepository;
import com.cursodovitin.projetoJPA.repositories.OrderItemRepository;
import com.cursodovitin.projetoJPA.repositories.OrderRepository;
import com.cursodovitin.projetoJPA.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    // tudo que voce colocar nesse metodo vai ser executado quando o programa começar
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics"); 
        Category cat2 = new Category(null, "Books"); 
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "T rd of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // isso é uma associação dizendo que o product p1 dentro da coleção de categoria q é a cat2
        p1.getCategories().add(cat2);
        // isso é um paradigima orientados a objetos
        // ai para salva no banco de dados tera que salva no paradigima relacional
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,u2); 
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1); 

        // o saveAll pegar uma lista de objetos e salva no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
        
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        /* para salva um objeto dependete de um para um
         * voce não vai chamar o prepository do proprio objeto
         * onde terá que fazer a associação de mão dupla em memoria
         */
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
