<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointtransaction.model.*"%>

<jsp:useBean id="pointtransactionVO" scope="request" class="com.pointtransaction.model.PointtransactionVO" />


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>餐點資料修改 - update_pointtransaction_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>點數資料修改 - update_pointtransaction_input.jsp</h3>
		 <h4><a href="pointtransaction_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="pointtransaction.do" name="form1">
<table>
	<tr>
		<td>點數編號:<font color=red><b>*</b></font></td>
		<td><%=pointtransactionVO.getPt_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="pt_memno" size="45" value="<%=pointtransactionVO.getPt_memno()%>" /></td>
	</tr>
	<tr>
		<td>餐廳編號:</td>
		<td><input type="TEXT" name="pt_resno" size="45" value="<%=pointtransactionVO.getPt_resno()%>" /></td>
	</tr>
	<tr>
		<td>交易金額:</td>
		<td><input type="TEXT" name="pt_nt" size="45" value="<%=pointtransactionVO.getPt_nt()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pt_no" value="<%=pointtransactionVO.getPt_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</html>