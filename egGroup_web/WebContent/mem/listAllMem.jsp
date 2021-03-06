<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.memGetAll();
    pageContext.setAttribute("list",list);
    Base64.Encoder encoder=Base64.getEncoder();
    session.setAttribute("encoder", encoder);
%>


<html>
<head>
<title>所有員工資料 - listAllMem.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllMem.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員地址</th>
		<th>性別</th>
		<th>生日</th>
		<th>電話</th>
		<th>信箱</th>
		<th>點數</th>
		<th>照片</th>
		<th>密碼</th>
		<th>帳號</th>
		<th>自介</th>
		<th>狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		<td>${memVO.getMem_no()}</td>
		<td>${memVO.getMem_name()}</td>
		<td>${memVO.getMem_adrs()}</td>
		<td>${memVO.getMem_sex()}</td>
		<td>${memVO.getMem_bd()}</td>
		<td>${memVO.getMem_ph()}</td>
		<td>${memVO.getMem_email()}</td>
		<td>${memVO.getMem_point()}</td>
		
		<!-- 取出byte[]轉成base64格式字串，然後印出來 -->
		<td><img src="data:image/jpg;base64,<c:out value='${encoder.encodeToString(memVO.getMem_img())}'/>"/></td> 
		<td>${memVO.getMem_img()}</td>
		<td>${memVO.getMem_pass()}</td>
		<td>${memVO.getMem_ac()}</td>
		<td>${memVO.getMem_intro()}</td>
		<td>${memVO.getMem_status()}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>