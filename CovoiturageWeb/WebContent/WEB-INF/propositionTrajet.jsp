<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post">
<p>
Saisissez une ville de départ :
<select name="villedepart">
   <c:forEach items="${listeVilles}" var="v">
		<option> ${v.nom} </option>
	</c:forEach>
</select> 
</p>
<p>
Saisissez une ville d'arrivée : 
<select name="villearrivee"> 
   <c:forEach items="${listeVilles}" var="v">
		<option> ${v.nom} </option>
	</c:forEach>
</select> 
</p>
<p>
Saisissez un tarif : <input type="number" name="tarif"/>
</p>
<p>
Saisissez un type de voiture : 
<select name="typevoiture"> 
   <option>SUV</option> 
   <option>Fourgonnette</option> 
   <option selected="selected">Break</option> 
   <option>Mdr</option> 
   <option>Urbaine</option> 
   <option>Petite</option> 
   <option>Grande</option> 
   <option>Moche mais pratique</option> 
   <option>Très grosse (cmb)</option> 
</select>
</p>
<p>
Saisissez un nombre de places :
<select name="nombreplaces"> 
   <option>1</option> 
   <option>2</option> 
   <option selected="selected">3</option> 
   <option>4</option> 
   <option>5</option> 
   <option>6</option> 
   <option>7</option> 
   <option>Abuse pas frère</option> 
</select>
</p> 



<p>
Date : 
	<p>
	Jour : 
	<select name="jour"> 
	   <c:forEach begin="1" end="31" var="j">
			<option>${j}</option> 
		</c:forEach>
	</select>
	Mois : 
	<select name="mois"> 
	   <c:forEach begin="1" end="12" var="m">
			<option>${m}</option> 
		</c:forEach>
	</select>
	</p>
</p>
<p>
Horaire : 
	<p>
	Heure :  
	<select name="heure"> 
	   <c:forEach begin="0" end="23" var="h">
			<option>${h}</option> 
		</c:forEach>
	</select>
	Minutes : 
	<select name="minutes"> 
	   <c:forEach begin="0" end="59" var="min">
			<option>${min}</option> 
		</c:forEach>
	</select>
	</p>
</p>
<p>
<h1>Etapes</h1>
<ul>
	<c:forEach items="${listeVilles}" var="v">
	<p>
		<input type="checkbox" name="etape" value="${v.nom}">${v.nom}
		Saisissez un tarif : <input type="number" name="${v.nom}"/>
	</p>
	</c:forEach>
</ul>
</p>


<button type="submit" name="todotrajet" value="valider">Valider</button>
</form>


</body>
</html>