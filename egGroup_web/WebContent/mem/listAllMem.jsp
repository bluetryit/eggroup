<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.memGetAll();
    pageContext.setAttribute("list",list);
    Base64.Encoder encoder=Base64.getEncoder();
    session.setAttribute("encoder", encoder);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllMem.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllMem.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
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

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|���a�}</th>
		<th>�ʧO</th>
		<th>�ͤ�</th>
		<th>�q��</th>
		<th>�H�c</th>
		<th>�I��</th>
		<th>�Ӥ�</th>
		<th>�K�X</th>
		<th>�b��</th>
		<th>�ۤ�</th>
		<th>���A</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		<td>${memVO.getMem_no()}</td>
		<td>${memVO.getMem_name()}</td>
		<td>${memVO.getMem_adrs()}</td>
		<td>${memVO.getMem_sex()}</td>
		<td>${memVO.getMem_bd()}</td>
		<td>${memVO.getMem_ph()}</td>
		<td>${memVO.getMem_email()}</td>
		<td>${memVO.getMem_point()}</td>
		
		<!-- ���Xbyte[]�নbase64�榡�r��A�M��L�X�� -->
		<td><img src="data:image/jpg;base64,<c:out value='${encoder.encodeToString(memVO.getMem_img())}'/>"/></td> 
		<td>${memVO.getMem_img()}</td>
		<td>${memVO.getMem_pass()}</td>
		<td>${memVO.getMem_ac()}</td>
		<td>${memVO.getMem_intro()}</td>
		<td>${memVO.getMem_status()}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>