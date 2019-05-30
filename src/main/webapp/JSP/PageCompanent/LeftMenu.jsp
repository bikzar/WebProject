<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div>
	<ul id="menu">

		<div class="title">
			<fmt:message key="userpage.BankAccount" />
		</div>

		<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
			<li>
				<form action="start" method="post">
					<input id="command" type="hidden" name="command" value="load_Bank_Account" /> 
					<input type="hidden" name="accountID" value="${bankAccount.getAccountId()}" />
					<input type="submit" class="AccountButton" value="${bankAccount.getAccountId()} (${bankAccount.getCurrencyType()})" />
				</form>
			</li>
		</c:forEach>

		<div class="title">
			<fmt:message key="userpage.Operation" />
		</div>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="go_To_Pay_Page" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="userpage.Operation.Pay" />" />
			</form>
		</li>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="go_To_Replenish_Page" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="userpage.Operation.AddMoney" />" />
			</form>
		</li>

		<div class="title">
			<fmt:message key="userpage.OperationHistory" />
		</div>
		
		<li>
			<form action="start" method="post">
				<input id="command" type="hidden" name="command" value="go_To_Payment_History" /> 
				<input type="submit" class="AccountButton" value="<fmt:message key="userpage.OperationHistory.loadPayHistory"/>" />
			</form>
		</li>
	</ul>
</div>