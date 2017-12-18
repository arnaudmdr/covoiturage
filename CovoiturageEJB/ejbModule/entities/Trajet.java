package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.internal.Nullable;

@Entity
public class Trajet {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private int nombrePlaces;
	
	@ManyToOne
	private Utilisateur conducteur;
	
	private String typeVoiture;
	
	@OneToOne
	private Ville villeDepart;
	
	@OneToMany
	@JoinColumn(nullable=true)
	private List<Reservation> reservations;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Reservation> demandes;
	
	//HashMap Ville -> Tarif
	@ElementCollection
	@CollectionTable(name="ETAPES", joinColumns=@JoinColumn(name="TRAJET_ID"))
	@Column(name="TARIF")
	@MapKeyJoinColumn(name="VILLE_ID")
	private Map<Ville, Integer> etapes = new HashMap<>();
	
	@OneToOne
	private Ville villeArrivee;
	
	// Pourquoi on fait pas etape juste comme ça ? 
	// ArrayList<Ville> etapes;
	// Map<Ville, Integer> hm = new HashMap<>();
	
	private int tarif;
	
	public Trajet() {
		
	}
	
	public Trajet(Utilisateur conducteur, Ville villedepart, Ville villearrivee, int id, int nombreplaces, String typevoiture, int tarif) {
		this.villeDepart = villedepart;
		this.villeArrivee = villearrivee;
		this.id = id;
		this.tarif = tarif;
		this.typeVoiture = typevoiture;
		this.nombrePlaces = nombreplaces;
		this.conducteur = conducteur;
	}
	
	
	
	//##############################################
	//			Getters and Setters
	//##############################################

	
	
	public Utilisateur getConducteur() {
		return conducteur;
	}
	

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setConducteur(Utilisateur conducteur) {
		this.conducteur = conducteur;
	}

	public Map<Ville, Integer> getEtapes() {
		return etapes;
	}

	public void setEtapes(Map<Ville, Integer> etapes) {
		this.etapes = etapes;
	}

	public int getId() {
		return id;
	}

	public List<Reservation> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<Reservation> demandes) {
		this.demandes = demandes;
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
