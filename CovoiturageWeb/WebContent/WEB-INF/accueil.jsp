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

<form method="post"> 
	<h1>Liste des trajets disponibles</h1>
	<ul>
		<c:forEach items="${listeTrajets}" var="t">
			<li> ${t.villeDepart.nom} -> ${t.villeArrivee.nom} tarif : ${t.tarif} </br>
			Nombre de places restantes : ${t.nombrePlaces}		
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