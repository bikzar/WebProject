 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<?xml version="1.0" encoding="UTF-8"?>

<fmt:requestEncoding value = "UTF-8" />
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div class="historyBlock">
	
	<div class="imageSearchForm"></div>

	<div class="verticalLine"></div>
	
	<form action="start" method="post">
		
		<input type="hidden" name="command" value="find_User">
	
		<table border="1" class="searchTable">
			<tr>
				<th><fmt:message key="registretion.lable.name" />*</th>
				<th><fmt:message key="registretion.lable.secondName" />*</th>
				<th><fmt:message key="registretion.lable.login" /></th>
			</tr>
			<tr>
				<th><input type="text" size="13" name="name" value="${requestScope.name}"></th>
				<th><input type="text" size="13" name="secondName" value="${requestScope.secondName}"></th>
				<th><input type="text" size="13" name="login" value="${requestScope.login}"></th>
			</tr>
		</table>
		
		<input type="submit" class="searchPageButton" value="<fmt:message key="adminpage.search" />">
				
	</form>
	
	<div class="Pay-flex-container">
		<font size="2" color="red" style="margin-left: 50px; margin-top: 20px">${message}</font>
	</div>
	
	<c:set var="isHidden" value="hidden"></c:set>
	
	<c:if test="${requestScope.isAfterCalc}">
		<c:set var="isHidden" value=""></c:set>
	</c:if>
	
	<table ${isHidden} class="resultSearchTable">
		
		<tr class="fatline">
			<th><fmt:message key="registretion.lable.name" /></th>
			<th><fmt:message key="registretion.lable.secondName" /></th>
			<th><fmt:message key="registretion.lable.birthDate" /></th>
			<th><fmt:message key="registretion.lable.login" /></th>
		</tr>
		
		<c:forEach items="${requestScope.userList}" var="user">
			<tr class="line">
				<td>${user.getName()}</td>
				<td>${user.getSecondName()}</td>
				<td>${user.getBirthDate()}</td>
				<td>${user.getLogin()}</td>
				
				<td>
					<form action="start" method="post">
						<input type="hidden" name="userID" value="${user.getUserId()}">
						<input type="hidden" name="command" value="load_Unlock_Page">
						<input type="submit" class="choisUserButt" value="<fmt:message key="adminpage.detail" />">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</div>
