<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Registration</title>
		<link rel="stylesheet" href="CSS/StylesS.css">
		<script>
			function changeCommandValue() {
				document.getElementById('command').value = 'goToLogInPage';
			}
		</script>
		<fmt:requestEncoding value = "UTF-8" />
	</head>
<body>
	<div class="marginRegistration">
		<form action="start" method="post"
			class="regestrationForm">
			<input id="command" type="hidden" name="command" value="registration" />
			<table>
				<tr>
					<th colspan="2">User Information.<br>
					</th>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight">Name:</td>
					<td><input type="text" name="name" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight">Second Name:</td>
					<td><input type="text" name="secondName" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight">Birth Date:</td>
					<td><input type="date" name="birthDate" value=""></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight">Login:</td>
					<td><input type="text" name="login" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight">Password:</td>
					<td><input type="text" name="password" value="" size="30">
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td colspan="2" class="registrationTdAlignCenter"><br>
					<input class="butGrad" type="submit" value="Regestry">
					<input class="butGrad" type="submit" value="Back"
						onclick="changeCommandValue()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>