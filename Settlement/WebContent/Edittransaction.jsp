<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
ORIGINAL VALUES:<br>
<table>
<c:forEach var="transaction" items="${transactions }">
<tr>
<td><c:out value="${transaction.transId }"></c:out></td>
<td><c:out value="${transaction.securityId }"></c:out></td>
<td><c:out value="${transaction.quantity }"></c:out></td>
<td><c:out value="${transaction.price}"></c:out></td>
<td><c:out value="${transaction.buyerCompId}"></c:out></td>
<td><c:out value="${transaction.sellerCompId}"></c:out></td>
</tr>
</c:forEach>
</table>
PLEASE FILL THE NEW TRANSACTION DETAILS:
<br>
<form action="updatetraansaction" method="get">
TRANSACTION ID<input type="text" name="transId"><br>
NAME OF SECURITY: <input type="text" name="security"><br>
QUANTITY: <input type="text" name="quantity"><br>
PRICE:<input type="text" name="price"><br>
BUYER CLEARING MEMBER:<input type="text" name="buyclearingmember"><br>
SELLER CLEARING MEMBER:<input type="text" name="sellclearingmember"><br><br>
<input type="submit" value="UPDATE TRANSACTION">
</form>
</body>
</html>