<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fooditem.model.*"%>

<jsp:useBean id="fooditemVO" scope="request" class="com.fooditem.model.FooditemVO" />


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>餐點資料修改 - update_fooditem_input.jsp</title>


<style>
  table {
	width: 450px;
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

<script type="text/javascript">
	var option = document.getElementsByClassName("typeSelect");
	var select = "<%=fooditemVO.getFo_status()%>";
	
	if(select == ("上架"))
	{
		$('.typeSelect')[1].selected="selected";
	}
	else if(select == ("下架"))
	{
		$('.typeSelect')[2].selected="selected";
	}

</script>

</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>


<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="fooditem.do" name="form1" enctype="multipart/form-data">
<div class="container">
	<div class="row justify-content-center">
	<div class="col-sm-7">
	<div class="row">
		<div class="col-sm-6">
		<h4><b>名稱:</b></h4>
		<input type="TEXT" name="fo_name" class="form-control" value="<%=fooditemVO.getFo_name()%>" />
		</div>
		<div class="form-group col-sm-6">
		<h4><b>價格:</b></h4>
		<input type="TEXT" name="fo_price" class="form-control" value="<%=fooditemVO.getFo_price()%>" />
		</div></div>
		<div class="row">
		<div class="form-group col-sm-12">
		<h4><b>介紹:</b></h4>
		<textarea name="fo_intro" class="form-control" rows="5"><%=fooditemVO.getFo_intro()%></textarea>
		</div></div>
		<div class="row">
		<div class="form-group col-sm-5">
		餐點圖片:
		<input type="file" name="fo_img"
			accept="image/gif, image/jpeg, image/png" id="imgpv"
			value="<%=fooditemVO.getFo_img()%>"/>
	
	<c:if test="${!fooditemVO.fo_status.equals(\"fo3\")}">
		<tr>
			<td>狀態:</td>
			<td>
					<select class="browser-default custom-select" class="form-control"  name="fo_status"> 
							<option class="typeSelect" value="fo1">上架</option>
							<option class="typeSelect" value="fo2">下架</option>
					</select>
			</td>
		</tr>
	</c:if>
	
	<c:if test="${fooditemVO.fo_status.equals(\"fo3\")}">
		<tr> <td>狀態:  餐點待審核</td> </tr>
		<input type="hidden" name="fo_status" size="45"	value="fo3" />
	</c:if>
</div>

		<div class="form-group col-sm-7">
		<img  width="100%" id="preview_img" src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=${fooditemVO.fo_no}">
		</div></div>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="fo_no" value="<%=fooditemVO.getFo_no()%>">
<div class="row justify-content-center">
<div class="col-sm-3">
<input type="submit" value="送出修改" class="btn btn-info">
</div>
<div class="col-sm-3">
<h4><a  href="<%=request.getContextPath()%>/front-end/fooditem/listOneFooditem.jsp">回上頁</a></h4>
</div></div>
</div>
</div>
</div>
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

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
</html>