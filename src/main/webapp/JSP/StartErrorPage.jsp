<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${pageContext.request.locale.language == 'ru'}">
	<fmt:setLocale value="${pageContext.request.locale.language}" />
</c:if>

<c:if test="${pageContext.request.locale.language != 'ru'}">
	<fmt:setLocale value="en" />
</c:if>

<fmt:setBundle basename="by.epam.webproject.voitenkov.bundle.MYbundle" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h2>
		<fmt:message key="error.message" />
	</h2>
</body>
</html>