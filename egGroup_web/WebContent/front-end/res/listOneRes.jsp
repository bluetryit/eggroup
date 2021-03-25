<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.model.*"%>
<%@ page import="com.tools.*, com.pointtransaction.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ResVO resVO = (ResVO) session.getAttribute("resVO"); //ResServlet.java(Concroller), 存入req的resVO物件

  String level[] = {"Free","Inexpensive","Moderate","Expensive","Very Expensive"};
  pageContext.setAttribute("level",level);
%>

<%
	PointtransactionService pointtransactionSvc = new PointtransactionService();
    List<PointtransactionVO> list = pointtransactionSvc.getAllPointByRes(resVO.getRes_no());
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>餐廳資料 - listOneRes.jsp</title>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="resService" scope="page" class="com.res.model.ResService" />

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
	width: 1600px;
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

<%@ include file="/header.jsp"%>

<table>
	<tr>
		<th>餐廳地址</th>
		<th>餐廳名稱</th>
		<th>餐廳電話</th>
		<th>餐廳點數</th>
		<th>餐廳帳號</th>
		<th>餐廳密碼</th>
		<th>餐廳照片</th>
		<th>餐廳介紹</th>
		<th>餐廳營業時間</th>
		<th>餐廳評分</th>
		<th>評分人次</th>
		<th>餐廳消費水準</th>
		<th>餐廳類型</th>
		<th>餐廳狀態</th>
	</tr>
	<tr>
			<td>${resVO.res_adrs}</td>
			<td>${resVO.res_name}</td>
			<td>${resVO.res_ph}</td>
			<td>${resVO.res_point}</td>
			<td>${resVO.res_ac}</td> 
			<td>${resVO.res_pass}</td>
			<td><img width="300" height="200" src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=${resVO.res_no}"> </td>
			<td>${resVO.res_intro}</td>
			<td>${resVO.res_start}~${resVO.res_end}</td>
			<td>${resVO.res_score}</td> 
			<td>${resVO.res_comcount}</td>
			<td>${(resVO.res_cost == 0)? level[0]:''}
				${(resVO.res_cost == 1)? level[1]:''}
				${(resVO.res_cost == 2)? level[2]:''}
				${(resVO.res_cost == 3)? level[3]:''}
				${(resVO.res_cost == 4)? level[4]:''}
			</td>
			<td>${resVO.res_type}</td> 
			<td>${FindCodeName.meaning(resVO.res_status)}</td>
	</tr>
</table>

<table>
		<tr>
			<td>會員</td>
			<td>餐廳</td>
			<td>點數</td>
		</tr>
		<c:forEach var="pointtransactionVO" items="${list}">


		<tr>
			<td>${memSvc.memFindByPrimaryKey(pointtransactionVO.pt_memno).getMem_name()}</td>
			<td>${resService.getOneRes(pointtransactionVO.pt_resno).getRes_name()}</td>
			<td>${pointtransactionVO.pt_nt}點</td>
		
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pointtransaction/pointtransaction.do" style="margin-bottom: 0px;">

			     <input type="hidden" name="pt_no"  value="${pointtransactionVO.pt_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>


<a href="<%=request.getContextPath()%>/front-end/res/update_res_input.jsp"><button class="btn btn-lg btn-success" type="submit">修改資料</button></a>
</body>



</html>