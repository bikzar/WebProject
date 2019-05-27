<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html dir="ltr">
	<head>
		<meta charset="utf-8">
		<title>Admin Page</title>
		<link rel="stylesheet" type="text/css" href="CSS/StylesS.css">
	</head>
	<body>
		
		<div class="parent">
		
			<c:import url="./PageCompanent/Header.jsp"></c:import>
			
			<c:import url="./PageCompanent/AdminLeftMenu.jsp"></c:import>
	
		</div>
		
		<c:import url="./PageCompanent/Footer.jsp"></c:import>
	
	</body>
</html>