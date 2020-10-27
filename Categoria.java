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
public class Categoria implements Serializable {

	private static final long serialVersionUID = 9099761317508065071L;
  
	@Id
	@Column(unique = true)
	private String id;
	
	@Column(nullable = false)
	private String categoria;
	
	@Column(name = "n_prodotti", nullable = false)
	private Integer nProdotti;
	
	
	public Categoria(){}
	
	public Categoria(String id, String categoria){
		
		this.id = id;
		this.categoria = categoria;
		this.nProdotti = 0;
	}
	
	public void setId(String id){
		
		this.id = id;
	}
	
	public String getId(){
		
		return id;
	}
	
   public void setCategoria(String categoria){
		
		this.categoria = categoria;
	}
	
	public String getCategoria(){
		
		return categoria;
	}
	
	public void setNProdotti(Integer nProdotti){
		
		this.nProdotti = nProdotti;
	}
	
	public Integer getNProdotti(){
		
		return nProdotti;
	}
	
	
	public void stampaCategoria(){
		
		System.out.println(" Categoria: " + categoria);
	}
	
	// aggiunge una categoria al database
	public void aggiungiCategoria(){
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Categoria categoria = new Categoria();
		categoria = this;	
		em.persist(categoria);
		et.commit();	
		em.close();
		emf.close();
					
		}

	 //rimuove categoria dal database
	 public void rimuoviCategoria(){
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Categoria categoria = em.find(Categoria.class, this.getId());
		em.remove(categoria);
		et.commit();
		em.close();
		emf.close();
			
		}
	
	
}
