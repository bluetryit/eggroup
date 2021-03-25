<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- fooditem_select_page 首頁 -->
<html>
<head>
<title>FoodItem: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>
<table id="table-1">
   <tr><td><h3>FoodItem: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for FoodItem: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">   
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFooditem.jsp'>List</a> all FoodItem.  <br><br></li>
  

  <% if (session.getAttribute("memberVO") == null && session.getAttribute("resVO") == null) { %>
			<script>
			alert('欲瀏覽其他頁面，請先登入!');
			</script>
			<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/loginhandler" method="post">
				<input class="form-control mr-sm-2 bg-white" type="text" placeholder="member_id" name="member_id">
				<input class="form-control mr-sm-2 bg-white" type="password" placeholder="password" name="mem_pwd">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
			</form>
			<% } else { %>
			<form class="form-inline my-2 my-lg-0"  method="GET" action="<%=request.getContextPath()%>/logouthandler">
				<span><span style="color:blue;">${memberVO.mem_name}</span>，歡迎回來!&nbsp&nbsp</span>
				<span><span>目前點數尚餘:&nbsp</span><span style="color:red;">${memberVO.mem_point}</span>點&nbsp</span>
				&nbsp &nbsp<button style="padding:0px; padding-left:5px; padding-right:5px; height:28px;" type="submit" class="btn btn-secondary"><span style="font-size:12px;">登出</span></button>
			</form>
			<% } %>
  
  
  <li>
    <FORM METHOD="post" ACTION="fooditem.do" >
        <b>輸入餐點編號 (如FO000001):</b>
        <input type="text" name="fo_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>


</ul>


<h3>餐點管理</h3>




<ul>
  <li><a href='addFooditem.jsp'>註冊</a> a new Fooditem.</li>
</ul>

</body>
</html>