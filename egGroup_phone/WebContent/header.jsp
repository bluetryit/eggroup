<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!-- Navigation 上方NAVBAR -->

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/imges/lol.gif" width="10px">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/small-business.css"
	rel="stylesheet">
<style>
body {
	font-family: "微軟正黑體";
	background-color: #D9C5A8;
}

#post {
	background-color: #ED8532;
}

#logout {
	background-color: #F4D03F;
}

.navbar .navbar-nav .nav-item .nav-link {
	position: relative;
}

.navbar .navbar-nav .nav-item .nav-link::before {
	content: '';
	position: absolute;
	left: 50%;
	width: 100%;
	height: 2px;
	background-color: #3cefff;
	transform-origin: center;
	transform: translateX(-50%) scaleX(0);
	transition: transform 0.4s ease;
}

.navbar .navbar-nav .nav-item .nav-link::before {
	top: 90%;
}

.navbar .navbar-nav .nav-item .nav-link::after {
	bottom: 0%;
}

.navbar .navbar-nav .nav-item .nav-link:hover::before, span:hover::after
	{
	transform: translateX(-50%) scaleX(1);
}

.navbar {
	background-color: #ea771b;
}

.navbar .navbar-brand {
	color: #603514;
}

.navbar .navbar-brand:hover, .navbar .navbar-brand:focus {
	color: #fff;
}

.navbar .navbar-text {
	color: #603514;
}

.navbar .navbar-text a {
	color: #4e237e;
}

.navbar .navbar-text a:hover, .navbar .navbar-text a:focus {
	color: #4e237e;
}

.navbar .navbar-nav .nav-link {
	color: #603514;
	border-radius: .25rem;
	margin: 0 0.25em;
}

.navbar .navbar-nav .nav-link:not (.disabled ):hover, .navbar .navbar-nav .nav-link:not
	 (.disabled ):focus {
	color: #fff;
}

footer {
	background-color: #ED8532;
}

.login-container {
	margin-top: 5%;
	margin-bottom: 5%;
}

.login-form-1 {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
	background-color: #fff;
}

.login-form-1 h3 {
	text-align: center;
	color: #333;
}

.login-form-2 {
	padding: 5%;
	background: #eb8019;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.login-form-2 h3 {
	text-align: center;
	color: #fff;
}

.login-container form {
	padding: 10%;
}

.btnSubmit {
	width: 50%;
	border-radius: 1rem;
	padding: 1.5%;
	border: none;
	cursor: pointer;
}

.login-form-1 .btnSubmit {
	font-weight: 600;
	color: #fff;
	background-color: #eb8019;
}

.login-form-2 .btnSubmit {
	font-weight: 600;
	color: #7c4e24;
	background-color: #fff;
}

.login-form-2 .ForgetPwd {
	color: #fff;
	font-weight: 600;
	text-decoration: none;
}

.login-form-1 .ForgetPwd {
	color: #0062cc;
	font-weight: 600;
	text-decoration: none;
}
</style>

<!-- Navigation 上方NAVBAR -->

<nav class="navbar navbar-expand-lg navbar-default bg-default fixed-top">
	<div class="container">

		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/hometag.jsp">Start For EGG!</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<%
						if (session.getAttribute("memberVO") != null) {
					%>
				
				<li class="nav-item active">
					<form class="form-inline my-2 my-lg-0" method="GET"
						action="<%=request.getContextPath()%>/logouthandler">
						<span><span style="color: blue;">${memberVO.mem_name}</span>，歡迎回來!&nbsp&nbsp</span>
						<span><span>目前點數尚餘:&nbsp</span><span style="color: green;">${memberVO.mem_point}</span>點&nbsp</span>
						&nbsp &nbsp

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown1" href="#"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> 設定</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<a class="dropdown-item"
									href="<%=request.getContextPath()%>/front-end/mem/mem.jsp">個人檔案設定</a>
								<a class="dropdown-item" href="#">飯局管理</a> <a
									class="dropdown-item" href="front-end/message/chatroom.jsp">聊天室</a> <a
									class="dropdown-item" href="#">...要甚麼自己加</a>

								<li class="nav-item"><input id="logout" type="submit"
									class="btn" name="登出" value="登出"></li>
							</div>
					</form>
				</li>


				<%
					} else if (session.getAttribute("resVO") != null) {
				%>
				<li class="nav-item active">
					<form class="form-inline my-2 my-lg-0" method="GET"
						action="<%=request.getContextPath()%>/logouthandler">
						<span><span style="color: blue;">${resVO.res_name}</span>，歡迎回來!&nbsp&nbsp</span>
						<span><span>目前點數尚餘:&nbsp</span><span style="color: green;">${resVO.res_point}</span>點&nbsp</span>
						&nbsp &nbsp

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> 餐廳檔案設定 </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">...</a> <a
									class="dropdown-item" href="#">...</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">...</a>
							</div></li>
						<li class="nav-item"><input id="logout" type="submit"
							class=" btn" name="登出" value="登出"></li>
					</form> <%
 	} else {
 %> <a data-toggle="modal" class="btn" href="#"
					data-target="#exampleModal" id="loginBtn">登入</a>
				</li>

				<%
					}
				%>

			</ul>

		</div>
	</div>
</nav>


<!-- 登入跳出視窗II -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="col-lg-12">
			<div class="modal-content">


				<ul class="nav nav-pills" role="tablist">
					<li class="nav-item"><a class="nav-link active btn-light"
						data-toggle="tab" href="#MemLogin" role="tab">會員登入</a></li>

					<li class="nav-item"><a class="nav-link  btn-light"
						data-toggle="tab" href="#ShopLogin" role="tab">商家登入</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="MemLogin">
						<div class="modal-body col-12 login-form-1">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>會員登入</h3>
							<form action="<%=request.getContextPath()%>/loginhandler"
								method="post">
								<input type="hidden" name="action" value="memLogin">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="請輸入會員帳號"
										value="" name="member_id" />
								</div>
								<div class="form-group">
									<input type="password" class="form-control"
										placeholder="請輸入會員密碼" value="" name="mem_pwd" />
								</div>
								<div class="form-group">
									<input type="submit" class="btnSubmit" value="Login" /> <font
										style="color: red">&nbsp;&nbsp;${errorMsgsForLogin.member}</font>
								</div>
								<div class="form-group">
									<a href="#" class="ForgetPwd">註冊成為會員</a>
								</div>
							</form>
							<div class="modal-footer">
								<button id="BtnS" type="button" class="btn btn-secondary"
									data-dismiss="modal">關閉</button>
							</div>
						</div>
					</div>

					<div role="tabpanel" class="tab-pane fade" id="ShopLogin">
						<div class="modal-body col-12 login-form-2">
							<h3>商家登入</h3>
							<form action="<%=request.getContextPath()%>/loginhandler"
								method="post">
								<input type="hidden" name="action" value="resLogin">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="請輸入商家帳號"
										value="" name="res_ac" />
								</div>
								<div class="form-group">
									<input type="password" class="form-control"
										placeholder="請輸入商家密碼" value="" name="res_pass" />
								</div>
								<div class="form-group">
									<input type="submit" class="btnSubmit" value="Login" /> <font
										style="color: red">&nbsp;&nbsp;${errorMsgsForLogin.resMember}</font>
								</div>
								<div class="form-group">
									<a href="#" class="ForgetPwd">註冊成為商家會員</a>
								</div>

							</form>
							<div class="modal-footer">
								<button id="BtnS" type="button" class="btn btn-secondary"
									data-dismiss="modal">關閉</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>






<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

<c:if test="${login.equals(\"false\")}" var="flag">
	<%
		session.removeAttribute("login");
	%>
	<script>
		$(function() {
			$('#loginBtn').trigger('click');
		});
	</script>
</c:if>
