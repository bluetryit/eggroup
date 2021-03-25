<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.res.model.*,com.tools.*,com.fooditem.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ResService resSvc = new ResService();
	List<ResVO> reviewList = resSvc.getAllNoReview();
	List<ResVO> reviewAgainList = resSvc.getAllReviewAgain(); //拿出所有資訊未審核以及上線中商家
	
	
	
	pageContext.setAttribute("reviewList", reviewList);
	pageContext.setAttribute("reviewAgainList", reviewAgainList);
%>

<jsp:useBean id="foodItemSvc" scope="page" class="com.fooditem.model.FooditemService" />
<html>
<head>
<!-- Required meta tags -->
<!-- <meta charset="utf-8"> -->
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" -->
<!-- 	crossorigin="anonymous"> -->
<!-- <link rel="stylesheet" href="css/all.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->

<!-- Custom styles for this template -->
<!-- <link href="css/simple-sidebar.css" rel="stylesheet"> -->
<title>審核餐廳/餐點</title>

<style>
table#table-1 {
	background-color: #8bedf4;
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
	width: 800px;
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
<div>
				<ul class="nav nav-tabs justify-content-center" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#res" role="tab">審核餐廳</a></li>

					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#fooditem" role="tab">審核餐點</a></li>
				</ul>
</div>
<div class="row justify-content-center">
	<div class="col-sm-9 tab-content ">
		<div role="tabpanel" class="tab-pane active" id="res">	
		<table class="table" width="100% auto">
			<tr>
				<th  scope="col" width="15%">餐廳編號</th>
				<th  scope="col" width="15%">餐廳名稱</th>
				<th  scope="col" width="15%">餐廳類型</th>
				<th  scope="col" width="15%">餐廳地址</th>
				<th  scope="col" width="15%">餐廳電話</th>
				<th  scope="col" width="80%">審查狀態</th>
			</tr>
			<c:forEach var="resterauntVO" items="${reviewList}">
				<tr>
					<td >${resterauntVO.res_no}</td>
					<td>${resterauntVO.res_name}</td>
					<td>${resterauntVO.res_type}</td>
					<td>${resterauntVO.res_adrs}</td>
					<td>${resterauntVO.res_ph}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/res/res.do"
							style="margin-bottom: 0px;">

							<select class="brower-default custom-select"
								class="form-control btn-danger" name="res_status">

								<option selected value="res1">未處理</option>
								<option value="res2">通過</option>
							</select>
							<th><input type="submit" class="btn btn-secondary" value="審核">
								<input type="hidden" name="res_no" value="${resterauntVO.res_no}">
								<input type="hidden" name="action" value="review"></th>
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>

		<div role="tabpanel" class="tab-pane" id="fooditem">	
		<table width="100% auto">
			<tr>
				<th  scope="col" width="15%">餐廳編號</th>
				<th  scope="col" width="15%">餐廳名稱</th>
				<th  scope="col" width="15%">餐廳類型</th>
				<th  scope="col" width="15%">餐廳地址</th>
				<th  scope="col" width="15%">餐廳電話</th>
				<th  scope="col" width="80%">審查狀態</th>
			</tr>
			<c:forEach var="resterauntVO" items="${reviewAgainList}">
			<c:if test="${foodItemSvc.getAllReviewFooditemByRes(resterauntVO.res_no).size()!=0}">
				<tr>
					<td>${resterauntVO.res_no}</td>
					<td>${resterauntVO.res_name}</td>
					<td>${resterauntVO.res_type}</td>
					<td>${resterauntVO.res_adrs}</td>
					<td>${resterauntVO.res_ph}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/res/res.do"
							style="margin-bottom: 0px;">
							餐點待審核
							<th><input type="submit" class="btn btn-secondary" value="審核菜單">
								<input type="hidden" name="res_no" value="${resterauntVO.res_no}">
								<input type="hidden" name="action" value="reviewFooditem"></th>
						</FORM>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</table>
		</div>
	</div>
	</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 		crossorigin="anonymous"></script> -->
<!-- 	<script -->
<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" -->
<!-- 		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" -->
<!-- 		crossorigin="anonymous"></script> -->
<!-- 	<script -->
<!-- 		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" -->
<!-- 		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" -->
<!-- 		crossorigin="anonymous"></script> -->
		
<c:if test="${successReviewRes.equals(\"true\")}" var="flag" scope="session">
	<%session.removeAttribute("successReviewRes");%>
	<script>
		$(function() {
			alert("已成功審核餐廳基本資料並傳送簡訊請店家新增菜單！");
		});
	</script>
</c:if>
<c:if test="${noChoice.equals(\"true\")}" var="flag" scope="session">
	<%session.removeAttribute("noChoice");%>
	<script>
		$(function() {
			alert("請選擇是否審核通過！");
		});
	</script>
</c:if>

<c:if test="${noNeedRe.equals(\"true\")}" var="flag" scope="session">
	<%session.removeAttribute("noNeedRe");%>
	<script>
		$(function() {
			alert("已審核餐點，該餐廳已無需要審核之餐點！");
		});
	</script>
</c:if>

</body>
</html>