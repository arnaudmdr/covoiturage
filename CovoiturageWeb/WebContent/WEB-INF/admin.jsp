<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page de l'administrateur</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<form method="post"> 
	<button type="submit" name="todo" value="deconnexion">Déconnexion</button>
</form>

<p>
<h3>Villes du système : </h3>
<ul>
	<c:forEach items="${listeVilles}" var="v">
		<li> ${v.nom} </li>
	</c:forEach>
</ul>

Ajouter une ville dans le système : 
<form method="post"> 
	Saisissez une ville à ajouter : <input type="text" name="newville"/>
	<button type="submit" name="admin" value="ajouterville">Ajouter</button>
</form>
</p>

<p>
<h3>Gabarits du système : </h3>
<ul>
	<c:forEach items="${listeGabarits}" var="g">
		<li> ${g} </li>
	</c:forEach>
</ul>

Ajouter un gabarit de véhicule dans le système : 
<form method="post"> 
	Saisissez le gabarit à ajouter : <input type="text" name="newgabarit"/>
	<button type="submit" name="admin" value="ajoutergabarit">Ajouter</button>
</form>
</p>


</body>
</html>