<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*,com.mem.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	MemVO memVO = (MemVO)session.getAttribute("memberVO");
	
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>




<body>

	  <%@ include file="/header.jsp"%>

		
		<div class="container">
			<div class="row">

				<div class="col-12">
<form action="pointtransaction.do" method="post">
					<div class="card">
						<div class="card-body">
							<font size="5"
								style="display: flex; align-items: center; justify-content: center; height: 100%;">儲值點數</font>
							<hr>
							<div style="text-align: center">請輸入您的信用卡卡號</div>
							<br>
							<div class="form-group form-row">
								<div class="col-sm-3">
									<input type="text" id="f1" class="form-control"
										onkeydown="change(1)">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f2" class="form-control" maxlength="4">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f3" class="form-control" maxlength="4">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f4" class="form-control" maxlength="4">
								</div>
							</div>

							卡片有效期限<select style="margin-left: 12px" name="Select_Month"
								id="Select_Month"></select>月
							<script>
								var Select_Month = document
										.getElementById("Select_Month")
								var Month_Start = 1 //起始月 
								var Month_End = 12 //結束月 
								var Month_Len = Month_End - Month_Start + 1;
								Select_Month.options.length = Month_Len;
								for (var i = 0; i < Month_Len; i++) {
									Select_Month.options[i].text = Select_Month.options[i].value = Month_Start
											+ i;
								}
							</script>

							<select name="Select_Year" id="Select_Year"></select>年
							<script>
								var Select_Year = document
										.getElementById("Select_Year")
								var Year_Start = 2019 //起始年 
								var Year_End = 2030 //結束年 
								var Year_Len = Year_End - Year_Start + 1;
								Select_Year.options.length = Year_Len;
								for (var i = 0; i < Year_Len; i++) {
									Select_Year.options[i].text = Select_Year.options[i].value = Year_Start
											+ i;
								}
							</script>
							<br> <br>
							<div>
								<ul class="pagination justify-content-left">
									信用卡驗証碼
									<input name="depositValue" class="title"
										placeholder="請輸入您的信用卡驗証碼" maxlength="3"
										style="margin-left: 12px">
								</ul>
							</div>

							請選擇您要儲值的點數金額 <br> 
							<input style="margin-left: 15px" type="radio" name="point" value="100">100點<br> 
							<input style="margin-left: 15px" type="radio" name="point" value="500">500點<br>
							<input style="margin-left: 15px" type="radio" name="point" value="1000">1000點<br>
							<input style="margin-left: 15px" type="radio" name="point" value="3000">3000點<br>
							<input style="margin-left: 15px" type="radio" name="point" value="5000">5000點<br>
						</div>
					</div>

					<br>

					<div class="col-12">
						<ul class="pagination justify-content-center">
						<input type="hidden" value="${memberVO.mem_no}" name="mem_no">
						<input type="hidden" value="addpoint" name="action">
							<input class="page-link" type="submit" value="儲值">
						</ul>
					</div>
</form>

	<button id="magic" style="opacity:0.1" onclick="setData()">這個</button>
				</div>
			</div>
		</div>



	<script>
		function change(id1) {
			if ($("#f" + id1).val().length == 4) {
				changeNext(id1 + 1);
			}
		}

		function changeNext(id2) {
			var v = "#f" + id2;
			$(v).focus();
			$(v).keyup(function focus2() {
				if ($("#f" + id2).val().length == 4) {
					changeNext(id2 + 1);
				}
			});
			if ($("#f" + 4).val().length == 4) {
				alert("輸入完成");
			}
		}
	</script>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script>
	function setData(){
		$('#f1').val('9487');
		$('#f2').val('9876');
		$('#f3').val('8787');
		$('#f4').val('4887');
		$('#Select_Month').val('8');
		$('#Select_Year').val('2020');
		$('#depositValue').val('987');
		$('input[name="point"]')[4].checked = true; //radio 賦值==>第二個選項選取
	}
	</script>
</body>
</html>