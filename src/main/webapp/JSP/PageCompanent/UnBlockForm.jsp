<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="MYbundle" />

<div class="accountDetailsBlock">

	<div class="imageUnlockDetails"></div>

	<div class="verticalLine"></div>

	<div class="accountDetailsText">
	
		<font size="2" color="red">${message}</font>
		
		<table>
		
			<c:forEach items="${sessionScope.bankAccountList}" var="bankAccount">
				
				<tr>
				
					<td colspan="7" class="line" width="400">
						<div style="font-weight: 700; margin-top: 15px;">
							<fmt:message key="userpage.BankAccount" />:
							<fmt:formatNumber type="number" pattern="#####################"	value="${bankAccount.getAccountId()}" /> (${bankAccount.getAccountMoney()} ${bankAccount.getCurrencyType()})
						</div>
					</td>
					
					<td align="center" class="line">
						<div>
							<form action="start" method="post" class="blockForm">
								
								<input type="hidden" name="accountID"	value="${bankAccount.getAccountId()}" />
								
								<c:if test="${bankAccount.isBlock()}">
									<input type="hidden" name="command" value="unLock_Account" /> 
									<button type="submit" class="lockUnlockButt" style="background-color: red;"> 
										<fmt:message key="adminpage.search.lock" />
									</button>
								</c:if>

								<c:if test="${!bankAccount.isBlock()}">
									<input type="hidden" name="command" value="lock_Account" /> 
									<button type="submit" class="lockUnlockButt"	style="background-color: green;">
										<fmt:message key="adminpage.search.unlock" />
									</button>
								</c:if>

							</form>
						</div>
					</td>
					<td align="center" class="line">
						<form action="start" method="post">
							<input type="hidden" name="accountID" value="${bankAccount.getAccountId()}" />
							<input type="hidden" name="command" value="add_Card" /> 
							<button type="submit" class="addCardButton">+<fmt:message key="userpage.BankAccount.addNew" /></button>
						</form>
					</td>
				</tr>
				
					<c:forEach items="${bankAccount.creditCardList}" var="creditCard">
						
						<tr>
							<td align="right" class="line" width="20">
								<div>
									<form action="start" method="post" class="blockForm">
										
										<input type="hidden" name="cardId"	value="${creditCard.getCreditCardId()}" />
										
										<c:if test="${creditCard.isBlock()}">
											<input type="hidden" name="command" value="unLock_Card" /> 
											<button type="submit" class="lockUnlockButt" style="background-color: red;"> 
												<fmt:message key="adminpage.search.lock" />
											</button>
										</c:if>

										<c:if test="${!creditCard.isBlock()}">
											<input type="hidden" name="command" value="lock_Card" /> 
											<button type="submit" class="lockUnlockButt"	style="background-color: green;">
												<fmt:message key="adminpage.search.unlock" />
											</button>
										</c:if>

									</form>
								</div>
							</td>
							
							<td class="line"  width="90">
								<div class="creditCardNameText">
									<fmt:message key="userpage.BankAccount.Card" />:
									<fmt:formatNumber type="number" value="${creditCard.getCreditCardId()}" />
								</div>
							</td>
							
							<td class="line">
								<form action="start" method="post" class="blockForm">
									
									<input type="hidden" name="cardId"	value="${creditCard.getCreditCardId()}" />
									<input type="hidden" name="command" value="delete_Card" /> 
									
									<button type="submit" class="deleteButt" style="background-color: red;">
										<fmt:message key="adminpage.search.delete" />
									</button>
	
								</form>
							</td>
							
						</tr>
						
					</c:forEach>
			</c:forEach>
		</table>
		
		<font size="2" color="red">${message}</font>
	</div>
</div>

