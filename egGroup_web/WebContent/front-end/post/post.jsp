<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.leavemessage.model.*"%>
<%@ page import="com.res.model.*"%>
<jsp:useBean id="FLSvc" scope="page" class="com.friendlist.model.FriendListService" />
<%
	PostService postSvc = new PostService();

	List<PostVO> list = postSvc.getAllOnlinePost();

	pageContext.setAttribute("list", list);
	LeaveMessageVO LeaveMessageVO = (LeaveMessageVO) request.getAttribute("LeaveMessageVO");
%>
<!doctype html>
<html lang="en">
<head>

<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
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



<title>貼文瀏覽</title>
<style type="text/css">
body {
	font-family: "微軟正黑體";
	background-color: #FAEBD7;
}

.title {
	display: flex;
	justify-content: space-between;
}

#postImg {
	width: 728 px;
	height: 728 px;
}

.starrating>input {
	display: none;
} /* Remove radio buttons */
.starrating>label:before {
	content: "\f005"; /* Star */
	margin: 5px;
	font-size: 20px;
	font-family: FontAwesome;
	display: inline-block;
}

.starrating>label {
	color: #222222; /* Start color when not clicked */
}

.starrating>input:checked ~ label {
	color: #ffca08;
} /* Set yellow color when star checked */
.starrating>input:hover ~ label {
	color: #ffca08;
} /* Set yellow color when star hover */
.TimePost {
	display: flex;
	justify-content: space-between;
}
</style>

</head>
<body>
	<%@ include file="/header.jsp"%>

<!-- 	<p> -->
<%-- 		<a href='<%=request.getContextPath()%>/front-end/post/addPost.jsp'>新增</a>貼文 --%>
<!-- 	</p> -->

	<%@ include file="/front-end/page1.file"%>
	<c:forEach var="PostVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<jsp:useBean id="PostVO" scope="page" class="com.post.model.PostVO" />
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
		<jsp:useBean id="lmSvc" scope="page"
			class="com.leavemessage.model.LeaveMessageService" />
		<jsp:useBean id="resSvc" scope="page" class="com.res.model.ResService" />
		<div class="container">
			<section class="my-4">
				<div class="card news-card">
					<div class="row">
						<div class="col-md-4">
							<div class="card-body">
								<div class="content">
									<div class="title">
										<h3 id="poster">
											<img id="MemImg" src="https://picsum.photos/50/50?random=1"
												class="rounded-circle avatar-img z-depth-1-half"> :
											${memSvc.memFindByPrimaryKey(PostVO.post_memno).mem_name}
										</h3>
									</div>

									<div class="right-side-meta" id="postTime">
										<h4>${resSvc.getOneRes(PostVO.post_res_no).res_name}</h4>
									</div>
									<div class="TimePost" id="postTime">
										<FORM METHOD="post" ACTION="post.do">
											<small>po文時間 :<fmt:formatDate
													value="${PostVO.post_time}" pattern="yyyy-MM-dd HH:mm" /></small>
										</FORM>
									</div>

								</div>
							</div>
							<FORM METHOD="post" ACTION="post.do">
								<c:if test="${PostVO.getPost_img()!=null }">
									<%
										String pic = new String(Base64.getEncoder().encode(PostVO.getPost_img()), "UTF-8");
									%>
									<img class="card-img-top" id="postImg"
										src="data:image/jpg;base64,<%=pic%>" />
								</c:if>
								<c:if test="${PostVO.getPost_img()==null }">
									<img id="" class="card-img-top"
										src="https://mdbootstrap.com/img/Photos/Others/girl1.jpg"
										alt="你沒有放圖片喔">
								</c:if>
							</FORM>
						</div>

						<div class="col-md-8">
							<div class="card-body">
								<div class="social-meta">

									<FORM METHOD="post" ACTION="post.do">
										<label id="postText" style="text-align: left;">po文內容 :</label>
										<p>${PostVO.post_text}</p>
									</FORM>
								</div>
								<label style="color: #69778c;"> <i
									style="color: #F08080;" class="fa fa-gratipay"></i>
									餐廳回應:${PostVO.post_respon}
								</label>
								<p>留言內容:</p>
								<div class=" listing-block"
									style="overflow-y: scroll; flex-grow: 1; height: 250px;">

									<c:forEach var="lmVO"
										items="${lmSvc.getAllLeaveMessageByPost(PostVO.post_no)}">
										<div class="div-inline">
											<label><h6 style="color: #000080; float: left;">
													<i class="fa fa-comment"></i>${memSvc.memFindByPrimaryKey(lmVO.lm_memno).mem_name}
												</h6></label>
											
											<c:if test="${!FLSvc.getAll(memberVO.mem_no).contains(FLSvc.findByIds(memberVO.mem_no, lmVO.lm_memno)) || !FLSvc.getAll(memberVO.mem_no).contains(FLSvc.findByIds(lmVO.lm_memno, memberVO.mem_no)) && memberVO.mem_no ne lmVO.lm_memno}">
												<td width="70%">
													<button id="add_friend_feast" class="btn btn-primary add_friend_feast" name="mye_feano" feano="${memberVO.mem_no}" memno="${lmVO.lm_memno}">加好友</button> 
												</td>
											</c:if> 		
												
											<button class="btn btn-light end" type="button"
												data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false" title="不喜歡這留言嗎?" style="float: right;">...</button>
											<div class="dropdown-menu btn btn-light"
												aria-labelledby="dropdownMenuButton" id="down">
												<a class="dropdown-item" data-toggle="modal"
													data-id="${lmVO.lm_no}" data-target="#report-2">檢舉此留言</a>
											</div>
										</div>
										<p id="mes">${lmVO.lm_text}</p>
									</c:forEach>

								</div>
							</div>

							<form class="form-inline" METHOD="post" ACTION="post.do">
								<div class="form-group mb-2">
									<p>
										對餐廳評分等級: <b>${PostVO.post_rate}</b>
									</p>
								</div>
								<!-- 								<div -->
								<!-- 									class="starrating risingstar d-flex  flex-row-reverse form-group mx-sm-3 mb-4"> -->
								<!-- 									<input type="radio" id="star5" name="rating" value="5" /><label -->
								<!-- 										for="star5" title="5 star"></label> <input type="radio" -->
								<!-- 										id="star4" name="rating" value="4" /><label for="star4" -->
								<!-- 										title="4 star"></label> <input type="radio" id="star3" -->
								<!-- 										name="rating" value="3" /><label for="star3" title="3 star"></label> -->
								<!-- 									<input type="radio" id="star2" name="rating" value="2" /><label -->
								<!-- 										for="star2" title="2 star"></label> <input type="radio" -->
								<!-- 										id="star1" name="rating" value="1" /><label for="star1" -->
								<!-- 										title="1 star"></label> -->
								<!-- 								</div> -->
							</form>

							<hr>
							<!-- Comment input -->
							<div class="md-form">
								<div class="flex-container">
									<form class="form-inline" METHOD="post"
										ACTION="leavemessage.do" name="form1">
										<div class="form-group-start mb-2">
											<input placeholder="留言.." name="lm_text" type="text"
												class="form-control" style="width: 450px;"
												value="<%=(LeaveMessageVO == null) ? "" : LeaveMessageVO.getLm_text()%>">

										</div>
										<div class="form-group-end  mx-sm-3 mb-2">
											<input type="hidden" name="lm_postno"
												value="${PostVO.post_no}"> <input type="hidden"
												name="action" value="insert"> <input type="submit"
												class="btn btn-light" value="發佈">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
		</section>
		</div>

	</c:forEach>


	<%
		if (pageNumber > 1) {
	%>


	<!-- 檢舉視窗II -->
	<div class="modal fade right bg-default " id="report-2" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-full-height modal-right modal-notify modal-info"
			role="document">
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header">
					<p class="heading lead">
						<strong>檢舉留言:</strong>
					</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" class="white-text">×</span>
					</button>
				</div>
				<!--Body-->
				<FORM METHOD="post" ACTION="reportLM.do" name="form1">

					<input type="hidden" name="action" value="insert"> <input
						type="hidden" id="ReportLM_lmno" name="LeaveMessage_lmno">
					<div class="modal-body">
						<!-- Radio -->
						<p class="text-center">
							<strong>確認檢舉後請按送出鍵</strong>
						</p>
						<!-- Radio -->
						<p class="text-center">
							<strong>並請告訴我們檢舉原因:</strong>
						</p>
						<!--文字欄位-->
						<div class="md-form">
							<textarea name="leaveMessageRe" required id="form79textarea"
								class="md-textarea form-control" rows="3"
								placeholder="例如:不當留言內容、言詞羞辱...等"></textarea>
						</div>
					</div>
					<!--Footer-->
					<div class="modal-footer justify-content-center">
						<button class="btn btn-info" type="submit" id="send">
							送出<i class="fa fa-paper-plane ml-1"></i>
						</button>

						<button class="btn btn-outline-info " data-dismiss="modal">
							取消 <i class="fa fa-times"></i>
						</button>
					</div>

				</FORM>
			</div>
		</div>
	</div>

	<%
		}
	%>
	<%@ include file="/front-end/page2.file"%>

	<script type="text/javascript">
		$('#report-2').on('show.bs.modal', function(e) {
			var product = $(e.relatedTarget).data('id');
			$("#ReportLM_lmno").val(product);
		});
		
		$("#send").click(function() {
			alert("檢舉已送出,謝謝您的反應!");
		});
		
		
		
		$(".add_friend_feast"). click(function()
				{
					console.log('add_friend_feast')
					$.post
					(
						"<%=request.getContextPath()%>/front-end/friendlist/friendlist.do",
						{ "action": "insert", "mye_feano": $(this).attr('feano'),  "f_memno": $(this).attr('memno'),  }
					).done(function(data)
							{
								var newDoc = document.open("text/html", "replace");
							    newDoc.write(data);
							    newDoc.close();
							}
						  )
					
				});
						

	</script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->


</body>
</html>