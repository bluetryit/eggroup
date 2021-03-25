<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.friendlist.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	MemService memSv = new MemService();
	FriendListService friSvc = new FriendListService();
	
	MemVO memVO = (MemVO) session.getAttribute("memberVO");//�]���{�b�S���|�����
    List<MemVO> list = friSvc.match(memVO.getMem_no());
    pageContext.setAttribute("list",list);
    pageContext.setAttribute("memVO",memVO);
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

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body bgcolor='white'>
<%@ include file="/header.jsp"%>
<table id="table-1">
	<tr>
		<td><h4><a href="select_page.jsp">�n�ͦC��</a></h4></td> 
		<td><h4><a href="select_page.jsp">����ܽ�</a></h4></td> 
	</tr>
</table>
	<h1>�n�Ͱt��</h1>
	<%@ include file="page1.file" %> 
	
	<jsp:useBean id="FriendListVO" scope="page" class="com.friendlist.model.FriendListVO"/>
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>

	<div class="container">
    <div class="row">
        <div class="col-md-12" align="center">
            <div class="people-nearby">
              
              <div class="nearby-user">
                <div class="row">
                   <c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                  <div class="col-md-2 col-sm-2">
                    <img src="<%=request.getContextPath()%>/back-end/mem/memPhoto.do?mem_no=${memVO.mem_no}" alt="user" class="profile-photo-lg">
                   </div>
                   
                  		<div class="col-md-7 col-sm-7">
                  		
                    		<h5>�m�W:${memVO.getMem_name()}</h5>
                    		<h5>�ʧO:${memVO.getMem_sex()}</h5>
                    		<h5>�ۧڤ���:${memVO.getMem_intro()}</h5>
                    	</div>
                  <div class="col-md-3 col-sm-3">
                    <button class="btn btn-primary pull-right">�e�X�ܽ�</button>
                  </div>
                  </c:forEach>
                </div>
              </div>
	</div>
</div>
</div>
</div>

<%@ include file="page2.file"%>

</body>
</html>