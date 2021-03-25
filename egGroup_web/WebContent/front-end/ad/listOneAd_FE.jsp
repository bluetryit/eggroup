<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.tools.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  AdVO adVO = (AdVO) request.getAttribute("adVO"); //ResAcServlet.java(Concroller), 存入req的adVO物件
%>

<html>
<head>
<title> 廣告資料 - listOneAd.jsp</title>

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


<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1" width="1600px">
	<tr><td>
		 	<h3>員工資料 - ListOneAd.jsp</h3>
		 </td>
		 <td>
		 	<h4><a href="/DA101G6/front-end/ad/ad_FE.jsp">
		 		<img src="/DA101G6/images/tomcat.png" width="100" height="100" border="0">回首頁</a>
		 	</h4>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>廣告編號</th>
		<th>餐廳編號</th>
		<th>廣告標題</th>
		<th>廣告內容</th>
		<th>廣告照片</th>
		<th>廣告開始時間</th>
		<th>廣告結束時間</th>
		<th>廣告狀態</th>
	</tr>
	<tr>
			<td>${adVO.ad_no}</td>
			<td>${adVO.ad_resno}</td>
			<td>${adVO.ad_title}</td>
			<td>${adVO.ad_text}</td>
			<td><img src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}" width="300" height="200"> </td>
			<td>${adVO.ad_start}</td> 
			<td>${adVO.ad_end}</td> 
			<td>${FindCodeName.meaning(adVO.ad_status)}</td>
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