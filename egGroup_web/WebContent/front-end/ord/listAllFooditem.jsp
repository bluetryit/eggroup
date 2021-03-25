<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooditem.model.*,com.res.model.*,com.feastinfo.model.*"%>

<%
	ResVO resVO = (ResVO) session.getAttribute("ResVO"); // "ResVO" 與 餐廳登入的"resVO" 區別
		
	
	FooditemService fooditemSvc = new FooditemService();
	List<FooditemVO> list = fooditemSvc.getByResNOFooditem(resVO.getRes_no());
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>

<title>Welcome to EGG !</title>

<style>
img {
	width: 300px;
	height: 150px;
}

.card {
	margin-bottom: 15px;
	margin-left: 8px;
	margin-right: 8px;
	border: 0px;
	 
}
.card-body{
height:200px;

}

.card-text {
	height: 50px;
	
}

.card-title {
	height: 35px;
}

.div_cart {
/*  width: 75px;  */ 
/* 	height:-100px;  */
	float: right; 
	position: fixed;
	right: 0px;
	bottom: 0px;
	z-index: 100;
	text-align:center;
}
#amount{
	color:#fff;
	font-size:30px;
	font-weight:bold; 

}
sub{
	font-size:40px;
	font-weight:bold;
}

</style>
</head>
<body>
<%@ include file="/header.jsp"%>
	<br>
	<div class="container">
<!-- 		<div class="row"> -->
<%-- 			<div class="col-12" align="center"><%@ include file="/page1.file"%></div> --%>
<!-- 		</div> -->
		<div class="row"> 
			<c:forEach var="fooditemVO" items="${list}">
				<div class="col-3">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=${fooditemVO.fo_no}"
							class="card-img-top">
							<h5 class="card-header">${fooditemVO.fo_name}</h5>
						<div class="card-body">
							<p>${fooditemVO.fo_intro}</p>
							
							<form name="shoppingForm" method="POST" class="ok">
							<input type="hidden" name="action" value="add">	
							<input type="hidden" name="fo_no" value="${fooditemVO.fo_no}">
							<input type="hidden" name="fo_name" value="${fooditemVO.fo_name}">
							<input type="hidden" name="fo_price" value="${fooditemVO.fo_price}">
<!-- 							<input type="hidden" name="fo_quantity" value="1"> -->
							<%int quantity = 1;%>
							</div>
						<div class="card-footer">
						<div class="row">
								<div class="form-group col-sm-6">
									<label>價格:</label>
									<p>$ ${fooditemVO.fo_price}</p>
									</div>
								<div class="form-group col-sm-6">
								<label>選擇數量:</label>
							<select size="1" name="fo_quantity">
								<c:forEach var="quantity" begin="1" end="10" step="1">
									<option value="<%=quantity%>"><%=quantity%>
									<%++quantity; %>
								</c:forEach>
							</select>
							</div>
							</div>
							<button type="button" name="button" class="ord btn btn-primary">訂購</button>
							</div>
						</div>
						 </form>
					</div>
			</c:forEach>
		</div>
		
	</div>
	<br>
	
	<a href="ordConfirm.jsp">
	<div class="div_cart" align="left"> 
	<img id="shopcart" src="<%=request.getContextPath()%>/images/money.png" style="height:10%; width:10%;">
	<span id="amount"><%=(session.getAttribute("total")==null||(int)session.getAttribute("total")==0)?"":session.getAttribute("total")%></span>
<br>
	<span style="color:#fff"><img id="shopcart" src="<%=request.getContextPath()%>/images/shoppingcart.png" style="height:10%; width:30%;"></span>
	<sub><%=(session.getAttribute("shoppingCartQuantity")==null||(int)session.getAttribute("shoppingCartQuantity")==0)?"":session.getAttribute("shoppingCartQuantity")%></sub></div></a>

	
	
	<script>
	$(document).ready(function() {
		$(".ord").click(function() {
			console.log( $(this).parent().prev().find('form'));
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/front-end/ord/ord.do",
				data : $(this).parent().prev().find('form').serialize(),
				dataType : "json",
				success : function(res) {
					<!--alert("已添加至購物車");-->
					alert("已添加至購物車");
					$("sub").text(res.cartQuantity);
					$("#amount").text(res.cartAmount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
	});
</script>

	
</body>
</html>