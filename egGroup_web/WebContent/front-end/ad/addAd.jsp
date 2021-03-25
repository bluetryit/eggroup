<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.LocalTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*,com.resac.model.*,java.text.SimpleDateFormat"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告申請 - addAd.jsp</title>

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

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}" >
		<font style="color: red" >請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="ad.do" name="form1" enctype="multipart/form-data">
		<table width="1000px">
			<tr>
				<td>廣告標題:</td>
				<td><input type="TEXT" name="ad_title" size="45" id="ad_title"
					value="<%=(adVO == null) ? "" : adVO.getAd_title()%>" /></td>
			</tr>
			
			<tr>
				<td>廣告內文:</td>
				<td>
					<div class="form-group">
 				 	<label for="exampleFormControlTextarea3"></label>
  					<textarea class="form-control" id="exampleFormControlTextarea3" name="ad_text" cols="50" rows="30" style="resize:none;"><%=(adVO == null) ? "" : adVO.getAd_text()%></textarea>
  					</div>
  				</td>
			</tr>
			
			<tr>
				<td>廣告照片:</td>
				<td><img width="300" height="200" id="preview_img2" src="<%=request.getContextPath()%>/images/noimg.jpg"></td>
				<td><input type="file" name="ad_img" size="20" 
					accept="image/gif, image/jpeg, image/png"
					id="imgpv2"
					value="<%=(adVO == null) ? "" : adVO.getAd_img()%>" /></td>	
			</tr>
			<tr>
				<td>廣告刊登開始日期:</td>
				<td><input name="ad_start" id="start_date" type="text"></td>
			</tr>
	
			<tr>
				<td>廣告刊登結束日期:</td>
				<td><input name="ad_end" id="end_date" type="text"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="action" value="insert">
				<input type="submit" value="送出新增">
				</td>
			</tr>
		</table>
		<br>
	</FORM>
	<button id="magic" style="opacity:0.1" onclick="setData()">這個</button>
</body>

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

<script>
$("#imgpv2").change(function(){
    //當檔案改變後，做一些事 
   readURL(this);   // this代表<input id="imgInp">
 });
function readURL(input){
	  if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $("#preview_img2").attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
	}
</script>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  Timestamp ad_start = null;
  Timestamp ad_end = null;
  try {
		if(adVO!=null){
			ad_start = adVO.getAd_start();
			ad_end = adVO.getAd_end();
		}
   } catch (Exception e) {
	   ad_start = null;
		ad_end = null;
   }
  

%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  theme: '',          //theme: 'dark',
      timepicker: true,   //timepicker: false,
      step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	  format:'Y-m-d',
	  value: <%=(ad_start == null)? null : "'" + ad_start + "'"%>,
	  onShow:function(){
		  var max = $('#end_date').val();
		  var maxAddOne = new Date(max).getTime()-24*60*60*1000
	   this.setOptions({
		minDate:new Date(),
	    maxDate:$('#end_date').val()?maxAddOne:false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
      theme: '',          //theme: 'dark',
	  timepicker: true,   //timepicker: false,
	  step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	  format:'Y-m-d',
	  
	  value: <%=(ad_end == null)? null : "'" +  ad_end + "'"%>,	
	  onShow:function(){
		  var min = $('#start_date').val();
		  var StartDateAddOne = new Date(min).getTime()+24*60*60*1000
		  var endDateMin = new Date().getTime()+24*60*60*1000
	   this.setOptions({
	    minDate:$('#start_date').val()?StartDateAddOne:endDateMin,
	   })
	  },
	  timepicker:false
	 });
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
<script type="text/javascript">
		function setData(){
			$('#ad_title').val('八八節特餐');
			$('#exampleFormControlTextarea3').val('蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元'+
					'\n蜂蜜啤酒大特價只要200元');
			$('#start_date').val('2019-08-01');
			$('#end_date').val('2019-08-31');
		}
</script>
</html>