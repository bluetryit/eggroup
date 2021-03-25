<%@page import="com.res.model.ResVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*,com.res.model.*"%>
<%@ page import="com.tools.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AdService adSvc = new AdService();
	ResVO resVO = (ResVO)session.getAttribute("resVO");
    List<AdVO> list = adSvc.getAllByRes(resVO.getRes_no());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有廣告資料 - listAllAd.jsp</title>

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
<a class="btn btn-info" href='<%=request.getContextPath()%>/front-end/ad/addAd.jsp'>申請廣告</a>
<table>
	<tr>
		<th>廣告標題</th>
		<th>廣告內容</th>
		<th>廣告照片</th>
		<th>廣告開始時間</th>
		<th>廣告結束時間</th>
		<th>廣告狀態</th>
		<th>修改</th>
	</tr>
	 
	<%@ include file="/page1.file" %> 
	<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${adVO.ad_title}</td>
			<td><pre class="lead" style="word-break:break-all;width:auto; height:auto;" >${adVO.ad_text}</pre></td>
<!-- 			<td><textarea class="form-control" id="exampleFormControlTextarea3" cols="150" rows="50" -->
<!-- 					style="resize:none;border: 0px;outline:none;"  -->
<%-- 					readonly="readonly">${adVO.ad_text}</textarea></td> --%>
			<td><img src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}" width="300" height="200"> </td>
			<td>${adVO.ad_start}</td> 
			<td>${adVO.ad_end}</td> 
			<td>${FindCodeName.meaning(adVO.ad_status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ad/ad.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ad_no"  value="${adVO.ad_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/page2.file" %>

</body>
</html>