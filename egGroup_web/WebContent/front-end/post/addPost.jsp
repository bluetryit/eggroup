<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.leavemessage.model.*"%>
<%@ page import="com.res.model.*"%>

<%
	PostVO PostVO = (PostVO) request.getAttribute("PostVO");
	String res_no = (String) request.getParameter("res_no");
	
	pageContext.setAttribute("res_no", res_no);
%>

<html>
<head>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/address.js"></script>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>貼文資料新增 - addpost.jsp</title>
<script>
	$(document).ready(function() {

		var readURL = function(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.avatar').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$(".file-upload").on('change', function() {
			readURL(this);
		});
	});
</script>

</head>
<body>
	<%@ include file="/header.jsp"%>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<h4><a href="post.jsp">回首頁</a></h4>
	<div class="container">
	<div class="row justify-content-center">
	<div class="col-8 ">
	<FORM METHOD="post" ACTION="post.do" name="form1" enctype="multipart/form-data">
	<div class="form-group">
	<div class="card">
  <div class="card-header">
			<b>新增貼文</b>
  </div>

  <div class="card-body">
    <h5 class="card-title">貼文內容:</h5>
    <p class="card-text"><textarea id="post_text" class="form-control" name="post_text" rows="5" pleaceholder=""><%=(PostVO == null) ? " " : PostVO.getPost_text()%></textarea></p>
    <div class="row">
    <div class="form-group col-sm-6">
    <p class="card-text">評分:
	<input type="TEXT" name="post_rate" id="post_rate" class="form-control" value="<%=(PostVO == null) ? 1 : PostVO.getPost_rate()%>" /></p>
	</div>
	<div class="form-group col-sm-6">
    <h6>貼文圖片</h6><input name="post_img" type="file" class="text-center center-block file-upload">
	</div>
  </div>
    <ul class="list-group list-group-flush">
	<li class="list-group-item justify-content-center">
						
							<img src="<%=request.getContextPath()%>/images/no.gif" width="50%"
								class="avatar img-circle-top" alt="avatar"> 
				
					
			</li> 
    </ul>
  <div class="card-footer">
   	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增" class="btn btn-primary">
   	<input type="hidden" name="post_res_no" value="${res_no}">
  </div>
</div>
</div>
		</div>
		<br>

			
		</FORM>
			</div>
		</div>
		<button id="magic" style="opacity:0.1" onclick="setData()">這個</button>
	</div> 
<%@ include file="/footer.jsp"%>

<script type="text/javascript">
		function setData(){
			$('#post_text').val('吃粗飽 真的好棒');
			$('#post_rate').val('5');
		}
</script>
</body>
</html>