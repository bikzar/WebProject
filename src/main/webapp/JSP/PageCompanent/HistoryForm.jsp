<%@page import="by.epam.webproject.voitenkov.util.ConstantConteiner"%>
<%@page
	import="by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader"%>
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

<div class="historyBlock">

	<div class="Pay-flex-container">

		<div style="margin-left: 15px; margin-top: 5px; margin-right: 5px">
			<fmt:message key="userpage.OperationHistory.choiceBankAccount" />:
		</div>

		<form action="start" method="post">

			<input id="command" type="hidden" name="command" value="loadPayHistory" /> 
				
				<c:set var="isSelect" value="" scope="page"></c:set>
				
				<select name="accountID" onchange="this.form.submit()">
				
				<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
					
					<c:if test="${sessionScope.accountID == bankAccount.getAccountId()}">
						<c:set var="isSelect" value="selected" scope="page" />
					</c:if>
										
					<option value="${bankAccount.getAccountId()}" ${isSelect}>
						${bankAccount.getAccountId()}: ${bankAccount.getAccountMoney()}
						(${bankAccount.getCurrencyType()})</option>
					
					<c:set var="isSelect" value="" scope="page" />
					
				</c:forEach>
			</select>
		</form>

		<font size="2" color="red" style="margin-left: 10px">${message}</font>

	</div>

	<table border="1" class="historyTable">

		<tr>
			<th>№</th>
			<th><fmt:message key="userpage.OperationHistory.sourceID" /></th>
			<th><fmt:message key="userpage.OperationHistory.destinationID" /></th>
			<th><fmt:message key="userpage.OperationHistory.operationType" /></th>
			<th><fmt:message key="userpage.OperationHistory.credditCardID" /></th>
			<th><fmt:message key="userpage.OperationHistory.operationSum" /></th>
			<th><fmt:message key="userpage.OperationHistory.currencyType" /></th>
			<th><fmt:message key="userpage.OperationHistory.date" /></th>
		</tr>

		<c:set var="number" value="1" scope="page" />
		<%
			request.getSession().getAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.TRANSACTION_LIST));
		%>
		<c:forEach items="${sessionScope.transactionList}" var="transaction">
			<tr>
				<td>${number}</td>
				<td>${transaction.getResursId()}</td>
				<td>${transaction.getDestinationId()}</td>
				<td>${transaction.getOperationType()}</td>
				<td>${transaction.getCardId()}</td>
				<td>${transaction.getSum()}</td>
				<td>${transaction.getCurrencyType()}</td>
				<td>${transaction.getDateTime()}</td>

				<c:set var="number" value="${number+1}" scope="page" />
			</tr>
		</c:forEach>

	</table>
</div>
