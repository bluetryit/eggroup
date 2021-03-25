<%@page import="java.time.LocalTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*,com.resac.model.*,com.tools.FindCodeName,java.text.SimpleDateFormat"%>

<%
	ResAcVO resAcVO = (ResAcVO) request.getAttribute("resAcVO"); //ResAcServlet.java (Concroller) 存入req的resAcVO物件 (包括幫忙取出的resAcVO, 也包括輸入資料錯誤時的resAcVO物件)
	session.setAttribute("resAcVO", resAcVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_res_input.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

<%@ include file="/header.jsp"%>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="resAc.do" name="form1"
		enctype="multipart/form-data">
		<table width="800px">
			<tr>
				<td>餐廳編號:<font color=red><b>*</b></font></td>
				<td><%=resAcVO.getResac_resno()%></td>
			</tr>
			<tr>
				<td>員工編號:<font color=red><b>*</b></font></td>
				<td><%=resAcVO.getResac_no()%></td>
			</tr>
			<tr>
				<td>員工姓名:<font color=red><b>*</b></font></td>
				<td><%=resAcVO.getResac_name()%></td>
			</tr>
			<tr>
				<td>員工密碼:</td>
				<td><input type="password" name="resac_pass" size="45"
					value="<%=resAcVO.getResac_pass()%>" /></td>
			</tr>

			<tr>
				<td>密碼確認:</td>
				<td><input type="password" name="resac_pass2" size="45"
					value="<%=resAcVO.getResac_pass()%>" /></td>
			</tr>
			<tr>
				<td>員工電話:</td>
				<td><input type="TEXT" name="resac_phone" size="45"
					value="<%=resAcVO.getResac_phone()%>" /></td>
			</tr>

			<tr>
				<td>餐廳照片:</td>
				<td><input type="file" name="resac_pic" size="45"
					accept="image/gif, image/jpeg, image/png" id="imgpv"
					value="<%=resAcVO.getResac_pic()%>" /></td>
				<td><img width="300" height="200" id="preview_img"
					src="<%=request.getContextPath()%>/back-end/resAc/resAcPhoto.do?resac_no=${resAcVO.resac_no}&resac_resno=${resAcVO.resac_resno}">
				</td>
			</tr>

			<tr>
				<td>員工介紹:</td>
				<td><input type="TEXT" name="resac_intro" size="45"
					value="<%=resAcVO.getResac_intro()%>" /></td>
			</tr>	
			
	<tr>
	<%int i = 0;%>
	<td>員工狀態:<font color=red><b>*</b></font></td>
		<td><select size="1" name="resac_status">
			<c:forEach var="i" begin="1" end="3" step="1">
				<%String status="resac" + ++i; %>
				<option value="<%=status%>"
				<%=(resAcVO != null && (resAcVO.getResac_status()).equals(status))? "selected" : "" %>
				><%=FindCodeName.meaning(status) %>
			</c:forEach>
		</select></td>
	</tr>
	
		
						</table>
		<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="resac_resno" value="<%=resAcVO.getResac_resno()%>">
			<input type="hidden" name="resac_no" value="<%=resAcVO.getResac_no()%>">
			<input type="hidden" name="resac_name" value="<%=resAcVO.getResac_name()%>">
			<input type="submit" value="送出修改">
	</FORM>
</body>

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

<script>
$("#imgpv").change(function(){
    //當檔案改變後，做一些事 
   readURL(this);   // this代表<input id="imgInp">
 });
function readURL(input){
	  if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $("#preview_img").attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
	}
</script>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</html>