<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*, com.tools.FindCodeName"%>

<%
	ResVO resVO = (ResVO) request.getAttribute("resVO"); //ResServlet.java (Concroller) 存入req的resVO物件 (包括幫忙取出的resVO, 也包括輸入資料錯誤時的resVO物件)
	if(resVO==null){
		resVO = (ResVO)session.getAttribute("resVO");
	}
	
	session.getAttribute("resVO");
	session.setAttribute("resVO", resVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>餐廳資料修改 - update_res_input.jsp</title>

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

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="res.do" name="form1"
		enctype="multipart/form-data">
		<table width="800px">
			<tr>
				<td>餐廳編號:<font color=red><b>*</b></font></td>
				<td><%=resVO.getRes_no()%></td>
			</tr>
			<tr>
				<td>餐廳名稱:<font color=red><b>*</b></font></td>
				<td><%=resVO.getRes_name()%></td>
			</tr>
			<tr>
				<td>餐廳地址:<font color=red><b>*</b></font></td>
				<td><%=resVO.getRes_adrs()%></td>
			</tr>
			<tr>
				<td>餐廳帳號:<font color=red><b>*</b></font></td>
				<td><%=resVO.getRes_ac()%></td>
			</tr>

			<tr>
				<td>餐廳密碼:</td>
				<td><input type="password" name="res_pass" size="45"
					value="<%=resVO.getRes_pass()%>" /></td>
			</tr>

			<tr>
				<td>密碼確認:</td>
				<td><input type="password" name="res_pass2" size="45"
					value="<%=resVO.getRes_pass()%>" /></td>
			</tr>
			<tr>
				<td>餐廳電話:</td>
				<td><input type="TEXT" name="res_ph" size="45"
					value="<%=resVO.getRes_ph()%>" /></td>
			</tr>

			<tr>
				<td>餐廳照片:</td>
				<td><input type="file" name="res_img" size="45"
					accept="image/gif, image/jpeg, image/png" id="imgpv"
					value="<%=resVO.getRes_img()%>" /></td>
				<td><img width="300" height="200" id="preview_img"
					src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=${resVO.res_no}">
				</td>
			</tr>

			<tr>
				<td>餐廳介紹:</td>
				<td><input type="TEXT" name="res_intro" size="45"
					value="<%=resVO.getRes_intro()%>" /></td>
			</tr>

			<tr>
				<td>營業時間:</td>
				<td><input type="TEXT" id="f_date1" name="res_start"
					value="<%=resVO.getRes_start()%>" />~<input type="TEXT"
					id="f_date2" name="res_end" value="<%=resVO.getRes_end()%>" /></td>
			</tr>
				<tr>
				<%int cost = 0;%>
				<%String level[] = {"Free","Inexpensive","Moderate","Expensive","Very Expensive"};
				%>
				<td>消費水準:<font color=red><b>*</b></font></td>
				<td><select size="1" name="res_cost">
			<c:forEach var="cost" begin="1" end="5" step="1">
				<option value="<%=cost%>"
				<%=(resVO != null && (resVO.getRes_cost()) == cost)? "selected" : "" %>
				><%=level[cost] %>
				<%++cost; %>
			</c:forEach>
				</select></td>
			</tr>


			<tr>
				<td>餐廳類型:</td>
				<td><input type="TEXT" name="res_type" size="45"
					value="<%=resVO.getRes_type()%>" /></td>
			</tr>		
			
	<tr>
	<%int i = 0;%>
	<td>餐廳狀態:<font color=red><b>*</b></font></td>
		<td><%=FindCodeName.meaning(resVO.getRes_status()) %></td>
	</tr>
	
		
						</table>
		<br><input type="hidden" name="res_point"
			value="<%=resVO.getRes_point()%>"> 
			<input type="hidden" name="res_status" value="<%=resVO.getRes_status()%>">
			<input type="hidden" name="res_lat" value="<%=resVO.getRes_lat()%>">
			<input type="hidden" name="res_lot" value="<%=resVO.getRes_lot()%>">
			<input type="hidden" name="res_score"
			value="<%=resVO.getRes_score()%>">
			<input type="hidden" name="res_comcount"
			value="<%=resVO.getRes_comcount()%>"> 
<%-- 			<input type="hidden" name="res_status" value="<%=resVO.getRes_status()%>">  --%>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="res_no" value="<%=resVO.getRes_no()%>">
			<input type="hidden" name="res_name" value="<%=resVO.getRes_name()%>">
			<input type="hidden" name="res_adrs" value="<%=resVO.getRes_adrs()%>">
			<input type="hidden" name="res_ac" value="<%=resVO.getRes_ac()%>">
			<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
$.datetimepicker.setLocale('zh');
$('#f_date1').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:true,       //timepicker:true,
   datepicker:false,
   step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'H:i',         //format:'Y-m-d H:i:s',
   value: '<%=resVO.getRes_start()%>', 
// value:   new Date(),
//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//startDate:	            '2017/07/10',  // 起始日
//minDate:               '-1970-01-01', // 去除今日(不含)之前
//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

$.datetimepicker.setLocale('zh');
$('#f_date2').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:true,       //timepicker:true,
   datepicker:false,
   step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'H:i',         //format:'Y-m-d H:i:s',
   value: '<%=resVO.getRes_end()%>',
	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
<script>
	$("#imgpv").change(function() {
		//當檔案改變後，做一些事 
		readURL(this); // this代表<input id="imgInp">
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</html>