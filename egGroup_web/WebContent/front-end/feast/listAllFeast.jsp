<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.feastinfo.model.*, com.tools.FindCodeName"%>

<%
	FeastInfoService feaSvc = new FeastInfoService();
	List<FeastInfoVO> historyList = feaSvc.getAllHistoryFeastInfo();
	pageContext.setAttribute("historyList", historyList);
	
	List<FeastInfoVO> currentList = feaSvc.getAllCurrentFeastInfo();
	pageContext.setAttribute("currentList", currentList);
%>
<jsp:useBean id="FindCodeName" scope="page" class="com.tools.FindCodeName" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />	
<jsp:useBean id="myeSvc" scope="page" class="com.myfeast.model.MyFeastService" />
<!doctype html>
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

<title>列出所有飯局</title>
</head>



<body>
<%@ include file="/header.jsp"%>



	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<div>
				<ul class="nav nav-tabs justify-content-center" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#historyFeast" role="tab">歷史飯局</a></li>

					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#feasting" role="tab">參加中</a></li>
				</ul>
</div>

<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="historyFeast">
	
	
		<div class="container">
		<div class="row ">
		<br>
		
	<!-- 我是飯局-->
				<h1 class="my-4">
					歷史飯局瀏覽: <small>創造一起吃飯的樂趣吧!</small>
				</h1>

				<c:forEach var="feastInfoVO" items="${historyList}">
					
					<c:if test="${myeSvc.getAllMyFeastVOsByFeaNo(feastInfoVO.getFea_no()).contains(myeSvc.getOneMyFeast(feastInfoVO.fea_no, memberVO.mem_no))}">
		
		<!-- 	mx-0 justify-content-center align-items-center w-100 -->
			<div class="col-6">
			<br>
				<div class="card">
				  <div class="card-header">
				  <br>
				  <h3><b>主揪者 :</b>${memSvc.memFindByPrimaryKey(feastInfoVO.fea_memNo).mem_name}</h3>
				  </div>
				  <div class="card-body">
				    <h5 class="card-title"><b>主題 :</b>${feastInfoVO.fea_title}</h5>
				    <p class="card-text">${feastInfoVO.fea_text}</p>
				    <p class="card-text"></p>
				    
				    
				  </div>
				  <ul class="list-group list-group-flush">
				    	<li class="list-group-item">
						<div class="row">
							<div class="form-group col-sm-4">
								<p>
									<b>目前人數 :</b>${feastInfoVO.fea_number}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>人數上限 :</b>${feastInfoVO.fea_upLim}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>人數下限:</b>${feastInfoVO.fea_lowLim}</p>
							</div>
							</div>
						</li>
				    <li class="list-group-item">
						<div class="row">
							<div class="form-group col-sm-4">
								<label><b>飯局日期 :</b></label>
								<p><fmt:formatDate value="${feastInfoVO.fea_date}" pattern="yyyy-MM-dd HH:mm"/></p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>報名截止時間 :</b></label>
								<p><fmt:formatDate value="${feastInfoVO.fea_startDate}" pattern="yyyy-MM-dd HH:mm"/></p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>訂餐截止時間 :</b></label>
								<p><fmt:formatDate value="${feastInfoVO.fea_endDate}" pattern="yyyy-MM-dd HH:mm"/></p>
								</div>
								</div>
				
				    </li>
				    
				    <li class="list-group-item ">
						<div class="row">
							<div class="form-group col-sm-4">
								<p>
									<b>飯局類型 :</b>${feastInfoVO.fea_type}</p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>飯局地點 :</b></label>
								<p>${feastInfoVO.fea_loc}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>飯局狀態 :</b>${FindCodeName.meaning(feastInfoVO.fea_status)}</p>
							</div>
							</div>
					</li>
				  </ul>
				  <div class="card-footer">
				  <div class="row  justify-content-center">
				  
				  
				  
				  <div class="form-group col-sm-3">
					<button type="button" class="btn btn-info" onclick="location.href='<%=request.getContextPath()%>/front-end/post/addPost.jsp?res_no=${feastInfoVO.fea_resNo}'">新增貼文</button>
				  
				  </div>
	
	
	
								
					</div>
					</div>
					
					</div>
		  		</div>
		  		</c:if>
		</c:forEach>
	  </div>
	</div>
	
	</div>
	
		<div role="tabpanel" class="tab-pane" id="feasting">
	
	
		<div class="container">
		<div class="row ">
		<br>

	<!-- 我是飯局-->
				<h1 class="my-4">
					參加中飯局瀏覽: <small>創造一起吃飯的樂趣吧!</small>
				</h1>

				<c:forEach var="feastInfoVO" items="${currentList}">
					
					<c:if test="${myeSvc.getAllMyFeastVOsByFeaNo(feastInfoVO.getFea_no()).contains(myeSvc.getOneMyFeast(feastInfoVO.fea_no, memberVO.mem_no))}">
		
		<!-- 	mx-0 justify-content-center align-items-center w-100 -->
			<div class="col-6">
			<br>
				<div class="card">
				  <div class="card-header">
				  <br>
				  <h3><b>主揪者 :</b>${memSvc.memFindByPrimaryKey(feastInfoVO.fea_memNo).mem_name}</h3>
				  </div>
				  <div class="card-body">
				    <h5 class="card-title"><b>主題 :</b>${feastInfoVO.fea_title}</h5>
				    <p class="card-text">${feastInfoVO.fea_text}</p>
				    <p class="card-text"></p>
				    
				    
				  </div>
				  <ul class="list-group list-group-flush">
				    	<li class="list-group-item">
						<div class="row">
							<div class="form-group col-sm-4">
								<p>
									<b>目前人數 :</b>${feastInfoVO.fea_number}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>人數上限 :</b>${feastInfoVO.fea_upLim}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>人數下限:</b>${feastInfoVO.fea_lowLim}</p>
							</div>
							</div>
						</li>
				    <li class="list-group-item">
						<div class="row">
							<div class="form-group col-sm-4">
								<label><b>飯局日期 :</b></label>
								<p>
									<fmt:formatDate value="${feastInfoVO.fea_date}" pattern="yyyy-MM-dd"/></p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>報名截止時間 :</b></label>
								<p>
									<fmt:formatDate value="${feastInfoVO.fea_startDate}" pattern="yyyy-MM-dd HH:mm"/></p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>訂餐截止時間 :</b></label>
								<p>
								<fmt:formatDate value="${feastInfoVO.fea_endDate}" pattern="yyyy-MM-dd HH:mm"/></p>
								</div>
								</div>
				
				    </li>
				    
				    <li class="list-group-item ">
						<div class="row">
							<div class="form-group col-sm-4">
								<p>
									<b>飯局類型 :</b>${feastInfoVO.fea_type}</p>
							</div>
							<div class="form-group col-sm-4">
								<label><b>飯局地點 :</b></label>
								<p>${feastInfoVO.fea_loc}</p>
							</div>
							<div class="form-group col-sm-4">
								<p>
									<b>飯局狀態 :</b>${FindCodeName.meaning(feastInfoVO.fea_status)}</p>
							</div>
							</div>
					</li>
				  </ul>
				  <div class="card-footer">
				  <div class="row  justify-content-center">
				  
				  
				  
				  <div class="form-group col-sm-3">
					<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/feast/feastinfo.do">

									<input type="hidden" class="form-check-input" name="action"
										value="getOne_For_Display"> <input type="hidden"
										class="form-check-input" name="fea_no"
										value="${feastInfoVO.fea_no}">
									<button type="submit" class="btn btn-primary">進入飯局</button>
					</form>
				  </div>
	
	
	
								
					</div>
					</div>
					
					</div>
		  		</div>
		  		</c:if>
		</c:forEach>
	  </div>
	</div>
	
	</div>	
	
</div>




</body>
</html>