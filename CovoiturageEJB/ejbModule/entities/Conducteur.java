package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Conducteur extends Utilisateur {
	
	//Plus tard il y aura une liste de demandes
	
	@OneToMany (mappedBy="conducteur")
	private List<Trajet> trajetsConducteur;

	public List<Trajet> getTrajetsConducteur() {
		return trajetsConducteur;
	}

	public void setTrajetsConducteur(List<Trajet> trajetsConducteur) {
		this.trajetsConducteur = trajetsConducteur;
	}
	
	
}
