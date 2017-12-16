package ejbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Trajet;
import entities.Utilisateur;
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
		
		
		public boolean Connexion(String username, String password) {
			Utilisateur user = em.find(Utilisateur.class, username);
			if(user!=null && (user.getPassword().equals(password))) {
				return true;
			}
			return false;
		}
		
		public List<Ville> getVilles() {
			Query q = em.createQuery("From Ville v");
			System.out.println(q.getResultList());
			return q.getResultList();
		}
		

		//Fonction pour proposer un nouveau trajet
		public void addTrajet(String username, String villedepart, String villearrivee, int nombreplaces, String typevoiture, int tarif, Map<String, Integer> etapes) {
			
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
			
			//On récupère l'utilisateur qui correspond au username
			Query q3 = em.createQuery("From Utilisateur u where u.username=:username");
			q3.setParameter("username", username);
			Utilisateur u = (Utilisateur) q3.getSingleResult();
			
			Map<Ville, Integer> etapesVilles = new HashMap<Ville, Integer>();
			for (String etapeVille : etapes.keySet()) {
				Query q4 = em.createQuery("From Ville v where v.nom=:nomville");
				q4.setParameter("nomville", etapeVille);
				Ville v = (Ville) q4.getSingleResult();
				etapesVilles.put(v, etapes.get(etapeVille));
			}
			
			//On ajoute dans la base
			Trajet t = new Trajet(u, vd, va, max+1, nombreplaces, typevoiture, tarif);
			t.setEtapes(etapesVilles);
			
			Trajet trajet_attach = em.merge(t); //Il faut synchroniser dans la base l'entity détachée
			
		}
}
