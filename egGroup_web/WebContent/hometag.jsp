<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.feastinfo.model.*, com.myfeast.model.*,com.ad.model.*"%>

<%
    FeastInfoService feaSvc = new FeastInfoService();
    List<FeastInfoVO> list = feaSvc.getAllFeastInfoVOsRandomly();
    
    pageContext.setAttribute("list", list);
    
    //***************************廣告要用的辣*******************************
    AdService adSvc = new AdService();
    List<AdVO> listAd = adSvc.getAllAding();
    Collections.reverse(listAd);
    pageContext.setAttribute("listAd", listAd);
    int CountAd = 1;
    
    //***************************廣告要用的辣*******************************//
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="myeSvc" scope="page" class="com.myfeast.model.MyFeastService" />

<!DOCTYPE html>
<html lang="en">

<head>




<title>Welcome to EGG !</title>

<!-- Bootstrap core CSS -->
<style>

#card{
background-color: #ED8532;
}




</style>

</head>

<body>

	<!-- Navigation 上方NAVBAR -->
	<%@ include file="/header.jsp"%>


	<!--廣告牆-->
	<div class="container">
		<div class="bd-example">
			<div id="carouselExampleCaptions" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
				<c:forEach var="i" items="${listAd}" begin="0" end="0">
					<li data-target="#carouselExampleCaptions" data-slide-to="i"
						class="active"></li>
				</c:forEach>
				<c:forEach var="i" items="${listAd}" begin="1">		
					<li data-target="#carouselExampleCaptions" data-slide-to="i"></li>
				</c:forEach>
				</ol>
				
				<div class="carousel-inner">
				<c:forEach var="adVO" items="${listAd}" begin="0" end="0">
					<div class="carousel-item active">
						<a href="<%=request.getContextPath()%>/front-end/ad/ad.do?ad_no=${adVO.ad_no}&action=showAdInfo">
							<img src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}"
								 alt="..." style="width:1080px; height:500px;">
						</a>
						<div class="carousel-caption d-none d-md-block">
							<h5>第<%=CountAd%>廣告區</h5>
							<p>${adVO.ad_title}</p>
							<%CountAd++;%>
						</div>
					</div>
				</c:forEach>
				<c:forEach var="adVO" items="${listAd}" begin="1">
					<div class="carousel-item">
						<a href="<%=request.getContextPath()%>/front-end/ad/ad.do?ad_no=${adVO.ad_no}&action=showAdInfo">
							<img src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}"
								 alt="..." style="width:1080px; height:500px;">
						</a>
						<div class="carousel-caption d-none d-md-block">
							<h5>第<%=CountAd%>廣告區</h5>
							<p>${adVO.ad_title}</p>
							<%CountAd++;%>
						</div>
					</div>
				</c:forEach>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleCaptions"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleCaptions" 
						role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>




		<!-- Page Content -->
		<div class="container">

			<!-- Heading Row -->
			<div class="row align-items-center my-5">
				<div class="col-lg-7">
					<img class="img-fluid rounded mb-4 mb-lg-0"
					src="<%=request.getContextPath()%>/images/MAP.jpg" style="width:750px;height:400px;">
				</div>

				<div class="col-lg-5">
					<h1 class="font-weight-light">來看看附近美食吧!</h1>
					<p>還在想要吃什麼嗎?加入會員，成為主揪，與他人製造美好的相遇吧!</p>

					
					<a class="btn btn-warning" href="<%=request.getContextPath()%>/restaurant.jsp">點我進入美食搜尋!</a>


				</div>
				<!-- /.col-md-4 -->
			</div>
			<!-- /.row -->


			<!-- Call to Action Well -->
			
			<div class="card text-dark  my-5 py-4 text-center" id="card">
				<img class="img" src="<%=request.getContextPath()%>/images/interPost.jpg" alt="">
				<div class="card-body" id="post">
					<a href="<%=request.getContextPath()%>/front-end/post/post.jsp"><p class="text-white m-0">進入評價貼文!</p></a>
				</div>
			</div>
		</div>



		<!-- Content Row -->
		<div class="row">
			<!-- Page Content -->
			<div class="container">

				<!-- 我是飯局-->
				<h1 class="my-4">
					飯局瀏覽: <small>創造一起吃飯的樂趣吧!</small>
				</h1>



				<%@ include file="/front-end/page1.file"%>

				<c:forEach var="feastInfoVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					
						<!-- Project One -->
						<div class="row">
							<div class="col-md-7">

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/feast/feastinfo.do">

									<input type="hidden" class="form-check-input" name="action"
										value="getOne_For_Display"> <input type="hidden"
										class="form-check-input" name="fea_no"
										value="${feastInfoVO.fea_no}">
									<button type="submit" style="background-color: #D9C5A8;width: 80%;background: transparent;border-color: #D9C5A8;border-style: solid;">
										<img class="img-fluid rounded mb-3 mb-md-0"
											src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=${feastInfoVO.fea_resNo}" alt="">
									</button>
								</form>

							</div>
							<div class="col-md-5">
								<h3>標題:${feastInfoVO.fea_title}</h3>

								<p>${feastInfoVO.fea_text}</p>
								<p>發起人:${memSvc.memFindByPrimaryKey(feastInfoVO.fea_memNo).mem_name}</p>
								
								<div class="row">	
								<div class="progress" style="width:100%">
								  <div class="progress-bar bg-warning" role="progressbar" style="width: ${100*(feastInfoVO.fea_number/feastInfoVO.fea_upLim)}%"
									   aria-valuenow="${feastInfoVO.fea_number}" aria-valuemin="${feastInfoVO.fea_lowLim}" aria-valuemax="${feastInfoVO.fea_upLim}">
								  </div>
								</div>
							</div>
							<div class="row" style="justify-content:space-between">
								<div>
									<p>下限:${feastInfoVO.fea_lowLim}人</p>
								</div>
								<div>
									<p >現在人數:${feastInfoVO.fea_number}</p>
								</div>
								<div>
									<p>上限:${feastInfoVO.fea_upLim}人</p>
								</div>
							</div>

								<p>
									開團時間:
									<fmt:formatDate value="${feastInfoVO.fea_date}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</p>
								<p>類型:${feastInfoVO.fea_type}</p>
								<p>地址:${feastInfoVO.fea_loc}</p>

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/feast/feastinfo.do">

									<input type="hidden" class="form-check-input" name="action"
										value="getOne_For_Display"> <input type="hidden"
										class="form-check-input" name="fea_no"
										value="${feastInfoVO.fea_no}">
									<button type="submit" class="btn btn-primary">JOIN!</button>
								</form>
							</div>
						</div>
						<!-- /.row -->
				</c:forEach>

				<hr>
				<%@ include file="/front-end/page2.file"%>

			</div>
			<!-- /.container -->

			<!-- /.col-md-4 -->


		</div>

	</div>
	<br>
	<br>



<%@ include file="footer.jsp"%>




</body>

</html>
