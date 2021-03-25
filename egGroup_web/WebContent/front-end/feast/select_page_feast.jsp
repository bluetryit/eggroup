<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    
<title>IBM Feast: Home</title>

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
   <tr><td><h3>IBM Feast: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Feast: Home</p>

<h3>資料查詢:</h3>
	
<%-- �航炊銵典�� --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFeast.jsp'>List</a> all Feasts.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="feastinfo.do" >
        <b>輸入飯局編號 (如FE000001):</b>
        <input type="text" name="fea_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="feastInfoSvc" scope="page" class="com.feastinfo.model.FeastInfoService" />
   
  <li>
     <FORM METHOD="post" ACTION="feastinfo.do" >
       <b>選擇飯局編號:</b>
       <select size="1" name="fea_no">
         <c:forEach var="feastInfoVO" items="${feastInfoSvc.allFeastInfoVOs}" > 
          <option value="${feastInfoVO.fea_no}">${feastInfoVO.fea_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="feastinfo.do" >
       <b>選擇飯局標題:</b>
       <select size="1" name="fea_no">
         <c:forEach var="feastInfoVO" items="${feastInfoSvc.allFeastInfoVOs}" > 
          <option value="${feastInfoVO.fea_no}">${feastInfoVO.fea_title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>飯局管理</h3>

<ul>
  <li><a href='addFeast.jsp'>Add</a> a new Feast.</li>
</ul>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>