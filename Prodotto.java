package eshop;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Prodotto implements Serializable, Comparable<Prodotto>{

	private static final long serialVersionUID = 7395126315934703356L;

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	@Column
	private String descrizione;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Integer prezzo;
	
	@Transient
	private Integer idAcquisto;
	
	public Prodotto(){}
	
	public Prodotto(String id, Categoria categoria, String descrizione, String nome, Integer prezzo){
		
		this.id = id;
		this.categoria = categoria;
		this.descrizione = descrizione;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public void setId(String id){
		
		this.id = id;
	}
	
	public String getId(){
		
		return id;
	}
	
    public void setCategoria(Categoria categoria){
		
		this.categoria = categoria;
	}
	
	public Categoria getCategoria(){
		
		return categoria;
	}
	
   public void setDescrizione(String descrizione){
		
		this.descrizione = descrizione;
	}
	
	public String getDescrizione(){
		
		return descrizione;
	}
	
   public void setNome(String nome){
		
		this.nome = nome;
	}
	
	public String getNome(){
		
		return nome;
	}
	
   public void setPrezzo(Integer prezzo){
		
		this.prezzo = prezzo;
	}
	
	public Integer getPrezzo(){
		
		return prezzo;
	}
	
	public void setIdAcquisto(Integer idAcquisto){
		
		this.idAcquisto = idAcquisto;
	}
	
	@Transient
	public Integer getIdAcquisto(){
		
		return idAcquisto;
	}
	
	public void stampaProdotto(){
		
		System.out.print("Prodotto: " + nome + " ");
		System.out.println("Categoria: "+ categoria.getCategoria());
	}
	
	//aggiunge prodotto al database
    public void aggiungiProdotto(){
		
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Prodotto prodotto = new Prodotto();
		prodotto = this;
		this.categoria.setNProdotti(this.categoria.getNProdotti() + 1);
		Categoria c = em.find(Categoria.class, this.getCategoria().getId());
		c.setNProdotti(c.getNProdotti() + 1);
		em.persist(c);
		em.persist(prodotto);
		et.commit();
		em.close();
		emf.close();
				
	}
   
    
   
   /*Metodo per rimuovere il prodotto dal database. Diminuisce di una unita il numero di 
    * prodotti della relativa categoria, e la cancella nel caso non ci sia più nessun prodotto associato.*/
   
   
   public void rimuoviProdotto(EntityManager em, EntityTransaction et){
		
		Categoria categoria = em.find(Categoria.class,  this.getCategoria().getId());
		categoria.setNProdotti(categoria.getNProdotti() - 1);
		em.remove(this);
		if(categoria.getNProdotti() == 0){
			em.remove(categoria);
		}
		else{
			em.persist(categoria);
			}
	}
   
  
   
   //Modifica il prodotto this nel DB. prima si accerta che il prodotto sia presente.
   
   public void modificaProdotto(){
	   	
	   	
	   	Scanner reader = new Scanner(System.in);
	   	if(this == null){
	   		
	   		System.out.println("il prodotto non esiste");
	   	}
	   	else{
	   
	   	    System.out.println("Nome attuale: " + this.getNome() + ". Prezzo attuale: " + this.getPrezzo());
	   	    System.out.println("Puoi modificare il nome o il prezzo del prodotto");
	      	System.out.println("Vuoi modificare il nome? (si/no)");
	   	    String s = reader.nextLine();
	   	    if(s.equals("si")){
	   		    System.out.println("inserisci il nome: ");
	   		    s = reader.nextLine();
	   		    this.setNome(s);
	      	}
	   	   System.out.println("Vuoi modificare il prezzo? (si/no)");
	   	   s = reader.nextLine();
	   	   if(s.equals("si")){
	   		   System.out.println("inserisci il prezzo: ");
	           Integer i = reader.nextInt();
	   		   this.setPrezzo(i);
	   		}
	    
	      }
	   	
	 }
	
	/*implemento il metodo compareTo per fare in modo che nel carrello i prodotti vengano inseriti in ordine alfabetico 
      rispetto alle categorie di appartenenza*/
   
	public int compareTo(Prodotto p){
		
		String[] s1 = new String[2];
		s1[0] = this.categoria.getCategoria();
		s1[1] = p.categoria.getCategoria();
		
		if(s1[0].equals(s1[1])&&this.nome.equals(p.getNome())&&this.prezzo == p.getPrezzo()) return 0;
		else{
		    String[] s2 = new String[2];
		    s2 = s1.clone();
		    Arrays.sort(s2);
		    if(s1[0] != s2[0]) return 1;
		    else{ return -1;}
		    }
	}
	
	
	
	
	
	
	
	
}
