package ejbs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Reservation;
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
			List<Trajet> listeNonSorted = q.getResultList();
			
			if (listeNonSorted.size()>1) {
				listeNonSorted.sort(new Comparator<Trajet>(){
					@Override
					public int compare(Trajet t1, Trajet t2) {
						return t1.getDate().compareTo(t2.getDate()) < 0 ? -1 : (t1.getDate().compareTo(t2.getDate())) > 0 ? 1 : 0;
					}
				});
			}
			return listeNonSorted; 
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
			return q.getResultList();
		}
		

		//Fonction pour proposer un nouveau trajet
		public void addTrajet(String username, String villedepart, String villearrivee, int nombreplaces, String typevoiture, int tarif, Map<String, Integer> etapes, int jour, int mois, int heure, int minutes) {
			
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
			Trajet t = new Trajet(u, vd, va, max+1, nombreplaces, typevoiture, tarif, jour, mois, heure, minutes);
			t.setEtapes(etapesVilles);
			
			Trajet trajet_attach = em.merge(t); //Il faut synchroniser dans la base l'entity détachée
			
		}
		
		public Trajet getTrajet(int trajetId) {
			Query q = em.createQuery("From Trajet t where t.id=:trajetId");
			q.setParameter("trajetId", trajetId);	
			return (Trajet) q.getSingleResult();	
		}
		
		public boolean reserverTrajet(int idDemande) {
			
			//Récupération de la demande :
			Query q = em.createQuery("From Reservation r where r.id=:id");
			q.setParameter("id", idDemande);	
			Reservation r = (Reservation) q.getSingleResult();
			
			Trajet t = r.getTrajet();
			
			//Modification nb places
			int nPlaces = t.getNombrePlaces();
			
			//Erreur on veut reserver + de places qu'il y en a
			if (nPlaces < r.getPlacesReservees()) {
				return false;
			} else {
				//Nv nombre de places
				t.setNombrePlaces(nPlaces-r.getPlacesReservees());
				
				//On remove la reservation de la liste des demandes
				List<Reservation> demandes = t.getDemandes();
				demandes.remove(r);
				t.setDemandes(demandes);
				
				//Mise a jour de la HM des passagers
				List<Reservation> reservations = t.getReservations();
				reservations.add(r);
				t.setReservations(reservations);
				
				return true;
			}		
		}
		
		public boolean envoyerDemande(String username, int trajetId, int nombrePlaces, String villeArrivee) {
			Trajet t = getTrajet(trajetId);
			
			//User
			Utilisateur user = em.find(Utilisateur.class, username);
					
			//Ville arrivée
			Query q2 = em.createQuery("From Ville v where v.nom=:nomvillearrivee");
			q2.setParameter("nomvillearrivee", villeArrivee);
			Ville va = (Ville) q2.getSingleResult();
			
			//Modification nb places
			int nPlaces = t.getNombrePlaces();
			
			//Erreur on veut reserver + de places qu'il y en a
			if (nPlaces < nombrePlaces) {
				return false;
			} else {	
				//Mise a jour de la HM des passagers
				List<Reservation> demandes = t.getDemandes();
				
				//Creation de reservation correspondant au passager et sauv ds la base
				Reservation r = new Reservation(user,nombrePlaces,va);	
				
				r.setTrajet(t);
				
				Reservation rAttach = em.merge(r);			
				
				demandes.add(rAttach);
				
				t.setDemandes(demandes);										
				return true;
			}		
		}
		
		public void refuserDemande(int idDemande) {
			
			Query q = em.createQuery("From Reservation r where r.id=:id");
			q.setParameter("id", idDemande);	
			Reservation r = (Reservation) q.getSingleResult();
			
			Trajet t = r.getTrajet();
			
			//On remove la reservation de la liste des demandes
			List<Reservation> demandes = t.getDemandes();
			demandes.remove(r);
			t.setDemandes(demandes);
		}
		
		
		
		public List<Ville> getEtapes(int trajetId) {
			Trajet t = getTrajet(trajetId);
			Map<Ville,Integer> etapes = t.getEtapes();
			
			ArrayList<Ville> listeEtapes = new ArrayList<Ville>(etapes.keySet());
			listeEtapes.add(t.getVilleArrivee());
			return listeEtapes;		
		}
		
		//Liste des trajets en tant que conducteur
		public List<Trajet> getTrajetsConducteur(String username) {
			
			//User
			Utilisateur conducteur = em.find(Utilisateur.class, username);
			//Trajets en tant que conducteur
			Query q = em.createQuery("From Trajet t where t.conducteur=:conducteur");
			q.setParameter("conducteur", conducteur);
			return q.getResultList();			
		}
		
		
}
