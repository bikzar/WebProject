<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login page</title>
	<link rel="stylesheet" href="CSS/StylesS.css">
	<script>
		function changeCommandValue() {
			document.getElementById('command').value = 'RegistrationPage';
		}
	</script>
</head>
<body>
	<div class="marg">
		<table>
			<tr>
				<td>
					<form class="login" id="userLogin" name="LoginForm"
						action="start" method="POST">
						<input id="command" type="hidden" name="command" value="login" />
						<table>
							<tr>
								<td><font color="#000000">Login:</font></td>
								<td><input class="login" type="text" name="login"></td>
							</tr>
							<tr>
								<td><font color="#000000">Password:</font></td>
								<td><input class="login" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<hr> 
									<input class="butGrad" type="submit" value="Log in"	style="height: 22px; width: 80px; font-size: 13px;"> 
									<input class="butGrad" type="submit" value="Sign up" onclick="changeCommandValue()" style="height: 22px; width: 80px; font-size: 13px;"><br>
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

