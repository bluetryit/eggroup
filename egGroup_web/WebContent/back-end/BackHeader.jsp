<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportlm.model.*"%>

<!DOCTYPE html>
<html lang="en">

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
<style>
body{
font-family:"微軟正黑體";
background-color: #dedede;
}
</style>

</head>

<body>

<nav class="navbar navbar-expand-lg  navbar-light "style="background-color: #7c4e24;">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <img src="<%=request.getContextPath()%>/images/lol.gif" style="width: 2%;height: 5%;">
  <a class="navbar-brand" href="#" style="color:#fff;">EGG後台</a>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a style="color:#fff;" class="nav-link" href="<%=request.getContextPath()%>/back-end/BackHome.jsp"> 首頁 <span class="sr-only">(current)</span></a>
      </li>
            
       <li class="nav-item">
        <a class="nav-link " href="#"> </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link btn btn-light" href="<%=request.getContextPath()%>/back-end/res/reviewRes.jsp">店家管理</a>
      </li>
      
       <li class="nav-item">
        <a class="nav-link " href="#"> </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link btn btn-light" href="<%=request.getContextPath()%>/back-end/pointtransaction/listAllPointtransaction.jsp">點數管理</a>
      </li>
            
       <li class="nav-item">
        <a class="nav-link " href="#"> </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link btn btn-light" href="<%=request.getContextPath()%>/back-end/ad/listAllAd_BE.jsp">廣告管理</a>
      </li>
            
       <li class="nav-item">
        <a class="nav-link " href="#"> </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link btn btn-light" href="<%=request.getContextPath()%>/back-end/reportlm/reportLm.jsp">檢舉管理</a>
      </li>
      
    </ul>
    
    <form class="form-inline">
     
    </form>
    
  </div>
</nav>




<%-- 	--updateSuccess--${updateSuccess} --%>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
	<c:if test="${update.equals(\"true\")}" var="flag" scope="request">
		<%
			session.removeAttribute("updateSuccess");
		%>
		<script>
			$(function() {
				$('#v-pills-report-tab').trigger('click');
			});
		</script>
	</c:if>
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
