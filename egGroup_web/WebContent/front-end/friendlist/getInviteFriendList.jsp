<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.friendlist.model.*"%>
<%@ page import="com.mem.model.*, com.tools.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemService memSv = new MemService();
	FriendListService friSvc = new FriendListService();

	MemVO memVO = (MemVO) session.getAttribute("memberVO");  
    List<MemVO> list = friSvc.getAllInvite(memVO.getMem_no());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style type="text/css">
    body{
    	margin-top:20px;
    	background:#FAFAFA;    
	}
	.people-nearby .google-maps{
  		background: #f8f8f8;
  		border-radius: 4px;
  		border: 1px solid #f1f2f2;
 	 	padding: 20px;
  		margin-bottom: 20px;
	}

	.people-nearby .google-maps .map{
	    height: 300px;
  	    width: 100%;
  	    border: none;
	}

	.people-nearby .nearby-user{
  		padding: 20px 0;
  		border-top: 1px solid #f1f2f2;
 	    border-bottom: 1px solid #f1f2f2;
        margin-bottom: 20px;
	}

	img.profile-photo-lg{
  		height: 80px;
  		width: 80px;
  		border-radius: 50%;
	}

    </style>




</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>
<table id="table-1">
	<tr>
		<td><h4><a href="<%=request.getContextPath()%>/front-end/friendlist/listAllFriendList.jsp">好友列表</a></h4></td> 
	
	</tr>
</table>
	<h1>交友邀請</h1>
	<%@ include file="page1.file" %> 
	
	<jsp:useBean id="FriendListVO" scope="page" class="com.friendlist.model.FriendListVO"/>
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
	
	<div class="container">
    <div class="row">
        <div class="col-md-12" align="center">
                <div class="row">

                   <c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                  		
                  		<div class="col-md-3 col-sm-3">
							<div class="col-md-2 col-sm-2">
                   				<img width="300" height="200" class="profile-photo-lg" src="<%=request.getContextPath()%>/back-end/mem/memPhoto.do?mem_no=${memVO.mem_no}">
                   			</div>
                    		<p>姓名:${memVO.getMem_name()}</p>
                    		<p>性別:${memVO.getMem_sex()}</p>
                    		<p>自我介紹:${memVO.getMem_intro()}</p>

                  
				  <button class="getInvite" f_memno="${memberVO.mem_no}" f_no="${memVO.mem_no}">接受邀請</button>
				  <button class="noInvite"  f_memno="${memVO.mem_no}" f_no="${memberVO.mem_no}">拒絕邀請</button>
                  </div>
                  </c:forEach>
                </div>
              </div>
</div>
</div>
<%@ include file="page2.file"%>

<script type="text/javascript">
// $(document).ready(function(){
//   	$("#accept").click(function(){
//   		$("p").hide(1000);
//   });
// });

$(".getInvite").click(function()	///???	
		{
			$.post
			(
				"<%=request.getContextPath()%>/front-end/friendlist/friendlist.do",
				{ "action": "getInvite", "f_no":$(this).attr('f_no'),"f_memno":$(this).attr('f_memno') }
			).done(function(data)
					{
						var newDoc = document.open("text/html", "replace");
					    newDoc.write(data);
					    newDoc.close();
					}
				  )
			
		});
		
$(".noInvite").click(function()		
		{
			$.post
			(
				"<%=request.getContextPath()%>/front-end/friendlist/friendlist.do",
				{ "action": "noInvite", "f_no":$(this).attr('f_no'),"f_memno":$(this).attr('f_memno') }
			).done(function(data)
					{
						var newDoc = document.open("text/html", "replace");
					    newDoc.write(data);
					    newDoc.close();
					}
				  )
			
		});

</script>

</body>
</html>