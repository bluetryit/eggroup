<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneMem.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

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