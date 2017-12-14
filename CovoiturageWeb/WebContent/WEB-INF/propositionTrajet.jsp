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
   <option>Marseille</option> 
   <option>Dijon</option> 
   <option>Bourges</option> 
   <option>Paris</option> 
   <option>Rouen</option> 
   <option>Rennes</option> 
   <option>Lorient</option> 
   <option>Chaville</option> 
   <option>Saint-Etienne</option> 
   <option selected="selected">Lyon</option>
</select> 
</p>
<p>
Saisissez une ville d'arrivée : 
<select name="villearrivee"> 
   <option>Marseille</option> 
   <option>Dijon</option> 
   <option selected="selected">Bourges</option> 
   <option>Paris</option> 
   <option>Rouen</option> 
   <option>Rennes</option> 
   <option>Lorient</option> 
   <option>Chaville</option> 
   <option>Saint-Etienne</option> 
   <option>Lyon</option>
</select> 
</p>
<p>
Saisissez un tarif : <input type="text" name="tarif"/>
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
<button type="submit" name="todotrajet" value="valider">Valider</button>
</form>


</body>
</html>