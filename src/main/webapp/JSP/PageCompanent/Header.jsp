<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div class="header">

	<div class="logo"></div>

	<div class="headerText">
		<div class="russian">
			<form action="start" method="post">
				<input name="command" value="change_Locale" type="hidden">
				<input name="language" value="ru" type="hidden">
				<input type="submit" class="AccountButton" value="RU  |"/>
			</form>
		</div>
		<div class="english">
			<form action="start" method="post">
				<input name="command" value="change_Locale" type="hidden">
				<input name="language" value="en" type="hidden">
				<input type="submit" class="AccountButton" value="EN"/>
			</form>
		</div>
	</div>

	<div class="nameMiddleName">${sessionScope.user.getSecondName()}
		${sessionScope.user.getName()}</div>

	<div class="menuDiv">
		<ul class="menu2">
			<li><a href=#><div class="profilebutton"></div></a>
				<ul class="submenu">
					<li><a href=start?command=log_out><fmt:message key="userpage.header.LogOut" /></a></li>
				</ul></li>
		</ul>
	</div>

	<hr class="hr">
</div>