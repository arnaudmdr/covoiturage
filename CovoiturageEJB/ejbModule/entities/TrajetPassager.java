package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TrajetPassager {
	
	@Id @GeneratedValue
	private int id;
	private Ville villeArrivee;
	private Trajet trajet;
	
	public int getId() {
		return id;
	}
	public Ville getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	public Trajet getTrajet() {
		return trajet;
	}
	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}
	
	
	

}
