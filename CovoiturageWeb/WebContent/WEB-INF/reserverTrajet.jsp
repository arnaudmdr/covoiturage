<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Réserver trajet</title>
</head>
<body>

<form method="post"> 
	${trajet.villeDepart.nom} -> ${trajet.villeArrivee.nom} tarif : ${trajet.tarif} </br>
	Nombre de places restantes : ${trajet.nombrePlaces}	</br>
	
	<p>
	Sélectionnez une ville d'arrivée :
	<select name="villearrivee">
	   	<c:forEach items="${listeEtapes}" var="e">
			<option> ${e.nom} </option>
		</c:forEach>
	</select> 
	</p>
		
	Saisissez un nombre de places : <input type="number" name="${trajet.id}"/>
	<button type="submit" name="validerReservation" value="${trajet.id}">Valider réservation</button>
</form>

</body>
</html>