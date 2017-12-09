package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Trajet {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinTable (name="LienConducteurTrajet", 
	joinColumns=@JoinColumn(name="TRAJET_ID"))
	private Utilisateur conducteur;
	
	@ManyToMany
	@JoinTable (name="LienPassagerTrajet")
	private List<Utilisateur> passagers;
	
	@OneToOne
	private Ville villeDepart;
	
	@OneToOne
	private Ville villeArrivee;
	
	private int tarif;
	
	@OneToMany
	@JoinTable (name="LienEtapeTrajet")
	private List<Etape> etapes;
	
	private Date dateDepart;
	
	private Date dateArrivee;
	
	public int getId() {
		return id;
	}
	public Utilisateur getConducteur() {
		return conducteur;
	}
	public void setConducteur(Utilisateur conducteur) {
		this.conducteur = conducteur;
	}
	public List<Utilisateur> getPassagers() {
		return passagers;
	}
	public void setPassagers(List<Utilisateur> passagers) {
		this.passagers = passagers;
	}
	public Ville getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}
	public Ville getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	public int getTarif() {
		return tarif;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
	public List<Etape> getEtapes() {
		return etapes;
	}
	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	public Date getDateArrivee() {
		return dateArrivee;
	}
	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	

	
	

}
