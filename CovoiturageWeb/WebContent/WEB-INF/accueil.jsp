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

MDR

<h1>Mes trajets</h1>
<ul>
	<c:forEach items="${listeTrajets}" var="t">
		<li> ${t.villeDepart} -> ${t.villeArrivee} </li>
	</c:forEach>
</ul>



</body>
</html>