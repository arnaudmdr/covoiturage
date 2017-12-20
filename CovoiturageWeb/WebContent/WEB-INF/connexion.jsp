<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="css/style.css">




</head>
<body>

 <div class="wrapper">
	<div class="container">

	<form method="post" class="form">
		Username : <input type="text" name="username"/>
		Password : <input type="text" name="password"/>
		<button type="submit" id="login-button" name="todo" value="connect">Se connecter</button>
	</form>
	</div>

</div>

<form>
	<h3>Liste des trajets disponibles</h3>
	<ul>
		<c:forEach items="${listeTrajetAnonyme}" var="t">
			<li> <h4> <img src="car.svg" alt="Car">  ${t.villeDepart.nom} -> ${t.villeArrivee.nom}</h4>
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
		</c:forEach>
	</ul>
</form>

</body>
</html>