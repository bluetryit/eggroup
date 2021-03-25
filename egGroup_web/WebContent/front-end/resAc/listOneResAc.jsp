<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.resac.model.*"%>
<%@ page import="com.tools.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ResAcVO resAcVO = (ResAcVO) request.getAttribute("resAcVO"); //ResAcServlet.java(Concroller), 存入req的resAcVO物件
%>

<html>
<head>
<title>員工資料 - listOneRes.jsp</title>

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
	width: 1600px;
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

<%@ include file="/header.jsp"%>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工密碼</th>
		<th>員工姓名</th>
		<th>員工電話</th>
		<th>員工照片</th>
		<th>員工介紹</th>
		<th>員工狀態</th>
	</tr>
	<tr>
			<td>${resAcVO.resac_no}</td>
			<td>${resAcVO.resac_pass}</td>
			<td>${resAcVO.resac_name}</td>
			<td>${resAcVO.resac_phone}</td>
			<td><img width="300" height="200" src="<%=request.getContextPath()%>/back-end/resAc/resAcPhoto.do?resac_no=${resAcVO.resac_no}&resac_resno=${resAcVO.resac_resno}"></td>
			<td>${resAcVO.resac_intro}</td> 
			<td>${FindCodeName.meaning(resAcVO.resac_status)}</td>
	</tr>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>


</html>