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
		
		String currentLogin = (String)request.getSession().getAttribute("username");
		if (currentLogin==null) {
			if ((todo!=null) && (todo.equals("connect"))) {
				//test connexion
				String login=(String)request.getParameter("username");
				String password=(String)request.getParameter("password");
				
				if (facade.Connexion(login, password)) {
					request.getSession().setAttribute("username", login);
					generateListeTrajets(request, response);
					return;
				}
			}
			
			//sinon on renvoie sur la page de connexion
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
				
			return;
		}
		
		
		
		
		String todotrajet = request.getParameter("todotrajet");
		
		//On regarde les cas où on se trouve sur la première page, accueil.jsp
		if (todo!=null) {
			switch (todo) {
			case "proposer":
				request.setAttribute("listeVilles", facade.getVilles());
				//On change de jsp et on passe sur la page proposer un trajet
				request.getRequestDispatcher("/WEB-INF/propositionTrajet.jsp").forward(request, response);
				break;
			default:
				break;
			}
			
			return;
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
		
		generateListeTrajets(request, response);
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
					Integer.parseInt(request.getParameter("tarif")), etapes);
		}
	}
	
	private void generateListeTrajets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeTrajets", facade.getListeTrajets());
		request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request, response);
	}

}
