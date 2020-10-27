package eshop;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class EShop {

	
	
	public static void main(String[] args) {
		
	 
		System.out.println("STARTING MENU");
		System.out.println("Cosa vuoi fare? Digita: ");
		System.out.println("1   se vuoi inserire categorie e prodotti, modificare prodotti o cancellare prodotti nello shop.");
		System.out.println("2   se vuoi inserire utenti nello shop");
		System.out.println("3   se vuoi prendere un carrello");
		System.out.println("0   se vuoi uscire");
		Scanner reader = new Scanner(System.in);
		Integer i = reader.nextInt();
		while(i != 0){
			
			switch(i){
			
			case 1:
				inserisciCategorieEProdotti();
				break;
				
			case 2:
				inserisciUtenti();
				break;
				
			case 3:
				Integer id = (int)Math.random() * 1000000;
				prendiCarrello(id);
				break;
				
				
			default:
				System.out.println("Hai sbagliato ad inserire. Riprova");
			}
			
			System.out.println("STARTING MENU");
			System.out.println("Cosa vuoi fare? Digita: ");
			System.out.println("1   se vuoi inserire categorie e prodotti, modificare prodotti o cancellare prodotti nello shop.");
			System.out.println("2   se vuoi inserire utenti nello shop");
			System.out.println("3   se vuoi prendere un carrello");
			System.out.println("0   se vuoi uscire");
			reader = new Scanner(System.in);
			i = reader.nextInt();			
	
		}
		
	}		
		
		
		
		
		
        

	public static void stampaElencoProdotti(){
	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		System.out.println("Elenco dei Prodotti");
		String queryStr = "SELECT p FROM Prodotto p";
	    Query query = em.createQuery(queryStr);
		List object = query.getResultList();
		for(Object obj : object){
			
			Prodotto prodotto = (Prodotto) obj;
			prodotto.stampaProdotto();
			System.out.println("------------------------------");
		}
		
		em.close();
		emf.close();
		
	}
	
    public static void stampaElencoUtenti(){
	
	    
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		EntityManager em = emf.createEntityManager();
		System.out.println("Elenco degli Utenti: ");
		String queryStr = "SELECT u FROM Utente u";
	    Query query = em.createQuery(queryStr);
		List object = query.getResultList();
		for(Object obj : object){
			
			Utente utente = (Utente) obj;
			utente.stampaUtente();
			System.out.println("------------------------------");
		}
		
		em.close();
		emf.close();
		
	}
   
   /* funzione per inserire categoria e prodotti. La logica è quella di inserire per ogni singola categoria, tutti i prodotti associati.
    * Qui si possono anche modificare, cancellare e stampare i prodotti.*/
    
   public static void inserisciCategorieEProdotti(){
	   
	   Scanner reader = new Scanner(System.in);
	   Scanner reader1 = new Scanner(System.in);
	   System.out.println("Qui puoi inserire nello shop le categorie e i prodotti associati.");
	   System.out.println("Digita 1 se vuoi inserire, 0 se vuoi passare oltre.");
	   Integer s = reader.nextInt();
	   //ciclo while per le categorie
	   while(s == 1){
		   
		   System.out.println("Digita l'id della categoria (2 caratteri)");
		   String s1 = reader1.nextLine();
		   reader.nextLine();
		   System.out.println("Digita il nome della categoria");
		   String s2 = reader1.nextLine();
		   Categoria c = new Categoria(s1, s2);
		   c.aggiungiCategoria();
		   System.out.println("Inserisci i prodotti associati a questa categoria.");
		   int i =1;
		   int j =1;
		   String id, descrizione, nome;
		   Integer prezzo;
		   // ciclo while per i prodotti
		   while(j == 1){
			   
			   
			   System.out.println("Digita il codice del " + i + "° prodotto (5 caratteri)");
			   id = reader.nextLine();
		       System.out.println("Digita la descrizione del prodotto");
		       descrizione = reader.nextLine();
		       System.out.println("Digita il nome del prodotto");
		       nome = reader1.nextLine();
		       System.out.println("Digita il prezzo del prodotto (intero)");
		       prezzo = reader1.nextInt();
		       Prodotto p = new Prodotto(id, c, descrizione, nome, prezzo);
		       p.aggiungiProdotto();
		       System.out.println("Se vuoi inserire altri prodotti associati a questa categoria digita 1, altrimenti 0");
		       j = reader1.nextInt();
		       reader1.nextLine();
		       i++;
		     }
		   
		   System.out.println("Digita 1 vuoi inserire un altra categoria, oppure 0 se hai finito");
		   s = reader.nextInt();
	   }
	   
	   System.out.println("Vuoi stampare i prodotti nel DB? Digita 1, altrimenti digita 0");
	   int i = reader.nextInt();
	   if(i == 1){
		   
		   stampaElencoProdotti();
	   }
	   
	   System.out.println("Vuoi modificare qualche prodotto? Digita 1, altrimenti digita 0");
	   i = reader.nextInt();
	   if(i == 1){
		   
		   EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		   EntityManager em = emf.createEntityManager();
		   EntityTransaction et = em.getTransaction();
		   et.begin();
		   String id;
		   while(i == 1){
			   
			   System.out.println("inserisci l' id del prodotto da modificare (5 caratteri)");
			   id = reader1.nextLine();
			   System.out.println("id prodotto: " + id);
			   Prodotto prodotto = em.find(Prodotto.class, id);
			   System.out.println("nome prodotto: " + prodotto.getNome());
			   prodotto.modificaProdotto();
			   em.persist(prodotto);
			   System.out.println("Vuoi modificare qualcos'altro? Digita 1, altrimenti digita 0");
			   i = reader1.nextInt();
		   }
		   et.commit();
		   em.close();
		   emf.close();
	   }
	   
	   System.out.println("Vuoi cancellare qualche prodotto? Digita 1, altrimenti digita 0");
	   i = reader.nextInt();
       if(i == 1){
		   
    	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
		   EntityManager em = emf.createEntityManager();
		   EntityTransaction et = em.getTransaction();
		   et.begin();
    	   String s1;
		   while(i == 1){
			   
			   System.out.println("inserisci l' id del prodotto da cancellare (5 caratteri)");
			   s1 = reader1.nextLine();
			   reader1.nextLine();
			   Prodotto prodotto = em.find(Prodotto.class, s1);
			   prodotto.rimuoviProdotto(em, et);
			   //em.remove(prodotto);
			   System.out.println("Vuoi modificare qualcos'altro? Digita 1, altrimenti digita 0");
			   i = reader1.nextInt();
		   }
		   et.commit();
		   em.close();
		   emf.close();
	   }
   }
   
   //questa funzione consente di inserire gli utenti nel DB e di cancellarli
   
  public static void inserisciUtenti(){
	  
	  Scanner reader = new Scanner(System.in);
	  Scanner reader1 = new Scanner(System.in);
	  Scanner reader2 = new Scanner(System.in);
	  System.out.println("Qui puoi inserire gli utenti.");
	  System.out.println("Digita 1 se vuoi inserire, 0 quando hai finito.");
	  int s = reader.nextInt();
	  String s1, s2;
	  while(s == 1){
		  
		  System.out.println("Digita l'username dell'utente");
		  s1 = reader1.nextLine();
		  System.out.println("Digita il nome dell'utente");
		  s2 = reader2.nextLine();
		  Utente u = new Utente(s1, s2);
		  u.aggiungiUtente();
		  System.out.println("Digita 1 se voui inserire, 0 quando hai finito.");
		  s = reader.nextInt();
	  }
	  
	  System.out.println("Vuoi stampare gli utenti nel DB? Digita 1, altrimenti digita 0");
	  int i = reader.nextInt();
	  if(i == 1){
		   
		   stampaElencoUtenti();
	   }
	  
	  System.out.println("Vuoi eliminare qualche utente? Digita 1, altrimenti digita 0");
	  i = reader.nextInt();
      if(i == 1){
		   
    	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
  		   EntityManager em = emf.createEntityManager();
  		   EntityTransaction et = em.getTransaction();
  		   et.begin();
  		   String username;
		   while(i == 1){
			   
			   
			   System.out.println("inserisci l' username dell'utente da eliminare");
			   username = reader1.nextLine();
			   reader.nextLine();
			   Utente utente = em.find(Utente.class, username);
			   em.remove(utente);
			   System.out.println("Vuoi eliminare qualche altro utente? Digita 1, altrimenti digita 0");
			   i = reader.nextInt();
		   }
		   et.commit();
		   em.close();
		   emf.close();
	   }
	  
  }
  
    //funzione per prendere un carrello. Consente di inserirci i prodotti, di toglierli successivamente e di acquistare successivamente.
  
    public static void prendiCarrello(Integer id){
    	
    	Scanner reader = new Scanner(System.in);
    	System.out.println("inserisci l'username e il nome dell'utente");
		System.out.println("username");
	    String username = reader.nextLine();
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShop");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction et = em.getTransaction();
    	et.begin();
    	Utente utente = em.find(Utente.class, username);
    	Carrello carrello  = new Carrello(id, utente);
    	System.out.println("nome utente: "+ utente.getNome());
    	em.persist(utente);
    	String s = "si";
    	while(!s.equals("no")){
    	   System.out.println("inserisci l'id del prodotto da mettere nel carrello (5 caratteri):");
    	   String idProdotto = reader.nextLine();
    	   Prodotto prodotto = em.find(Prodotto.class, idProdotto);
    	   carrello.aggiungiProdottoCarrello(prodotto, em, et);
    	   em.persist(prodotto);
    	   System.out.println("vuoi inserire altro? (si/no)");
    	   s = reader.nextLine();
    	   
    	}
    	
    	System.out.println("vuoi togliere qualcosa dal carrello prima dell'acquisto? (si/no)");
    	s = reader.nextLine();
    	if(s.equals("si")){
    		
    		while(!s.equals("no")){
    	    	   System.out.println("inserisci l'id del prodotto da togliere nel carrello (5 caratteri):");
    	    	   String idProdotto = reader.nextLine();
    	    	   Prodotto prodotto = em.find(Prodotto.class, idProdotto);
    	    	   carrello.rimuoviProdottoCarrello(prodotto, em, et);
    	    	   em.persist(prodotto);
    	    	   System.out.println("vuoi togliere altro? (si/no)");
    	    	   s = reader.nextLine();
    	    	   
    	    	}
    		
    	}
    	et.commit();
    	em.close();
    	emf.close();
    	
    	System.out.println("Vuoi stampare i prodotti nel carrello? (si/no)");
    	s = reader.nextLine();
    	if(s.equals("si")){
    		
    		carrello.stampaProdottiCarrello();
    	}
    	
    	System.out.println("procediamo con l'acquisto dei prodotti nel carrello");
    	carrello.acquisto();
  	
    }
    
    
    
}
