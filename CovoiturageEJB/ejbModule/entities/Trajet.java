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
	
	public enum TypeVehicule {
		  Fourgonnette("Fourgonnette"),
		  SUV("SUV"),
		  Urbaine("Urbaine"),
		  Compacte("Compacte"),
		  Routiere("Routiere"),
		  Break("Break");
		
		private final String text;

	    private TypeVehicule(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}
	
	private int nombrePlaces;
	

	@ManyToOne
	@JoinTable (name="LienConducteurTrajet", 
	joinColumns=@JoinColumn(name="TRAJET_ID"))
	private Conducteur conducteur;
	
	@ManyToMany
	@JoinTable (name="LienPassagerTrajet",
	joinColumns=@JoinColumn(name="trajet"),
	inverseJoinColumns=@JoinColumn(name="passager"))
	private List<Passager> passagers;
	
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
	
	
	public Conducteur getConducteur() {
		return conducteur;
	}


	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}


	public List<Passager> getPassagers() {
		return passagers;
	}


	public void setPassagers(List<Passager> passagers) {
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
	public int getNombrePlaces() {
		return nombrePlaces;
	}
	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	

	
	

}
