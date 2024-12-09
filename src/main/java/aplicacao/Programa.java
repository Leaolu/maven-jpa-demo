package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		
		//automatically creates the connection with the data bank
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		//starts the transaction process
		//every time an alteration is going to be done in the data bank, it's mandatory to open a transaction
		em.getTransaction().begin();
		//search for an item in the data bank and transforms it into an object
		Pessoa p = em.find(Pessoa.class, 2);
		//create an object and adds it to the data bank, automatically transforming it to spreadsheet
		Pessoa p1 = new Pessoa(null, "George", "George@gmail.com");
		em.persist(p1);
		//find an object with the id 2 and deletes it from the data bank
		//the object has to be monitored to be removed:
		//1. the object has just been added 
		//2. the object has just been searched for
		Pessoa p2 = em.find(Pessoa.class, 2);
		em.remove(p2);
		
		System.out.println(p);
		
		//save all the changes and update them all at once
		//close transaction
		em.getTransaction().commit();
		System.out.println("Pronto");
		em.close();
		emf.close();
	}

}
