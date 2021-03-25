<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooditem.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.mem.model.*"%>
<%! @SuppressWarnings("unchecked") %>

<% 
	MemVO memVO = (MemVO)session.getAttribute("memberVO");
	OrdVO ordVO = (OrdVO)request.getAttribute("ordVO");
	Vector<FooditemVO> buylist = (Vector<FooditemVO>) session.getAttribute("shoppingCart");
	String amount =  String.valueOf(session.getAttribute("total"));
	pageContext.setAttribute("memVO", memVO);
	pageContext.setAttribute("ordVO", ordVO);
	pageContext.setAttribute("feastInfoVO", session.getAttribute("feastInfoVO"));
	
	session.removeAttribute("total");
	session.removeAttribute("shoppingCart");
	session.removeAttribute("shoppingCartQuantity");
	session.removeAttribute("feastInfoVO");
%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
	<script src="<%=request.getContextPath()%>/js/jquery.twzipcode.min.js"></script>
<title>Welcome to EGG !</title>
<style>
img {
	display: block;
	margin-left: auto;
	margin-right: auto;
	<%--width: 50%;--%>
	max-width: 230px;
	max-height: 95px;
	width: auto;
	height: auto;
}
#content{
	text-align:left;
	margin-left:450px;
	margin-right:auto;
	border:1px solid lightblue;
}
</style>
</head>
<body>
<%@ include file="/header.jsp"%>
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>	
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<div class="card card-body">
	<br>	
	<div class="col-8">
	親愛的<b>${memVO.mem_name}</b> 您好!!!<br>
	訂單編號:<b>${ordVO.ord_no}</b><br>
	以下為您此次的訂單明細，屆時請準時參加飯局，謝謝！<br>
	</div>
	</div>
	<br>
	<%	if(buylist != null && buylist.size() != 0){ %>
<div class="card" >
 <div class="card-body">
		

	<% 	for (int i = 0; i < buylist.size(); i++) {
			FooditemVO order = buylist.get(i);
			String fo_no = order.getFo_no();
			String fo_name = order.getFo_name();
			int fo_price = order.getFo_price();
			int fo_quantity = order.getFo_quantity();
	%>

 	<div class="card-title">
 		<div class="row">
			<div class="form-group col-sm-6">
			餐點編號 : <%=fo_no%>
			</div>
			<div class="form-group col-sm-6">
			餐點名稱 : <%=fo_name%>
			</div></div></div>
		<div class="card-text">
			<div class="row">
				<div class="form-group col-sm-4">
				數量 : <%=fo_quantity%>
				</div>
				<div class="form-group col-sm-4">
				餐點金額 : <%=fo_price%>
				</div>
				<div class="form-group col-sm-4">
				小計 : <%=fo_price*fo_quantity%></div></div>
			</div></div>

			

	<%}
	}%>
		<div class="card-footer">
		
		<div class="form-group col-sm-6">
		消費總金額：<%=amount%></div>
		<div class="form-group col-sm-6">
		剩餘金額：$${memberVO.mem_point}</div>
	
		
		</div>

</div>
</div>
</div>
<div>
</div>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/feast/feastinfo.do">

									<input type="hidden" class="form-check-input" name="action"
										value="getOne_For_Display"> <input type="hidden" class="form-check-input" name="fea_no" value="${feastInfoVO.fea_no}">
									<button type="submit" class="btn btn-primary">返回飯局</button>
					</form>
</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>