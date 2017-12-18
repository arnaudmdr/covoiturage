package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	private int placesReservees;
	
	@ManyToOne
	private Ville villeArrivee;
	
	
	
	
	public Reservation() {
		
	}
	
	
	public Reservation(Utilisateur utilisateur, int placesReservees, Ville villeArrivee) {
		this.utilisateur = utilisateur;
		this.placesReservees = placesReservees;
		this.villeArrivee = villeArrivee;
	}


	public int getId() {
		return id;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getPlacesReservees() {
		return placesReservees;
	}
	public void setPlacesReservees(int placesReservees) {
		this.placesReservees = placesReservees;
	}
	public Ville getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	
}
