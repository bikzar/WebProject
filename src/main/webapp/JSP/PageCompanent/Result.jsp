<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="MYbundle" />

<div class="PayDetailsBlock">

	<c:if test="${sessionScope.isSuccess}">
		<div class="imageSucImage"></div>
	</c:if>

	<c:if test="${!sessionScope.isSuccess}">
		<div class="resultFailImage"></div>
	</c:if>

	<div class="verticalLinePay"></div>

	<div class="PayDetailsText">

		<c:if test="${sessionScope.isSuccess}">

			<font size="5" color="black" ><fmt:message
					key="userpage.Operation.success" /></font>

		</c:if>

		<c:if test="${!sessionScope.isSuccess}">

			<font size="5" color="black"><fmt:message
					key="userpage.Operation.fail" /></font>

		</c:if>


	</div>
</div>