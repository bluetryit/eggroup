<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportlm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ReportLmService reportlmSvc = new ReportLmService();
	List<ReportLmVO> list = reportlmSvc.getAllUnprocesse();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<title>檢舉處理</title>

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
	<jsp:useBean id="lmSvc" scope="page"
		class="com.leavemessage.model.LeaveMessageService" />
		
		
		<%@ include file="/back-end/BackHeader.jsp"%>
		<div class="row justify-content-center" >
	<div class="col-sm-5 ">

<br>

		<table id="table-1" >
			<tr>
				<td>
					<h3>所有被檢舉資料</h3>
				</td>
			</tr>
		</table>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table>
			<tr>
				<th width="10%">留言檢舉編號</th>
				<th width="10%">被檢舉的留言編號</th>
				<th>會員編號</th>
				<th width="20%">檢舉內容</th>
				<th>留言內容</th>
				<th width="80%">審查狀態</th>
			</tr>


			<c:forEach var="ReportLmVO" items="${list}">
				<tr>
					<td>${ReportLmVO.repolm_no}</td>
					<td>${ReportLmVO.repolm_lmano}</td>
					<td>${ReportLmVO.repolm_memno}</td>
					<td>${ReportLmVO.repolm_text}</td>
					<td width="40% auto">${lmSvc.getOneLeaveMessage(ReportLmVO.repolm_lmano).lm_text}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/reportlm/reportLM.do"
							style="margin-bottom: 0px;">

							<select class="brower-default custom-select"
								class="form-control btn-danger" name="repolm_status">

								<option selected value="repolm3">未處理</option>
								<option value="repolm1">通過</option>
								<option value="repolm2">未通過</option>
							</select>

							<th><input type="submit" value="確認修改"> <input
								type="hidden" name="repolm_no" value="${ReportLmVO.repolm_no}">
								<input type="hidden" name="repolm_lmano"
								value="${ReportLmVO.repolm_lmano}"> <input type="hidden"
								name="action" value="update"></th>
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

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
</body>
</html>