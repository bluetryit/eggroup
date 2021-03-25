<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fooditem.model.*, com.res.model.*, com.tools.FindCodeName"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<jsp:useBean id="foodItemSvc" scope="page" class="com.fooditem.model.FooditemService" />
<html>
<head>
<title>餐點資料</title>



</head>
<body bgcolor='white'>

<%@ include file="/header.jsp"%>

<div class="container">
<br>
<c:if test="${resVO.res_status ne \"res1\"}">
<a class="btn btn-info btn-lg"  href="addFooditem.jsp">新增餐點</a>
</c:if>
	<div class="row">

	<c:forEach var="fooditemVO" items="${foodItemSvc.getAllfooditemVOByRes(resVO.res_no)}">
	<div class="col-4">
	<br>
<div class="card" >
  <img class="card-img-top"  style="max-height:300px;" src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=${fooditemVO.fo_no}" alt="Card image cap">
  <div class="card-body">
    <h4 class="card-title">${fooditemVO.getFo_name()}</h4>
    <p class="card-text">${fooditemVO.getFo_intro()}</p>
    <div class="row">
		<div class="form-group col-sm-6">
    <p class="card-text"><b>價格</b>${fooditemVO.getFo_price()}</p>
    	</div>
    	<div class="form-group col-sm-6">
    <p class="card-text"><b>狀態</b>&nbsp; ${FindCodeName.meaning(fooditemVO.getFo_status())}</p>
    </div>
    </div>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fooditem/fooditem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-secondary">
			     <input type="hidden" name="fo_no"  value="${fooditemVO.fo_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
	</FORM>
	
	

  </div>
</div>
</div>

	
</c:forEach>

</div>
</div>
</body>
</html>