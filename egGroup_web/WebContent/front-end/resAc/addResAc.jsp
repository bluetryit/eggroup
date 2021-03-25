<%@page import="java.time.LocalTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*,com.resac.model.*,java.text.SimpleDateFormat"%>

<%
	ResAcVO resAcVO = (ResAcVO) request.getAttribute("resAcVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addResAc.jsp</title>

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

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}" >
		<font style="color: red" >請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="resAc.do" name="form1" enctype="multipart/form-data">
		<table width="1000px">
			<tr>
				<td>員工編號:</td>
				<td><input type="TEXT" name="resac_no" size="45" id="resac_no"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_no()%>" /></td>
			</tr>
			
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="resac_name" size="45" id="resac_name"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_name()%>" /></td>
			</tr>
			
			<tr>
				<td>員工密碼:</td>
				<td><input type="password" name="resac_pass" size="45" id="resac_pass"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_pass()%>" /></td>
			</tr>

			<tr>
				<td>密碼確認:</td>
				<td><input type="password" name="resac_pass2" size="45" id="resac_pass2"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_pass()%>" /></td>
			</tr>
			<tr>
				<td>員工電話:</td>
				<td><input type="TEXT" name="resac_phone" size="45" id="resac_phone"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_phone()%>" /></td>
			</tr>

			<tr>
				<td>員工照片:</td>
				<td><input type="file" name="resac_pic" size="20" 
					accept="image/gif, image/jpeg, image/png"
					id="imgpv1"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_pic()%>" /></td>
				<td><img width="300" height="200" id="preview_img1" src="<%=request.getContextPath()%>/images/noimg.jpg"></td>	
			</tr>

			<tr>
				<td>員工介紹:</td>
				<td><input type="TEXT" name="resac_intro" size="20" id="resac_intro"
					value="<%=(resAcVO == null) ? "" : resAcVO.getResac_intro()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
		<button id="magic" style="opacity:0.1" onclick="setData()">這個</button>
</body>


<script>
$("#imgpv1").change(function(){
    //當檔案改變後，做一些事 
   readURL(this);   // this代表<input id="imgInp">
 });
function readURL(input){
	  if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $("#preview_img1").attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
	}
</script>
<script type="text/javascript">
		function setData(){
			$('#resac_no').val('000001');
			$('#resac_name').val('皮卡丘');
			$('#resac_pass').val('Aa123456');
			$('#resac_pass2').val('Aa123456');
			$('#resac_phone').val('0987987987');
			$('#resac_intro').val('我愛工作 皮卡');
		}
</script>

</html>