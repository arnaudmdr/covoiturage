<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Covoiturage</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

		<c:if test="${not empty demandeErreur}">
		    ${demandeErreur}
		</c:if>
		
		<c:if test="${not empty reservationEffectuee}">
		    ${reservationEffectuee}
		</c:if>
		
		<p>
			<h1>Bonjour ${username}</h1>
			
			<form method="post"> 
				<button type="submit" name="todo" value="deconnexion">Déconnexion</button>
			</form>
		</p>
		


	
		<form method="post" class="formDemandes"> 
			<h3>Liste de demandes</h3>
			<ul>
				<c:forEach items="${listeTrajetsConducteur}" var="t">
						<c:forEach items="${t.demandes}" var="d">
							<li>
								${t.villeDepart.nom} -> ${t.villeArrivee.nom} </br>
								
								${d.utilisateur.username} : ${d.villeArrivee.nom} : ${d.placesReservees}
								
								<p>
									<button type="submit" name="accepter" value="${d.id}">Accepter</button>
									<button type="submit" name="refuser" value="${d.id}">Refuser</button>
								</p>
								
							</li>		
						</c:forEach>
				</c:forEach>
			</ul>
		</form>
	
	<h3>Liste des trajets où je conduis</h3>
	<ul>
		<c:forEach items="${listeTrajetsConducteur}" var="t">
				
			<li>
				${t.villeDepart.nom} -> ${t.villeArrivee.nom} </br>	
				Départ : Le ${t.date.date}/${t.date.month} à ${t.date.hours}h${t.date.minutes} </br>
				Etapes : </br>
				<c:forEach items="${t.etapes}" var="entry">
	    			- ${entry.key.nom}<br>
				</c:forEach>	
				Nombre de places restantes : ${t.nombrePlaces} </br>	
				</br>					
			</li>		
		</c:forEach>
	</ul>
	
	<h3>Liste des trajets où je suis passager</h3>
	<ul>
	
		<c:forEach items="${listeTrajetsPassager}" var="entry">
			<li>
				${entry.value.villeDepart.nom} -> ${entry.key.villeArrivee.nom} </br>
				Départ : Le ${entry.value.date.date}/${entry.value.date.month} à ${entry.value.date.hours}h${entry.value.date.minutes} </br>
				Nombre de places reservées : ${entry.key.placesReservees} </br>
				Tarif une place pour ${entry.key.villeArrivee.nom} : ${entry.value.etapes[entry.key.villeArrivee]}€ </br>
				Tarif total : ${entry.key.placesReservees*entry.value.etapes[entry.key.villeArrivee]}€ </br>
				Conducteur : ${entry.value.conducteur.username}</br>
				</br>
	    	</li>	
		</c:forEach>
		
	</ul>
	
	<form method="post"> 
	<h1>Liste des trajets disponibles</h1>
	<ul>
		<c:forEach items="${listeTrajets}" var="t">
			<li> 
				<h4> <img src="car.svg" alt="Car"> ${t.villeDepart.nom} -> ${t.villeArrivee.nom}</h4>
				<B> Tarif :  </B> ${t.tarif}€ </br>
				<B>Nombre de places restantes : </B> ${t.nombrePlaces} </br>
				<B>Départ : </B> Le ${t.date.date}/${t.date.month} à ${t.date.hours}h${t.date.minutes} </br>
				<B>Etapes : </B> </br>
				<c:forEach items="${t.etapes}" var="entry">
		   			- ${entry.key.nom}, Tarif : ${entry.value}€<br>
				</c:forEach>
				<B>Conducteur : </B> ${t.conducteur.username} </br>
				<B>Type de voiture :  </B> ${t.typeVoiture} </br>		
			</li>
			</br>
			<button type="submit" name="reserver" value="${t.id}">Réserver le trajet</button>
			</br>
		</c:forEach>
	</ul>
	</form>
	
	<form method="post"> 
		<button type="submit" name="todo" value="proposer">Proposer un trajet</button>
	</form>



</body>
</html>