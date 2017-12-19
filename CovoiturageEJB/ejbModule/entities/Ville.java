package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ville {
	
	@Id
	private String nom;
	
	public Ville() {
	}
	
	public Ville(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
