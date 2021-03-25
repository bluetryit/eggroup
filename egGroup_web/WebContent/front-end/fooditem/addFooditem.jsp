<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fooditem.model.*"%>

<%
	FooditemVO	fooditemVO = (FooditemVO) request.getAttribute("fooditemVO");

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>餐點資料新增 - addFooditem.jsp</title>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/address.js"></script>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>

<script>
	$(document).ready(function() {

		var readURL = function(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.avatar').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$(".file-upload").on('change', function() {
			readURL(this);
		});
	});
</script>
</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>




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
	<input type="hidden" name="fo_resno" size="45"  value="${resVO.res_no}" />
	<div class="container">
	<br>
	
	

	<div class="row justify-content-center">
	<div class="col-sm-7">
	<div class="row">
		<div class="form-group col-sm-6">
		<h4>餐點名稱 :</h4><input type="TEXT" name="fo_name" class="form-control" id="fo_name"
			 value="<%= (fooditemVO==null)? "名稱" : fooditemVO.getFo_name()%>"/>
		
	</div>
		<div class="form-group col-sm-6">
		<h4>價格:</h4>
		<input class="form-control" type="TEXT" name="fo_price" id="fo_price"
			 value="<%= (fooditemVO==null)? "0" : fooditemVO.getFo_price()%>" />
	</div></div>
	<div class="row">
		<div class="form-group col-12">
		<h4>介紹:</h4>
		<textarea name="fo_intro" class="form-control" id="fo_intro" rows="5"><%= (fooditemVO==null)? "10000" : fooditemVO.getFo_intro()%></textarea>
	</div></div>
	<div class="row">
		<div class="form-group col-sm-6">
		<h4>圖片 :</h4>
		<input type="file" name="fo_img" class="text-center center-block file-upload"
			 value="<%=(fooditemVO==null)? "圖片" : fooditemVO.getFo_img()%>" />
		</div>
		<div class="form-group col-sm-6">
		<img src="<%=request.getContextPath()%>/images/no.gif" width="100%" class="avatar" alt="avatar" >
		</div>
	</div>
		<div class="row justify-content-center">
		<div class="form-group col-sm-3">
			 <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增" class="btn btn-info">
		</div>
			</FORM>
		<div class="form-group col-sm-3">
			<a class="btn btn-outline-secondary"  href="<%=request.getContextPath()%>/front-end/fooditem/listOneFooditem.jsp">回上頁</a>
		</div>
		</div>
			
	
		


</div>
</div>
</div>

			<table align="center">
			<tr>
			<td><button id="magic"  style="opacity:0.1" onclick="setData1()">這個1</button></td>
			<td><button id="magic"  style="opacity:0.1" onclick="setData2()">這個2</button></td>
			<td><button id="magic"  style="opacity:0.1" onclick="setData3()">這個3</button></td>
			</tr>
			</table>	
</body>

<script type="text/javascript">
		function setData1(){
			$('#fo_name').val('超脆皮比薩');
			$('#fo_price').val('500');
			$('#fo_intro').val('e式脆皮烘烤，香香好吃');
		}
		
		function setData2(){
			$('#fo_name').val('炙火燒肉');
			$('#fo_price').val('404');
			$('#fo_intro').val('骰子火烤多汁嫩肉');
		}
		
		function setData3(){
			$('#fo_name').val('蜂蜜啤酒');
			$('#fo_price').val('200');
			$('#fo_intro').val('單一純麥，多泡冰鎮，均一價!!');
		}
</script>
</html>