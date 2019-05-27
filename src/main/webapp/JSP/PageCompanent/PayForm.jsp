<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div class="PayDetailsBlock">

	<div class="imagePayDetails"></div>

	<div class="verticalLinePay"></div>

	<div class="PayDetailsText">

		<div style="text-decoration: underline; font-weight: 700">
			<fmt:message key="userpage.Operation.Pay.Header" />:
		</div>

		<form action="start" method="post">
			<input type="hidden" name="command" value="pay" />

			<div class="Pay-flex-container">

				<div style="margin-left: 37px">
					<fmt:message key="userpage.Operation.Pay.ChooseSourcesCard" />:
				</div>

				<select name="cardId" class="PaySelector">
					<c:set var="isSelect" value="selected" scope="request"></c:set>
					<c:forEach items="${sessionScope.bankAccountList}"
						var="bankAccount">
						<c:forEach items="${bankAccount.getCreditCardList()}"
							var="creditCard">
							<c:if test="${!creditCard.isBlock()}">
								<option ${isSelecte} value="${creditCard.getCreditCardId()}">${creditCard.getCreditCardId()}:
									${bankAccount.getAccountMoney()}
									(${creditCard.getCyrrencyType()})</option>
								<c:set var="isSelect" value="" scope="request"></c:set>
							</c:if>
						</c:forEach>
					</c:forEach>

				</select>
			</div>

			<div class="Pay-flex-container">
				<div>
					<fmt:message key="userpage.Operation.Pay.DestionationCard" />:
				</div>
				<input type="text" class="inputId" name="accountID" />

			</div>

			<div class="Pay-flex-container">
				<div style="margin-left: 103px">
					<fmt:message key="userpage.Operation.Pay.EnterSum" />:
				</div>
				<input type="text" class="inputId" name="sum" />
			</div>

			<button type="submit" class="PayButton">
				<fmt:message key="userpage.Operation.Pay.ButtonText" />
			</button>

		</form>

	</div>
</div>