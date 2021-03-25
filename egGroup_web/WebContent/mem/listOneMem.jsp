<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneMem.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員地址</th>
		<th>性別</th>
		<th>生日</th>
		<th>電話</th>
		<th>信箱</th>
		<th>點數</th>
		<th>照片</th>
		<th>密碼</th>
		<th>帳號</th>
		<th>自介</th>
		<th>狀態</th>
	</tr>
	<tr>
		<td><%=memVO.getMem_no()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_adrs()%></td>
		<td><%=memVO.getMem_sex()%></td>
		<td><%=memVO.getMem_bd()%></td>
		<td><%=memVO.getMem_ph()%></td>
		<td><%=memVO.getMem_email()%></td>
		<td><%=memVO.getMem_point()%></td>
		<td><img src="data:image/jpg;base64,<%=((Base64.Encoder)session.getAttribute("encoder")).encodeToString(memVO.getMem_img())%>"/></td> 
		<td><%=memVO.getMem_pass()%></td>
		<td><%=memVO.getMem_ac()%></td>
		<td><%=memVO.getMem_intro()%></td>
		<td><%=memVO.getMem_status()%></td>
	</tr>
</table>

</body>
</html>