package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.Facade;
import entities.Trajet;
import entities.Ville;


@WebServlet("/CovoiturageServlet")
public class CovoiturageServlet extends HttpServlet {
	
	@EJB 
	private Facade facade;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Afficher toutes les offres de covoiturage	
		String todo = request.getParameter("todo");
		
		//Bouton reserver un trajet
		String reserver = request.getParameter("reserver");
		
		//Bouton valider reservation
		String validerReservation = request.getParameter("validerReservation");
		
		String currentLogin = (String)request.getSession().getAttribute("username");
		if (currentLogin==null || currentLogin=="") {
			if ((todo!=null) && (todo.equals("connect"))) {
				//test connexion
				String login=(String)request.getParameter("username");
				String password=(String)request.getParameter("password");
				
				if (facade.Connexion(login, password)) {
					
					if (login.equals("admin")) {
						request.getSession().setAttribute("username", login);
						//si admin, on dispatch sur la jsp correspondante
						generateAdmin(request, response);
						return;
					}else {
						request.getSession().setAttribute("username", login);
						generateListeTrajets(request, response, login);
						return;
					}
				}
			}
			System.out.println("Je passe");
			List<Trajet> test = facade.getListeTrajets();
			System.out.println(test);
			//sinon on renvoie sur la page de connexion
			request.setAttribute("listeTrajetAnonyme", facade.getListeTrajets());
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			return;
		}
		
		
		//*******************************************************************************
		//Cas où on est admin
		String deconnexion = request.getParameter("deconnexion");
			
		if (deconnexion!=null) {
			request.getSession().setAttribute("username", "");
			request.setAttribute("listeTrajetAnonyme", facade.getListeTrajets());
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
		
		String action = request.getParameter("admin");
		if (action!=null) {
			switch (action) {				
			case "ajouterville":
				//On peut ajouter des villes 
				if (request.getParameter("newville")!=null)
				{
					String newville = request.getParameter("newville");
					facade.addVille(newville);
					generateAdmin(request, response);
				}
				break;
			case "ajoutergabarit":
				if (request.getParameter("newgabarit")!=null)
				{
					//On peut aussi ajouter des nouveaux gabarits de véhicules
					String newgabarit = request.getParameter("newgabarit");
					facade.addGabarit(newgabarit);
					generateAdmin(request, response);
					
				}
				break;
			default:
				break;
			}	
			return;
		}
		
		//*******************************************************************************
		
		
		String todotrajet = request.getParameter("todotrajet");
		
		//On regarde les cas où on se trouve sur la première page, accueil.jsp
		if (todo!=null) {
			switch (todo) {
			case "proposer":
				request.setAttribute("listeVilles", facade.getVilles());
				request.setAttribute("listeTypeVehicule", facade.getListeGabarits());
				//On change de jsp et on passe sur la page proposer un trajet
				request.getRequestDispatcher("/WEB-INF/propositionTrajet.jsp").forward(request, response);
				break;
			case "deconnexion":
				request.getSession().setAttribute("username", "");
				request.setAttribute("listeTrajetAnonyme", facade.getListeTrajets());
				request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
				break;	
				
			default:
				break;
			}
			
			return;
		}
		
		//Passage a la page de reservation d'un trajet
		if (reserver!=null) {
			int idTrajet = Integer.parseInt(reserver);
			request.setAttribute("trajet", facade.getTrajet(idTrajet));
			request.setAttribute("listeEtapes", facade.getEtapes(idTrajet));
			request.getRequestDispatcher("/WEB-INF/reserverTrajet.jsp").forward(request, response);
			return;
		}
		
		//Reservation de trajet validée en cliquant
		if (validerReservation!=null) {
			int idTrajet = Integer.parseInt(validerReservation);
			
			//Nombre de places à reserver
			int nombrePlaces = Integer.parseInt(request.getParameter(validerReservation));
			String villeArrivee = request.getParameter("villearrivee");
			
			if (!(facade.envoyerDemande(currentLogin, idTrajet, nombrePlaces, villeArrivee))){
				request.setAttribute("demandeErreur","Reservation impossible : pas assez de place");
			} else {
				request.setAttribute("reservationEffectuee","Demande effectuée");
			}
					
		}

		
		//On regarde les cas où on se trouve sur la page propositionTrajet.jsp
		if (todotrajet!=null) {
			switch (todotrajet) {
			case "valider":
				ajouterTrajet(request, response, currentLogin);
				//On change de jsp et on passe sur la page proposer un trajet
				break;
			default:
				break;
			}
		}
		
		
		//Traitement des demandes (cas acceptation ou refus)
		String accepter = request.getParameter("accepter");
		String refuser = request.getParameter("refuser");
		
		if (accepter!=null) {
			//cas acceptation de la demande
			int idDemande = Integer.parseInt(accepter);
			facade.reserverTrajet(idDemande);
		}
		if (refuser!=null) {
			//cas refus de la demande
			int idDemande = Integer.parseInt(refuser);
			facade.refuserDemande(idDemande);
		}
		
		generateListeTrajets(request, response, currentLogin);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void ajouterTrajet(HttpServletRequest request, HttpServletResponse response, String currentLogin) throws ServletException, IOException {
		
		String[] casesCochees = request.getParameterValues("etape");
		Map<String, Integer> etapes = new HashMap<String, Integer>();
		if (casesCochees!=null) {
			for (String etapeVille : casesCochees) {
				if (!request.getParameter(etapeVille).isEmpty())
					etapes.put(etapeVille, Integer.parseInt(request.getParameter(etapeVille)));
			}
		}
		if ( !request.getParameter("tarif").isEmpty() ) {
			facade.addTrajet(currentLogin, request.getParameter("villedepart"), request.getParameter("villearrivee"),
					Integer.parseInt(request.getParameter("nombreplaces")), request.getParameter("typevoiture"),
					Integer.parseInt(request.getParameter("tarif")), etapes, Integer.parseInt(request.getParameter("jour")),
					Integer.parseInt(request.getParameter("mois")), Integer.parseInt(request.getParameter("heure")),
					Integer.parseInt(request.getParameter("minutes")));
		}
	}
	
	private void generateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeVilles", facade.getVilles());
		request.setAttribute("listeGabarits", facade.getListeGabarits());
		request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}
	
	private void generateListeTrajets(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
		request.setAttribute("listeTrajetsConducteur", facade.getTrajetsConducteur(username));
		request.setAttribute("listeTrajetsPassager", facade.getTrajetsPassager(username));
		request.setAttribute("listeTrajets", facade.getListeTrajets());
		request.setAttribute("username", username);
		request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request, response);
	}

}
