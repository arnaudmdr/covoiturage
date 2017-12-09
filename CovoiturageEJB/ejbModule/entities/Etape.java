package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Etape {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinTable (name="LienEtapeVille",
			joinColumns=@JoinColumn(name="ETAPE_ID"))
	private Ville ville;
	
	private int tarif;
	
	public int getId() {
		return id;
	}

	public Ville getVille() {
		return ville;
	}
	
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	public int getTarif() {
		return tarif;
	}
	
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
	
	
	

}
