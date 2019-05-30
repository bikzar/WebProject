<%@page import="by.epam.webproject.voitenkov.util.ConstantConteiner"%>
<%@page	import="by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login page</title>
	<link rel="stylesheet" href="CSS/StylesS.css">
	<script>
		function changeCommandValue() {
			var elem = document.getElementById('newCommand');
			elem.value = 'Registration_Page';
		}
	</script>
</head>
<body>
	<div class="marg">
		<table>
			<tr>
				<td>
					<form class="login" id="userLogin" name="LoginForm"	action="start" method="POST">
					
						<input id="newCommand" type="hidden" name="command" value="login" />
						
						<table>
							<tr>
								<td><font color="#000000"><fmt:message key="login.login" />:</font></td>
								<td><input class="login" type="text" name="login"></td>
							</tr>
							<tr>
								<td><font color="#000000"><fmt:message key="login.pasword" />:</font></td>
								<td><input class="login" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<hr> 
									<input class="butGrad" type="submit" value="<fmt:message key="login.enter" />"	style="height: 22px; width: 80px; font-size: 13px;"> 
									<input class="butGrad" type="submit" value="<fmt:message key="login.SignUp" />" onclick="changeCommandValue()" style="height: 22px; width: 89px; font-size: 13px;"><br>
									<font size="2" color="red">${message}</font>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>

