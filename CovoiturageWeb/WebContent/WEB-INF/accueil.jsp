<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Covoiturage</title>
</head>
<body>


<c:if test="${not empty demandeErreur}">
    ${demandeErreur}
</c:if>

<c:if test="${not empty reservationEffectuee}">
    ${reservationEffectuee}
</c:if>


<form method="post"> 
	<h1>Liste de demandes</h1>
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


<form method="post"> 
	<h1>Liste des trajets disponibles</h1>
	<ul>
		<c:forEach items="${listeTrajets}" var="t">
			<li> ${t.villeDepart.nom} -> ${t.villeArrivee.nom} tarif : ${t.tarif} </br>
			Nombre de places restantes : ${t.nombrePlaces}	
			Date de départ : Le ${t.date.date}/${t.date.month} à ${t.date.hours}h${t.date.minutes}
			</li>
			<button type="submit" name="reserver" value="${t.id}">Réserver le trajet</button>
		</c:forEach>
	</ul>
</form>
<form method="post"> 
	<button type="submit" name="todo" value="proposer">Proposer un trajet</button>
</form>



</body>
</html>