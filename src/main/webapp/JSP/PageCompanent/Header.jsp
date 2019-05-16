<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${pageContext.request.locale.language == 'ru'}">
	<fmt:setLocale value="${pageContext.request.locale.language}" />
</c:if>

<c:if test="${pageContext.request.locale.language != 'ru'}">
	<fmt:setLocale value="en" />
</c:if>

<fmt:setBundle basename="by.epam.webproject.voitenkov.bundle.MYbundle" />

<div class="header">

	<div class="logo"></div>

	<div class="headerText">
		<div class="russian">
			<a href="#">RU</a>&nbsp&nbsp|
		</div>
		<div class="english">
			<a href="#">EN</a>&nbsp&nbsp|
		</div>
		<div class="aboutProject">
			<a href="#">О проекте</a>
		</div>
	</div>

	<div class="nameMiddleName">${sessionScope.user.getSecondName()}
		${sessionScope.user.getName()}</div>

	<div class="menuDiv">
		<ul class="menu2">
			<li><a href=#><div class="profilebutton"></div></a>
				<ul class="submenu">
					<li><a href=#>Мой профиль</a></li>
					<li><a href=#>Редактировать</a></li>
					<li><a href=start?command=logout>Выход</a></li>
				</ul></li>
		</ul>
	</div>

	<hr class="hr">
</div>