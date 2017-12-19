<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>

<form method="post">
	Username : <input type="text" name="username"/>
	Password : <input type="text" name="password"/>
	<button type="submit" name="todo" value="connect">Se connecter</button>
</form>

<h1>Liste des trajets disponibles</h1>
<ul>
	<c:forEach items="${listeTrajetAnonyme}" var="t">
		<li> ${t.villeDepart.nom} -> ${t.villeArrivee.nom} tarif : ${t.tarif}€ </br>
		Nombre de places restantes : ${t.nombrePlaces} </br>
		Départ : Le ${t.date.date}/${t.date.month} à ${t.date.hours}h${t.date.minutes} </br>
		Etapes : </br>
		<c:forEach items="${t.etapes}" var="entry">
   			- ${entry.key.nom}, Tarif : ${entry.value}€<br>
		</c:forEach>
		Conducteur : ${t.conducteur.username} </br>
		Type de voiture : ${t.typeVoiture} </br>		
		</li>
		</br>
	</c:forEach>
</ul>



</body>
</html>