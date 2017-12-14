package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.Facade;
import entities.Trajet;

/**
 * Servlet implementation class CovoiturageServlet
 */
@WebServlet("/CovoiturageServlet")
public class CovoiturageServlet extends HttpServlet {
	
	@EJB 
	private Facade facade;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Afficher toutes les offres de covoiturage	
		
		
		String todo = request.getParameter("todo");
		String todotrajet = request.getParameter("todotrajet");
		
		//On regarde les cas où on se trouve sur la première page, accueil.jsp
		if (todo!=null) {
			switch (todo) {
			case "proposer":
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
				facade.addTrajet(request.getParameter("villedepart"), request.getParameter("villearrivee"),
						Integer.parseInt(request.getParameter("nombreplaces")), request.getParameter("typevoiture"),
						Integer.parseInt(request.getParameter("tarif")));
				//On change de jsp et on passe sur la page proposer un trajet
				break;
			default:
				break;
			}
		}
		
		
		request.setAttribute("listeTrajets", facade.getListeTrajets());
		request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
