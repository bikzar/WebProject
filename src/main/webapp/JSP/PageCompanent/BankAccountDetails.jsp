<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="MYbundle" />

<div class="accountDetailsBlock">

	<div class="imageAccountDetails"></div>

	<div class="verticalLine"></div>

	<div class="accountDetailsText">
		<table>

			<tr>
				<td width="28">
					<c:if test="${sessionScope.bankAccount.isBlock()}">
						<button disabled="disabled" class="lockUnlockButt" style="background-color: red; cursor: default;"> 
							<fmt:message key="adminpage.search.lock" />
						</button>
					</c:if>

					<c:if test="${!sessionScope.bankAccount.isBlock()}">
						<button disabled="disabled" class="lockUnlockButt"	style="background-color: green; cursor: default;">
							<fmt:message key="adminpage.search.unlock" />
						</button>
					</c:if>
				
				</td>
				
				<td colspan="4" width="400">
					<div style="text-decoration: underline; font-weight: 700">
						<fmt:message key="userpage.BankAccount" />:  
						<fmt:formatNumber type = "number" pattern = "#####################" value = "${sessionScope.bankAccount.getAccountId()}"/>
						(${sessionScope.bankAccount.getAccountMoney()}
						${sessionScope.bankAccount.getCurrencyType()})
					</div>
				</td>
			</tr>
			
			<c:forEach items="${sessionScope.creditCardList}" var="creditCard">
			
				<tr>
					<td></td>
					
					<td width="20"></td>
					
					<td class="line">
						<div class="creditCardNameText">
							<fmt:message key="userpage.BankAccount.Card" />: 
							<fmt:formatNumber type = "number" value = "${creditCard.getCreditCardId()}"/>
						</div>
					</td>
					
					<td class="line" width="29">
						<div>
							<form action="start" method="post" class="blockForm">
							
								<input type="hidden" name="cardId" value="${creditCard.getCreditCardId()}"/>

								<c:if test="${creditCard.isBlock()}">
									<button disabled="disabled" class="lockUnlockButt" style="background-color: red; cursor: default;"> 
										<fmt:message key="adminpage.search.lock" />
									</button>
								</c:if>

								<c:if test="${!creditCard.isBlock()}">
									<input type="hidden" name="command" value="blockCard"/>
									<button type="submit" class="lockUnlockButt" style="background-color: green;">
										<fmt:message key="adminpage.search.unlock" />
									</button>
								</c:if>
							</form>
						</div>
					</td>
					<td width="110" align="center">
						<c:if test="${creditCard.isBlock()}">
							<font size="1">Contact with us for unlock</font>
						</c:if>
					</td>
				</tr>

			</c:forEach>
		</table>
	</div>
</div>
