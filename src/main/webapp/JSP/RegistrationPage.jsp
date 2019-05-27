<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

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
	</head>
<body>
	<div class="marginRegistration">
		<form action="start" method="post"
			class="regestrationForm">
			<input id="command" type="hidden" name="command" value="registration" />
			<table>
				<tr>
					<th colspan="2"><fmt:message key="registretion.lable.userInfo" />.<br>
					</th>
				</tr>
				<tr>
					<td class="registrationTdAlignRight"><fmt:message key="registretion.lable.name" />:</td>
					<td><input type="text" name="name" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight"><fmt:message key="registretion.lable.secondName" />:</td>
					<td><input type="text" name="secondName" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight"><fmt:message key="registretion.lable.birthDate" />:</td>
					<td><input type="date" name="birthDate" value=""></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight"><fmt:message key="registretion.lable.login" />:</td>
					<td><input type="text" name="login" value="" size="30"></td>
				</tr>
				<tr>
					<td class="registrationTdAlignRight"><fmt:message key="registretion.lable.password" /></td>
					<td><input type="text" name="password" value="" size="30">
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td colspan="2" class="registrationTdAlignCenter"><br>
					<input class="butGrad" type="submit" value="<fmt:message key="registretion.lable.reg" />">
					<input class="butGrad" type="submit" value="<fmt:message key="registretion.lable.back" />"
						onclick="changeCommandValue()">
					</td>
				</tr>
			</table>
		</form>
		<font size="2" color="red">${message}</font>
	</div>
</body>
</html>