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
<table>
<c:forEach var="transaction" items="${transactions }">
<tr>
<td><c:out value="${transaction.transId }"></c:out></td>
<td><c:out value="${transaction.securityId }"></c:out></td>
<td><c:out value="${transaction.quantity }"></c:out></td>
<td><c:out value="${transaction.price}"></c:out></td>
<td><c:out value="${transaction.buyerCompId}"></c:out></td>
<td><c:out value="${transaction.sellerCompId}"></c:out></td>
<td><a href="delete?transId=<c:out value='${book.ISBN }'/>">DELETE</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>