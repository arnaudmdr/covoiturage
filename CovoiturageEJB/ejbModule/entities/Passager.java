package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Passager extends Utilisateur {
	
	@OneToMany
	@JoinTable(name="LienPassagerVilleArrivee")
	private List<Ville> villesArrivee;
	
	@ManyToMany(mappedBy="passagers")
	private List<Trajet> trajetsPassager;

	public List<Trajet> getTrajetsPassager() {
		return trajetsPassager;
	}

	public void setTrajetsPassager(List<Trajet> trajetsPassager) {
		this.trajetsPassager = trajetsPassager;
	}

	public List<Ville> getVillesArrivee() {
		return villesArrivee;
	}

	public void setVillesArrivee(List<Ville> villesArrivee) {
		this.villesArrivee = villesArrivee;
	}


}
