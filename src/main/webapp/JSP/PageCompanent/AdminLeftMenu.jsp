<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div>
	<ul id="menu">

		<div class="title">
			<fmt:message key="adminpage.search" />
		</div>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="load_Search_Form" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="adminpage.findUserCard" />" />
			</form>
		</li>
	</ul>
</div>