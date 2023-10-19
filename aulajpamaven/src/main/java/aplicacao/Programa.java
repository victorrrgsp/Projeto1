package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		// instacia o EntityManagerFactory com as configuração q está nos arquivos de configuração
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		// vai ter uma conecção com o banco de dados 
		EntityManager em = emf.createEntityManager();
		
		// a função find busca uma objeto pelo id q estar no banco
		Pessoa p = em.find(Pessoa.class, 2);
		System.out.println(p);

		Pessoa p1 = em.find(Pessoa.class, 2);

		// isso vai iniciar uma trazação com o banco de dados
		// quando uma operação não é uma leitura do banco ele necessita de uma trazação
		em.getTransaction().begin();
		em.remove(p1);
		// isso confirma as auterações q eu fiz
		em.getTransaction().commit();
		
		System.out.println("Pronto!");
		// estar fechando o EntityManager
		em.close();
		// estar fechando o EntityManagerFactory
		emf.close();
	}
}
