<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*" %>
<%
	ResVO resVO = (ResVO) session.getAttribute("resVO");
%>
<html>
<head>
<title>IBM ResAc: Home</title>

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
   <tr><td><h3>IBM ResAc: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ResAc: Home</p>

<h3>資料查詢:</h3>
	
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
  <li><a href='listAllResAc.jsp'>List</a> all ResAcs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="resAc.do" >
        <b>輸入員工編號 (如000001):</b>
        <input type="text" name="resac_no">
        <input type="hidden" name="action" value="getOneResAc_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="resAcSvc" scope="page" class="com.resac.model.ResAcService" />
   
  <li>
     <FORM METHOD="post" ACTION="resAc.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="resac_no">
         <c:forEach var="resAcVO" items="${resAcSvc.getAllByResNo(resVO.res_no)}" > 
          <option value="${resAcVO.resac_no}">${resAcVO.resac_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneResAc_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="resAc.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="resac_no">
         <c:forEach var="resAcVO" items="${resAcSvc.getAllByResNo(resVO.res_no)}" > 
          <option value="${resAcVO.resac_no}">${resAcVO.resac_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneResAc_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/resAc/addResAc.jsp'>Add</a> a new ResAc.</li>
</ul>

</body>
</html>