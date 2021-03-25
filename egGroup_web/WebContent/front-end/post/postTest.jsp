<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.leavemessage.model.*"%>
<%@ page import="com.res.model.*"%>

<%
	PostService postSvc = new PostService();
	
	List<PostVO> list = postSvc.getAll();

	
	pageContext.setAttribute("list",list);
	LeaveMessageVO LeaveMessageVO = (LeaveMessageVO) request.getAttribute("LeaveMessageVO");
%>
<!doctype html>
<html lang="en">
  <head>
  
  <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
     <link rel="stylesheet" href="css/all.css">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  	
	
		
    <title>美麗貼文新世界</title>
    <style type="text/css">
    body{
      font-family:"微軟正黑體";
    background-color: #FAEBD7;
    }
    .title{
    	display: flex;
    	justify-content: space-between;
    }
    #postImg{
    width:348px;
    height:328px;
    }




.starrating > input {display: none;}  /* Remove radio buttons */

.starrating > label:before { 
  content: "\f005"; /* Star */
  margin: 5px;
  font-size: 20px;
  font-family: FontAwesome;
  display: inline-block; 
}

.starrating > label
{
  color: #222222; /* Start color when not clicked */
}

.starrating > input:checked ~ label
{ color: #ffca08 ; } /* Set yellow color when star checked */

.starrating > input:hover ~ label
{ color: #ffca08 ;  } /* Set yellow color when star hover */

.TimePost{
	display:flex;
	justify-content:space-between;

}

    </style>
 
  </head>
  <body>


	   <%@ include file="/front-end/post_page1.file" %>
	<c:forEach var="PostVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<jsp:useBean id="PostVO" scope="page" class="com.post.model.PostVO" />
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
		<jsp:useBean id="lmSvc" scope="page"
			class="com.leavemessage.model.LeaveMessageService" />
		<jsp:useBean id="resSvc" scope="page" class="com.res.model.ResService" />

		<div class="container ">
			<section class="my-4">
				<div class="row">
					<!-- Grid column -->
					<div class="col-lg-4 md-5">
						<!-- Card -->
						<div class="card news-card">
							<!-- Heading-->
							<div class="card-body">
								<div class="content">
									<div class="title">
										<h3 id="poster">
										<img id="MemImg" src="https://picsum.photos/50/50?random=1" class="rounded-circle avatar-img z-depth-1-half"> 王
										
										</h3>
										<div class="dropdown" >
											<button class="btn btn-light " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" title="不喜歡這篇貼文嗎?">
											...
											</button>
											<div class="dropdown-menu btn btn-light" aria-labelledby="dropdownMenuButton" id="down">
												<a class="dropdown-item" href="#">檢舉</a>
												<a class="dropdown-item" href="#">修改</a>
												<a class="dropdown-item" href="#">刪除</a>
											</div>
										</div>
									</div>
									<div class="right-side-meta">
										<h4>PO文者999999999</h4>
									</div>
									<div class="TimePost" id="postTime">
										<h6>文章編號99999999</h6>
										<small>po文時間99999999999</small>
									</div>
								</div>
								</div><!--cardBody1-->
								<!-- Card image-->
								<img id="postImg" class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/girl1.jpg"  alt="你沒有放圖片喔">
								<div class="card-body">
									<div class="social-meta">
										<p id="postText">我的po文內容</p>
										<span><i class="fa fa-gratipay"></i> 店家回應:</span>
										<div class=" listing-block" style="overflow-y: scroll;height: 80px;">
										<div><p><i class="fa fa-comment"></i>留言內容:121321321231321321321321321321</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>
										<div><p><i class="fa fa-comment"></i>留言內容:</p></div>

										</div>
										
										<form class="form-inline">
											<div class="form-group mb-2">
												<p >評分:</p>
											</div>
											<div class="starrating risingstar d-flex  flex-row-reverse form-group mx-sm-3 mb-4">
												<input type="radio" id="star5" name="rating" value="5"/><label for="star5"title="5 star"></label>
												<input type="radio" id="star4" name="rating" value="4"/><label for="star4" title="4 star"></label>
												<input type="radio" id="star3" name="rating" value="3"/><label for="star3" title="3 star"></label>
												<input type="radio" id="star2" name="rating" value="2"/><label for="star2" title="2 star"></label>
												<input type="radio" id="star1" name="rating" value="1"/><label for="star1" title="1 star"></label>
											</div>
										</form>
										
									</div>
									<hr>
									<!-- Comment input -->
									
									<div class="md-form">
										<form class="form-inline">
											<div class="form-group mb-2">
												<input placeholder="留言.." type="text" id="form5" class="form-control">
											</div>
											<div class="form-group  mx-sm-3 mb-2">
												<input type="submit" name="sendLeaveMes" class="btn btn-light" value="發佈">
											</div>
										</form>
									</div>
									</div>
<!-- 									Card body -->
									</div>
<!-- 									Card -->
									</div>
<!-- 									Grid column -->
									<!--postCard 3張範圍-->
				</div>
			</section>
		</div>
	</c:forEach>

	<p><a href='<%=request.getContextPath() %>/front-end/post/addPost.jsp'>Add</a> a new Post.</p>



<%if (pageNumber>1) {%>
    <!-- 底下換頁標籤 -->
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<%if (whichPage == 1) {%>
						<%for(int i = 1; i <= 3; i++){%>
	           				<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
	         			<%}%> 
					<%}else if (whichPage == pageNumber){%>
						<%for(int i = pageNumber - 2; i <= pageNumber; i++){%>
	           				<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
	         			<%}%> 
					<%}else{%>
						<%for(int i = 1; i <= 3; i++){%>
	           				<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+i-2%>"><%=whichPage+i-2%></a></li>
	         			<%}%> 
					<%}%>
					
					<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
	<%}%>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 </body>
</html>