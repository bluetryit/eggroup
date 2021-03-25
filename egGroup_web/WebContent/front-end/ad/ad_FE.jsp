<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*" %>
<%
	ResVO resVO = (ResVO) session.getAttribute("resVO");
%>
<html>
<head>
<title>IBM Ad: Home</title>

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

<div class="container">
	<div class="row justify-content-center">
	<table class="table">
	 <div class="form-group">
	 <tr>
<h3>廣告管理</h3>
</tr>
</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<tr>
  <a href='listAllAd_FE.jsp'>List</a> all ResAcs.  <br><br>
  </tr>
  

  <jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" />
   

 <tr>
     <FORM METHOD="post" ACTION="ad.do" >
      <div>
       <b>選擇廣告標題:</b>
       <select size="1" name="ad_no" class="btn btn-outline-secondary dropdown-toggle">
         <c:forEach var="adVO" items="${adSvc.getAllByRes(resVO.res_no)}" > 
          <option value="${adVO.ad_no}" >${adVO.ad_title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneAd_For_Display_By_Res">
       <input type="submit" value="送出"  class="btn btn-info">
     </div>
     </FORM>
  </tr>



<tr>
<ul>
  <li><a href='/DA101G6/front-end/ad/addAd.jsp'>Add</a> a new Ad.</li>
</ul>
</tr>
</table>
</div>
</div>
</body>
</html>