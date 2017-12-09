package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ville {
	
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
