<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="accountDetailsBlock">

	<div class="imageAccountDetails"></div>

	<div class="verticalLine"></div>

	<div class="accountDetailsText">

		<div style="text-decoration: underline; font-weight: 700">Bank account: 
			<fmt:formatNumber type = "number" pattern = "#####################" value = "${sessionScope.bankAccount.getAccountId()}"/>
			(${sessionScope.bankAccount.getAccountMoney()}
			${sessionScope.bankAccount.getCurrencyType()})</div>

		<div class="accountDetailsList">

			<c:forEach items="${sessionScope.creditCardList}" var="creditCard">
			
			<c:set var="hidden" value="" scope="request"/>
			<c:set var="iconColor" value="green" scope="request"/>
			<c:set var="iconSimbol" value="v" scope="request"/>
			
			<c:if test="${creditCard.isBlock()}">
				<c:set var="hidden" value="hidden" scope="request"/>
				<c:set var="iconColor" value="red" scope="request"/>
				<c:set var="iconSimbol" value="x" scope="request"/>
			</c:if>
				
				<div class="flex-container">

					<button type="button" class="accountDetailsBlockIcon"
						style="background-color: ${iconColor}" >${iconSimbol}</button>

					<div class="creditCardNameText">Credit card: 
						<fmt:formatNumber type = "number" value = "${creditCard.getCreditCardId()}"/>
					</div>

					<div>
						<form action="start" method="post" class="blockForm">
							<input type="hidden" name="command" value="blockCard"/>
							<input type="hidden" name="cardId" value="${creditCard.getCreditCardId()}"/>
							<button type="submit" class="accountDetailsButton" ${hidden}>Block</button>
						</form>
					</div>

				</div>
				
			</c:forEach>

			<div class="flex-container">
				<form action="start" method="post">
					<button type="submit" class="addCardButton">+ Add new...</button>
				</form>
			</div>

		</div>
	</div>
</div>
