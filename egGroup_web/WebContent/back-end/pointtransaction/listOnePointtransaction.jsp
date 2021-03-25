<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pointtransaction.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  PointtransactionVO pointtransactionVO = (PointtransactionVO) request.getAttribute("pointtransactionVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
  PointtransactionService mysvc = new PointtransactionService();
  List<PointtransactionVO> list = new ArrayList<PointtransactionVO>(); 
  list = mysvc.getAllPointtransactionVOByPointtransaction(pointtransactionVO.getPt_no());

  pageContext.setAttribute("mysvc", mysvc);
  pageContext.setAttribute("list",list);

%>

<html>
<head>
<title>點數資料 - listOnePointtransaction.jsp</title>

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
		 <h3>點數資料 - ListOnePointtransaction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>"/back-end/pointtransaction_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>點數交易編號</th>
		<th>會員編號</th>
		<th>餐廳編號</th>
		<th>交易金額</th>
	</tr>
	<tr>
		<td><%=pointtransactionVO.getPt_no()%></td>
		<td><%=pointtransactionVO.getPt_memno()%></td>
		<td><%=pointtransactionVO.getPt_resno()%></td>
		<td><%=pointtransactionVO.getPt_nt()%></td>
		
	</tr>
</table>

</body>
</html>