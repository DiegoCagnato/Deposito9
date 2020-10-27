package eshop;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Acquisto implements Serializable {

	private static final long serialVersionUID = 8983686708618167145L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_carrello", nullable = false)
	private Integer idCarrello;
	
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "id_prodotto", nullable = false)
	private Prodotto prodotto;
	
	@Column(name = "data_acquisto", nullable = false)
	private LocalDateTime data;
	
	
	@PrePersist
	public void prePersist(){
		
		data = LocalDateTime.now();
	}
	
	public Acquisto(){}

	public Acquisto(Integer id, Integer idCarrello, Utente utente, Prodotto prodotto){
		
		this.id = id;
		this.idCarrello = idCarrello;
		this.utente = utente;
		this.prodotto = prodotto;
	}
	
	
	
	
	public void setId(Integer id){
		
		this.id = id;
	}
	
	public Integer getId(){
		
		return id;
	}
	
   public void setIdCarrello(Integer idCarrello){
		
		this.idCarrello = idCarrello;
	}
	
	public Integer getIdCarrello(){
		
		return idCarrello;
	}
	
	
    public void setUtente(Utente utente){
		
		this.utente = utente;
	}
	
	public Utente getUtente(){
		
		return utente;
	}
	
    public void setProdotto(Prodotto prodotto){
		
		this.prodotto = prodotto;
	}
	
	public Prodotto getProdotto(){
		
		return prodotto;
	}
	
	
	
	
}
