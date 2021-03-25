<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.tools.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AdService adSvc = new AdService();
    List<AdVO> list = adSvc.getAllNotReview();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有待審核廣告 - listAllAd_BE.jsp</title>

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
	width: 100%;
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
<%@ include file="/back-end/BackHeader.jsp" %> 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="row justify-content-center">
<table>
	<tr>
		<th>廣告編號</th>
		<th>餐廳編號</th>
		<th>廣告標題</th>
<!-- 		<th>廣告內容</th> -->
		<th>廣告照片</th>
		<th>廣告開始時間</th>
		<th>廣告結束時間</th>
		<th>廣告狀態</th>
		<th>審核</th>
	</tr>
	 
	 	<%@ include file="page1.file"%>
	<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${adVO.ad_no}</td>
			<td>${adVO.ad_resno}</td>
			<td>${adVO.ad_title}</td>
<!-- 			<td><pre class="form-control" id="exampleFormControlTextarea3" cols="35" rows="10"style="resize:none;border: 0px;outline:none;Comparator.reverseOrder()"  -->
<%-- 					style="word-break:break-all">${adVO.ad_text}</pre></td> --%>
			<td><img src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}" width="300" height="200"> </td>
			<td><fmt:formatDate value="${adVO.ad_start}" pattern="yyyy-MM-dd" /></td> 
			<td><fmt:formatDate value="${adVO.ad_end}" pattern="yyyy-MM-dd" /></td> 
			<td>${FindCodeName.meaning(adVO.ad_status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/ad/ad.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-secondary" value="審核">
			     <input type="hidden" name="ad_no"  value="${adVO.ad_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file" %>

</body>
</html>