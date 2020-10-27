package eshop;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table
public class Utente implements Serializable {

	private static final long serialVersionUID = -5818085412466411641L;

	@Id
	private String username;
	
	@Column
	private String nome;
	
	
	public Utente(){}
	
	public Utente(String username, String nome){
		
		this.username = username;
		this.nome = nome;
	}
	
	public void setUsername(String username){
		
		this.username = username;
	}
	
	public String getUsername(){
		
		return username;
	}
	
    public void setNome(String nome){
		
		this.nome = nome;
	}
	
	public String getNome(){
		
		return nome;
	}
	
	public void stampaUtente(){
		
		System.out.println("Username: " + username + " Nome: " + nome );
	}
	
	/*questo metodo consente di aggiungere un utente nel database*/
    public void aggiungiUtente(){
		
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Utente utente = new Utente();
		utente = this;	
		em.persist(utente);
		et.commit();	
		em.close();
		emf.close();
				
	}
   
	
	
}
