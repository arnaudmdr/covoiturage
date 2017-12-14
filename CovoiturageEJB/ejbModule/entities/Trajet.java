package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private int nombrePlaces;
	
	private String typeVoiture;
	
	@OneToOne
	private Ville villeDepart;
	
	@OneToOne
	private Ville villeArrivee;
	
	// Pourquoi on fait pas etape juste comme ça ? 
	// ArrayList<Ville> etapes;
	// Map<Ville, Integer> hm = new HashMap<>();
	
	private int tarif;
	
	public Trajet() {
		
	}
	
	public Trajet(Ville villedepart, Ville villearrivee, int id, int nombreplaces, String typevoiture, int tarif) {
		this.villeDepart = villedepart;
		this.villeArrivee = villearrivee;
		this.id = id;
		this.tarif = tarif;
		this.typeVoiture = typevoiture;
		this.nombrePlaces = nombreplaces;
	}
	
	
	
	//##############################################
	//			Getters and Setters
	//##############################################

	public int getId() {
		return id;
	}


	public int getNombrePlaces() {
		return nombrePlaces;
	}

	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	public String getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(String typeVoiture) {
		this.typeVoiture = typeVoiture;
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
	
	
	
	

}
