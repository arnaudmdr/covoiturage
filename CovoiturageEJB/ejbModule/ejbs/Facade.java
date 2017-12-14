package ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Trajet;
import entities.Ville;

/**
 * Session Bean implementation class Facade
 */
@Stateless
@LocalBean
public class Facade {
	
		@PersistenceContext(unitName="monUnite")
		EntityManager em;
		
		//Recup liste trajets
		public List<Trajet> getListeTrajets() {
			Query q = em.createQuery("From Trajet t");			
			return q.getResultList();
		}
		
		public void addTrajet(String villedepart, String villearrivee, int nombreplaces, String typevoiture, int tarif) {
			
			// ordre pour addTrajet : 
			// Ville villedepart, Ville villearrivee, int id, int nombreplaces, String typevoiture, int tarif
			
			//On récupère l'id max pour pouvoir ajouter un Trajet avec un id différent 
			Query q0 = em.createQuery("SELECT max(id) FROM Trajet");
			int max = (int)q0.getSingleResult();
			
			// On récupère l'objet Ville dans la base de données qui correspond au nom de
			// la ville de depart
			Query q1 = em.createQuery("From Ville v where v.nom=:nomvilledepart");
			q1.setParameter("nomvilledepart", villedepart);
			Ville vd = (Ville) q1.getSingleResult();
			
			// Pareil pour ville d'arrivée
			Query q2 = em.createQuery("From Ville v where v.nom=:nomvillearrivee");
			q2.setParameter("nomvillearrivee", villearrivee);
			Ville va = (Ville) q2.getSingleResult();
			
			//On ajoute dans la base
			Trajet t = new Trajet(vd, va, max+1, nombreplaces, typevoiture, tarif);
			Trajet trajet_attach = em.merge(t); //Il faut synchroniser dans la base l'entity détachée
			
		}
}
