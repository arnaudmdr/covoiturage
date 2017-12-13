package ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Trajet;

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
  

}
