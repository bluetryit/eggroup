<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fooditem.model.*, com.res.model.*, com.tools.FindCodeName"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<% 
	ResVO resVO = (ResVO)request.getAttribute("resVO");
	
	pageContext.setAttribute("resVO", resVO);

%>

<jsp:useBean id="foodItemSvc" scope="page" class="com.fooditem.model.FooditemService" />
<html>
<head>
<title>待審核餐點 </title>

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
	width: 600px;
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

<%@ include file="/back-end/BackHeader.jsp"%>

<div class="container">
<div class="container">
  
</div>
	<div class="row">
 			<div class="col text-center">
				<button type="button" class="btn btn-warning"><a href="<%=request.getContextPath()%>/back-end/res/reviewRes.jsp" class="justify-content-center">返回審核餐廳頁面</a></button>
			</div>
		</div>
	<div class="row">

	<c:forEach var="fooditemVO" items="${foodItemSvc.getAllReviewFooditemByRes(resVO.res_no)}">
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
			     <input type="submit" value="審核通過" class="btn btn-secondary">
			     <input type="hidden" name="fo_no"  value="${fooditemVO.fo_no}">
			     <input type="hidden" name="action"	value="reviewFooditem">
	</FORM>
	

  </div>
</div>
</div>

	
</c:forEach>

</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

<c:if test="${needRe.equals(\"true\")}" var="flag" scope="session">
	<%session.removeAttribute("needRe");%>
	<script>
		$(function() {
			alert("已審核餐點！");
		});
	</script>
</c:if>


</body>
</html>