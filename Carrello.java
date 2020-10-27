package eshop;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Carrello {
    
	private Integer id;
	private Utente utente;
	private TreeSet<Prodotto> elenco;   //struttura dati che ho pensato ideale per il carrello
	private Map <String, Prodotto> mappa;


	
	public Carrello(Integer id, Utente utente){
		
		this.id = id;
		this.utente = utente;
		this.elenco = new TreeSet<Prodotto>();
		this.mappa = new HashMap<>();
	}
	
	
	public TreeSet<Prodotto> getElenco(){
		
		return elenco;
	}
	
	/*metodo per aggiungere prodotto al carrello. Non aggiorna la tabella prodotti nel DB rispetto ai inseriti (lo fa il metodo acquisto() più in basso, 
	 * ma aggiorna la tabella Acquisti rispetto ai prodotti inseriti nel carrello.*/
	
	public void aggiungiProdottoCarrello(Prodotto p, EntityManager em, EntityTransaction et){
		
		mappa.put(p.getId(), p);
		boolean b = elenco.add(p);
		
		Acquisto acquisto = new Acquisto();
		acquisto.setIdCarrello(this.id);
		acquisto.setUtente(this.utente);  
		acquisto.setProdotto(p);
		em.persist(acquisto);
		p.setIdAcquisto(acquisto.getId());
			
	}
	
	/* rimuove il prodotto dal carrello aggiornando la tabella Acquisti*/
	
	public void rimuoviProdottoCarrello(Prodotto p, EntityManager em, EntityTransaction et){
		
		boolean b = elenco.remove(p);
		mappa.remove(p.getId());

		Acquisto acquisto = new Acquisto();
		acquisto = em.find(Acquisto.class, p.getIdAcquisto());
		em.remove(acquisto);
		
	}
	
	public Prodotto getProduct(String key){
		
		return mappa.get(key);
	}
	
	public void stampaProdottiCarrello(){
		
		for(Prodotto prodotto : elenco){
			
			prodotto.stampaProdotto();
			System.out.println("--------------------");
		}
	}
	
	 /*acuisto i prodotti inseriti nel carrello. I prodotti acquistati vengono cancellati dal DB. Cancello inoltre la categoria se non rimangono 
	  * più prodotti associati*/
	
	 public void acquisto(){
			
		    
			for(Prodotto prodotto : elenco){
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
				EntityManager em = emf.createEntityManager();
				EntityTransaction et = em.getTransaction();
				et.begin();
				Prodotto p = em.find(Prodotto.class, prodotto.getId());
				Categoria categoria = em.find(Categoria.class, prodotto.getCategoria().getId());
				categoria.setNProdotti(categoria.getNProdotti() - 1);
				prodotto.getCategoria().setNProdotti(prodotto.getCategoria().getNProdotti() - 1);
				if(categoria.getNProdotti() == 0){
					em.remove(p);
				    em.remove(categoria);
				    }
				else{
					em.remove(p);
					em.persist(categoria);
				}
				et.commit();
				em.close();
				emf.close();
				
			}
			
			System.out.println("acquisto effettuato");
			
		}
	
	
}
