<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*, com.tools.FindCodeName"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO"); 

	
	session.getAttribute("adVO");
	session.setAttribute("adVO", adVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改廣告 - update_ad_input_FE.jsp</title>

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
	width: 1600px;
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
	 <a class="btn btn-info" href='<%=request.getContextPath()%>/front-end/ad/listAllAd_FE.jsp'>回上頁</a>
	<FORM METHOD="post" ACTION="ad.do" name="form1"
		enctype="multipart/form-data">
		<table width="800px">

			<tr>
				<td>廣告標題:</td>
				<td><input type="TEXT" name="ad_title" size="45"
					value="<%=(adVO == null) ? "" : adVO.getAd_title()%>" /></td>
			</tr>
			
			<tr>
				<td>廣告內文:</td>
				<td>
					<div class="form-group">
 				 	<label for="exampleFormControlTextarea3"></label>
  					<textarea class="form-control" id="exampleFormControlTextarea3" name="ad_text" cols="50" rows="30"
					style="resize:none;border: 0px;outline:none;" 
					><%=(adVO == null) ? "" : adVO.getAd_text()%></textarea>
  					</div>
  				</td>
			</tr>
			<tr>
				<td>廣告照片:</td>
				<td><input type="file" name="ad_img" size="45"
					accept="image/gif, image/jpeg, image/png" id="imgpv"
					value="<%=adVO.getAd_img()%>" /></td>
				<td><img width="300" height="200" id="preview_img"
					src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}">
				</td>
			</tr>

			<tr>
				<td>廣告刊登開始日期:<font color=red><b>*</b></font></td>
				<td><%=adVO.getAd_start()%></td>
			</tr>

			<tr>
				<td>廣告刊登開始日期:<font color=red><b>*</b></font></td>
				<td><%=adVO.getAd_end()%></td>
			</tr>
				
			
		</table>
		<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="ad_no" value="<%=adVO.getAd_no()%>">
			<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllAd_BE.jsp-->
			<input type="submit" value="送出審核">
	</FORM>
</body>

<script>
	$("#imgpv").change(function() {
		//當檔案改變後，做一些事 
		readURL(this); // this代表<input id="imgInp">
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

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
</html>