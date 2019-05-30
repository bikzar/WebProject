<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div class="PayDetailsBlock">

	<div class="imageReplenishDetails"></div>

	<div class="verticalLinePay"></div>

	<div class="PayDetailsText">
	
		<div style="text-decoration: underline; font-weight: 700">
			<fmt:message key="userpage.Operation.AddMoney.Header" />:
		</div>

		<form action="start" method="post">

			<input id="ReplenishCommand" type="hidden" name="command"
				value="replenish_Account" />

			<div class="Pay-flex-container">

				<div style="margin-left: 15px">
					<fmt:message key="userpage.Operation.AddMoney.ChooseSourcesCard" />:
				</div>

				<select name="cardId" class="PaySelector">
								
					<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
						
						<c:if test="${!bankAccount.isBlock()}">
						
							<c:forEach items="${bankAccount.getCreditCardList()}" var="creditCard">
	
								<c:if test="${!creditCard.isBlock()}">
									
									<c:set var="isSelect" value="" scope="request"></c:set>
									
									<c:if test="${requestScope.cardId eq creditCard.getCreditCardId()}">
										<c:set var="isSelect" value="selected" scope="request"></c:set>
										<c:set var="currency" value="${creditCard.getCyrrencyType()}" scope="request"></c:set>
									</c:if>
				
									<option value="${creditCard.getCreditCardId()}"  ${isSelect}>${creditCard.getCreditCardId()}:
										${bankAccount.getAccountMoney()}
										(${creditCard.getCyrrencyType()}) </option>
		
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>

				</select>
			</div>

			<div class="Pay-flex-container">

				<div>
					<fmt:message key="userpage.Operation.AddMoney.DestionationCard" />:
				</div>

				<select name="destinationCardId" class="PaySelector">

					<c:if test="${!requestScope.isAfterCalc}">
						<c:set var="isSelect" value="selected" scope="request"></c:set>
					</c:if>

					<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
					
					<c:if test="${!bankAccount.isBlock()}">
							
							<c:forEach items="${bankAccount.getCreditCardList()}" var="creditCard">
							
								<c:if test="${!creditCard.isBlock()}">
	
									<c:set var="isSelect" value="" scope="request"></c:set>
								
									<c:if test="${requestScope.destinationCardId eq creditCard.getCreditCardId()}">
										<c:set var="isSelect" value="selected" scope="request"></c:set>
									</c:if>
	
									<option ${isSelect} value="${creditCard.getCreditCardId()}">${creditCard.getCreditCardId()}:
										${bankAccount.getAccountMoney()}
										(${creditCard.getCyrrencyType()})</option>
	
								</c:if>
								
							</c:forEach>
							
						</c:if>
						
					</c:forEach>

				</select>
			</div>

			<div class="Pay-flex-container">
				<div style="margin-left: 85px">
					<fmt:message key="userpage.Operation.AddMoney.EnterSum" />:
				</div>
				<input type="text" class="inputId" name="sum" value="${requestScope.sum}"/>
			</div>
			
			<div style="margin-left: 190px; margin-top: -10px;">
				<font size="1" color="black">
					<fmt:message key="userpage.Operation.AddMoney.info"/>
				</font>
			</div>
			
			<input type="submit" class="CountButton"
				value="<fmt:message key="userpage.Operation.AddMoney.CountButton"/>"
				onclick="changeCommandValue()" />

			<div class="Pay-flex-container">
				<div style="margin-left: 68px">

					<c:set var="isHidden" value="hidden" scope="request"></c:set>

					<c:if test="${requestScope.isAfterCalc}">
						<c:set var="isHidden" value="" scope="request"></c:set>
					</c:if>
					
					<div style="margin-left: 40px;" ${isHidden}>
							
							<fmt:message key="userpage.Operation.AddMoney.Commision" />: 
						
							<fmt:formatNumber type = "number" pattern = "#################.##" value = "${requestScope.commission}"/>
							${requestScope.currency}
							
							<hr>
							
					</div>
					
					<div style="margin-left: 40px;" ${isHidden}>
						
						<div>
							<fmt:message key="userpage.Operation.AddMoney.TotalAmount" />: 
							
							<fmt:formatNumber type = "number" pattern = "#################.##" 
								value = "${requestScope.commission + requestScope.transactionSum}"/> 
									${requestScope.currency}
						</div>
						
						<div style="margin-left: 20px; margin-top: -13px;">
							<font size="1" color="black">
								<fmt:message key="userpage.Operation.AddMoney.withCommisionInfo"/>
							</font>
						</div>
					</div>

				</div>
			</div>
			
			<button type="submit" class="ReplenishButton" ${isHidden}>
				<fmt:message key="userpage.Operation.AddMoney.ButtonText" />
			</button>
		</form>

		<font size="2" color="red">${message}</font>

	</div>
</div>