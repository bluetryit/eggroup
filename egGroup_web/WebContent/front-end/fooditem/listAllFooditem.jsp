<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooditem.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    FooditemService fooditemSvc = new FooditemService();
    List<FooditemVO> list = fooditemSvc.getAll();    
    pageContext.setAttribute("list",list);
//      FooditemVO fooditemVO = (FooditemVO) session.getAttribute("fooditemVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
//   FooditemService mysvc = new FooditemService();
//   List<FooditemVO> list = new ArrayList<FooditemVO>(); 
//   list = mysvc.getAllfooditemVOByFooditem(fooditemVO.getFo_resno());
//   pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��\�I��� - listAllFooditem.jsp</title>

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
	width: 800px;
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
<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">

	<tr><td>
		 <h3>�Ҧ��\�I��� - listAllFooditem.jsp</h3>
		 <h4><a href="fooditem_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table style="background-color:rgba(0,0,0,0);">
	<tr>
	
		<th>�\�U�s��</th>
		<th>�\�I�s��</th>
		<th>�W��</th>
		<th>����</th>
		<th>�Ϥ�</th>
		<th>����</th>
		<th>���A</th>
	
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="fooditemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${fooditemVO.fo_resno}</td>
			<td>${fooditemVO.fo_no}</td>
			<td>${fooditemVO.fo_name}</td>
			<td>${fooditemVO.fo_price}</td>
			<td><img src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=${fooditemVO.fo_no}" width="200"></td>
			<td>${fooditemVO.fo_intro}</td> 
			<td>${fooditemVO.fo_status}</td> 
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fooditem/fooditem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="fo_no"  value="${fooditemVO.fo_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fooditem/fooditem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="fo_no"  value="${fooditemVO.fo_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%

    pageContext.removeAttribute("list");
%>
</body>
</html>