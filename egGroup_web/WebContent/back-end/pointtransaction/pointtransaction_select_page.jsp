<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- fooditem_select_page 首頁 -->
<html>
<head>
<title>Pointtransaction: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>
<table id="table-1">
   <tr><td><h3>Pointtransaction: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Pointtransaction: Home</p>

<h3>後台點數資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPointtransaction.jsp'>所有點數交易紀錄</a>   <br><br></li>

  
  
  
  <li>
    <FORM METHOD="post" ACTION="pointtransaction.do" >
        <b>輸入點數交易編號 (EX:PT000001):</b>
        <input type="text" name="pt_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
<li>
    <FORM METHOD="post" ACTION="pointtransaction.do" >
        <b>輸入會員編號 (如ME000001):</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getOne_For_addpoint">
        <input type="submit" value="送出">
    </FORM>
  </li>

</ul>


<h3>餐點管理</h3>

<ul>
  <li><a href='addPointtransaction.jsp'>Add</a>回上頁</li>
</ul>

</body>
</html>