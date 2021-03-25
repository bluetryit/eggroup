<%@page import="oracle.net.aso.a"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooditem.model.*"%>
<%@ page import="com.mem.model.*,com.res.model.*,com.feastinfo.model.*"%>
<%! @SuppressWarnings("unchecked") %>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
 	<title>Welcome to EGG !</title>
<script> 	
 	$(document).ready(function() {
		$(".btn-delete").click(function() {
			var btn = this;
			$.ajax({
				type : "POST",
				url : "ord.do",
				data : $(this).parent().serialize(),
				dataType : "json",
				success : function(res) {
					var cartAmount = res.cartAmount
					$("#tdtotal").text('$'+cartAmount);
					$(btn).parents("tr").remove();
					if(res.cartAmount==0){
						alert("目前購物車空空如也");
						window.location.href="http://localhost:8081/DA101G6/front-end/ord/listAllFooditem.jsp"};
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		})
		$("select").change(function(){
			$.ajax({
				type : "POST",
				url : "ord.do",
				data : createQueryString($(this).val(), $(this).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartAmount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
		$(".form-control").change(function(e){
			$.ajax({
				type : "POST",
				url : "ord.do",
				data : createQueryString($(this).val(), $(this).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartAmount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
		$(".ord").click(function(){
			var put;
			if(this.name=='add'){
			put = $(this).parent().prev();
			var value = parseInt($(this).parent().prev().val(), 10);
			value = isNaN(value) ? 0 : value;
			value++;
			put.val(value);
			}else{
				put = $(this).parent().next();
				var value = parseInt($(this).parent().next().val(), 10);
				value = isNaN(value) ? 0 : value;
				value <= 1 ? value = 1 : value--;
				put.val(value);
			}
			$.ajax({
				type : "POST",
				url : "ord.do",
				data : createQueryString($(put).val(), $(put).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartAmount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
	});
	function createQueryString(fo_quantity, fo_no){
		var queryString= {"action":"modifyQuantity", "fo_quantity":fo_quantity, "fo_no":fo_no};
		return queryString;
	}
</script>

<style>
table{
	width:720px;
	margin-left:auto; 
	margin-right:auto;
}
#emptycart{
	font-size:72px;
	font-weight:bold;
	color:#ff8080;
	margin-left:auto; 
	margin-right:auto;
}
#tdtotal{
	color:red;
	font-weight:bold;
}
.form-control{
	width:20px;
}
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
  -webkit-appearance: none; 
  margin: 0; 
}
#imgflow {
	display: block;
	margin-left: auto;
	margin-right: auto;
	max-width: 230px;
	max-height: 95px;
	width: auto;
	height: auto;
}
th{
	text-align:center;
}
table img{
	width:100px;
	height:60px;
}

</style>	
</head>
<body>
<%@ include file="/header.jsp"%>


<hr><p>

	
	<%
		FeastInfoVO feaVO = (FeastInfoVO)session.getAttribute("feastInfoVO");
		Vector<FooditemVO> buylist = (Vector<FooditemVO>) session.getAttribute("shoppingCart");
		String amount =  String.valueOf(session.getAttribute("total"));
		MemVO memVO = (MemVO)session.getAttribute("memberVO");
		Integer total = new Integer(amount);
	%>	
	<%	if(buylist != null && buylist.size() != 0){ %>
		<table>
		<tr>
			<th>餐點編號</th>
			<th>餐點圖片</th>
			<th>餐點名稱</th>
			<th>餐點金額</th>
			<th width="120px">數量</th>
			<th></th>
		</tr>
	<% 	for (int i = 0; i < buylist.size(); i++) {
			FooditemVO order = buylist.get(i);
			String fo_no = order.getFo_no();
			String fo_name = order.getFo_name();
			int fo_price = order.getFo_price();
			int fo_quantity = order.getFo_quantity();
	%>
	<tr>
		<td><div align="center"><b><%=fo_no%></b></div></td>
		<td align="center"><img alt="img" src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=<%=order.getFo_no()%>"/></td>
		<td><div align="center"><b><%=fo_name%></b></div></td>
		<td><div align="center"><b><%=fo_price%></b></div></td>
			<td width="80px">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<button class="ord btn btn-outline-secondary" type="button"
							id="button-addon1" name="minus">-</button>
					</div>
					<input type="number" class="form-control" placeholder=""
						aria-label="Example text with button addon"
						aria-describedby="button-addon1" value="<%=fo_quantity%>" name="<%=fo_no%>">
					<div class="input-group-prepend">
						<button class="ord btn btn-outline-secondary" type="button"
							id="button-addon2" name="add">+</button>
					</div>	
				</div>
			</td>
			<td><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/front-end/ord/ord.do" method="POST">
              <input type="hidden" name="action" value="remove">
              <input type="hidden" name="remove" value="<%= fo_no %>">
              <input type="button" class="btn-delete" value="取消">
          </form>
        </div></td>
	</tr>
	<%}%>
	<tr>
		<td></td>
		<td></td>
		
		<td><div align="center"><font color="red"><b>消費總金額：</b></font></div></td>
		<td id="tdtotal"> $<%=amount%></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		
		<td><div align="center"><font color="red"><b>目前點數尚餘：</b></font></div></td>
		<td id="tdtotal">$${memberVO.mem_point}</td>
		<td></td>
	</tr>
	<%-- <tr>
		<td></td>
		<td></td>
		
		<td><div align="center"><font color="red"><b>消費後剩下：</b></font></div></td>
		<td id="tdtotal">$<%=memVO.getMem_point()-total%> </td>
		<td></td>
	</tr> --%>
</table>
	<%}else{%>
	<div id="emptycart">目前購物車空空如也喔</div>	
	<%}%>
<div>
<div align="center">
<button onclick="location.href='ord.do?res_no=<%=feaVO.getFea_resNo()%>&action=showFoodsInfo">繼續購物</button>
<br>
<form method="GET" action="<%=request.getContextPath()%>/front-end/ord/ord.do">
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="mem_no" value="<%=memVO.getMem_no()%>">
		<input type="hidden" name="ord_fea_no" value="<%=feaVO.getFea_no()%>">
		<input type="hidden" name="ord_type" value="<%=feaVO.getFea_type()%>">
		<input type="submit" id="subm" value="結帳" class="btn-primary">
</form>
</div>
</div>	

<c:if test="${noMoney.equals(\"true\")}" var="flag" scope="session">
<%session.removeAttribute("noMoney");%>
	<script>
		$(function() {
			alert("尚餘點數不足，請儲值");
		});
	</script>
</c:if>

	
</body>
</html>
