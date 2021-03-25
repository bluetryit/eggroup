<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.res.model.*"%>



<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<!--                                                                                       -->
<style type="text/css">
	body {
	font-family: "微軟正黑體";
	}

	.title {
		display: flex;
		justify-content: space-between;
	}
</style>
	

    <title>餐廳檔案</title>
  </head>
  <body>
  <%@ include file="/header.jsp"%>
  --${param.whichPage}--
    <br>
    <jsp:useBean id="resSvc" 	scope="session" class="com.res.model.ResService" />
	<jsp:useBean id="resVO" 	scope="session" class="com.res.model.ResVO" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 order-1">
				<h1>${resVO.res_name}</h1>
			</div>
			<div class="col-sm-3">
				<a href="#" class="pull-right">
					<img src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=${resVO.res_no}" title="Profile image" class="rounded-circle"
					width="200" height="200">
				</a>
			</div>
		</div>
		<br>
		<br>
		<div class="row">
    		<div class="col-sm-2 text-center">
    			<div class="accordion" id="accordionExample">
				  <div class="card">
				    <div class="card-header" id="headingOne">
				      <h2 class="mb-0">
				        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				          餐廳資料
				        </button>
				      </h2>
				    </div>

				    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
				        <div class="list-group list-group-flush">
						  <a href="#ResInfo" id="infobtn" data-toggle="tab" class="list-group-item list-group-item-action active">基本資料</a>
						  <a href="#ResUpdateInfo" data-toggle="tab" class="list-group-item list-group-item-action">修改資料</a>
						</div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingTwo">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				          餐點管理
				        </button>
				      </h2>
				    </div>
				    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
				      <div class="list-group list-group-flush">
						  <a href="#" data-toggle="tab" class="list-group-item list-group-item-action">新增餐點</a>
						  <a href="#" data-toggle="tab" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
						  <a href="#" data-toggle="tab" class="list-group-item list-group-item-action">Morbi leo risus</a>
						  <a href="#" data-toggle="tab" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
						</div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingThree">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				          廣告管理
				        </button>
				      </h2>
				    </div>
				    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
				      <div class="list-group list-group-flush">
						  <a href="#addAd" data-toggle="tab" id="addAdbtn" class="list-group-item list-group-item-action">申請廣告</a>
						  <a href="#searchAd" data-toggle="tab" class="list-group-item list-group-item-action">廣告查詢</a>
						</div>
				    </div>
				  </div>
				  <div class="card">
				    <div class="card-header" id="headingfor">
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" id="test1btn" type="button" data-toggle="collapse" data-target="#amos" aria-expanded="false" aria-controls="collapseThree">
				          員工管理
				        </button>
				      </h2>
				    </div>
				    <div id="amos" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
				      <div class="list-group list-group-flush">
						  <a href="#addResAc" id="addResAcBtn" data-toggle="tab" class="list-group-item list-group-item-action">新增員工</a>
						  <a href="#searchResAc"  data-toggle="tab" class="list-group-item list-group-item-action">查詢員工</a>
						  <a href="#searchOneResAc" id="searchResAcBtn" style='display: none' data-toggle="tab" class="list-group-item list-group-item-action">查詢一個員工</a>
						</div>
				    </div>
				  </div>
				</div>
    		</div>

			<div class="col-sm-10">
				
				<div class="tab-content">
					<!--include 各個jsp 基本資料為預設顯示-->
					<div class="tab-pane active" id="ResInfo">
							<jsp:include page="/front-end/res/listOneRes.jsp"/> 
					</div>
					<!--修改資料-->
					<div class="tab-pane" id="ResUpdateInfo">
						<jsp:include page="/front-end/res/update_res_input.jsp"/>
						
					</div>

					<!--餐點管理(預留)-->
					<div class="tab-pane" id="">
						
					</div>

					<!--餐點管理(預留)-->
					<div class="tab-pane" id="">
						
					</div>

					<!--餐點管理(預留)-->
					<div class="tab-pane" id="">
						
					</div>

					<!--餐點管理(預留)-->
					<div class="tab-pane" id="">
						
					</div>

					<!--申請廣告-->
					<div class="tab-pane" id="addAd">
						<jsp:include page="/back-end/res/listAllRes.jsp"/>
					</div>

					<!--查詢廣告-->
					<div class="tab-pane" id="searchAd">
						
					</div>

					<!--廣告管理(預留)-->
					<div class="tab-pane" id="">
						
					</div>
					
					<!--新增員工-->
					<div class="tab-pane" id="addResAc">
						<jsp:include page="/front-end/resAc/addResAc.jsp"/>
					</div>
					
					<!--查詢員工-->
					<div class="tab-pane" id="searchResAc">
						<jsp:include page="/front-end/resAc/listAllResAc.jsp"/>
					</div>

					<!--查詢一個員工-->
					<div class="tab-pane" id="searchOneResAc">
						<jsp:include page="/front-end/resAc/listOneResAc.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>

${addResAc}
	<c:if test="${addResAc.equals(\"true\")}" var="flag" scope="session">
<%session.removeAttribute("addResAc");%>
	<script>
		$(function() {
			
			$('#test1btn').trigger('click');
			$('#infobtn').removeClass('active')
			$('#searchResAcBtn').trigger('click');
			
		});
	</script>
</c:if>

<c:if test="${addResAc.equals(\"false\")}" var="flag" scope="session">
<%session.removeAttribute("addResAc");%>
	<script>
		$(function() {
			
			$('#test1btn').trigger('click');
			$('#addResAcBtn').trigger('click');
		});
	</script>
</c:if>

<c:if test="${param.whichPage!=null}" var="flag" scope="request">
<%session.removeAttribute("addResAc");%>
	<script>
		$(function() {
			
// 			$('#test1btn').trigger('click');
			$('#addAdbtn').trigger('click');
		});
	</script>
</c:if>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>