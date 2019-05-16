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

<div>
	<ul id="menu">

		<div class="title">
			<fmt:message key="userpage.BankAccount" />
		</div>

		<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
			<li>
				<form action="start" method="post">
					<input id="command" type="hidden" name="command"
						value="loadBankAccount" /> <input id="command" type="hidden"
						name="accountID" value="${bankAccount.getAccountId()}" /> <input
						type="submit" class="AccountButton"
						value="${bankAccount.getAccountId()} (${bankAccount.getCurrencyType()})" />
				</form>
			</li>
		</c:forEach>

		<div class="title">
			<fmt:message key="userpage.Operation" />
		</div>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="goToPayPage" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="userpage.Operation.Pay" />" />
			</form>
		</li>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="replenishAccount" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="userpage.Operation.AddMoney" />" />
			</form>
		</li>

		<div class="title">
			<fmt:message key="userpage.OperationHistory" />
		</div>
	</ul>
</div>