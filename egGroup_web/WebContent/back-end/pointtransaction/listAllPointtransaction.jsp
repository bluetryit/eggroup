<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointtransaction.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
PointtransactionService pointtransactionSvc = new PointtransactionService();
    List<PointtransactionVO> list = pointtransactionSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有點數交易資料 - listAllPointtransaction.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="/back-end/BackHeader.jsp"%>
	<br>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>點數交易編號</th>
		<th>會員編號</th>
		<th>餐廳編號</th>
		<th>交易金額</th>
		
	
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="pointtransactionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${pointtransactionVO.pt_no}</td>
			<td>${pointtransactionVO.pt_memno}</td>
			<td>${pointtransactionVO.pt_resno}</td>
			<td>${pointtransactionVO.pt_nt}</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%

    pageContext.removeAttribute("list");
%>
</body>
</html>