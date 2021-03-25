<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resac.model.*,com.res.model.*"%>
<%@ page import="com.tools.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ResAcService resAcSvc = new ResAcService();
	ResVO resVO = (ResVO) session.getAttribute("resVO");
    List<ResAcVO> list = resAcSvc.getAllByResNo(resVO.getRes_no());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllResAc.jsp</title>

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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<h3>員工管理</h3>


 <a class="btn btn-info" href='<%=request.getContextPath()%>/front-end/resAc/addResAc.jsp'>新增員工</a>


<table>
	<tr>
		<th>員工編號</th>
		<th>員工密碼</th>
		<th>員工姓名</th>
		<th>員工電話</th>
		<th>員工照片</th>
		<th>員工介紹</th>
		<th>員工狀態</th>
		<th>修改</th>
	</tr>
	
	<%@ include file="/page1.file" %> 
	<c:forEach var="resAcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${resAcVO.resac_no}</td>
			<td>${resAcVO.resac_pass}</td>
			<td>${resAcVO.resac_name}</td>
			<td>${resAcVO.resac_phone}</td>
			<td><img width="300" height="200" src="<%=request.getContextPath()%>/back-end/resAc/resAcPhoto.do?resac_no=${resAcVO.resac_no}&resac_resno=${resAcVO.resac_resno}"></td>
			<td>${resAcVO.resac_intro}</td> 
			<td>${FindCodeName.meaning(resAcVO.resac_status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/resAc/resAc.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="resac_no"  value="${resAcVO.resac_no}">
			     <input type="hidden" name="action"	value="getOneResAc_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/page2.file" %>

</body>
</html>