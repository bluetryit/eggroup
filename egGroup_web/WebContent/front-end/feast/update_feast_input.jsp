<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feastinfo.model.*, java.util.*, java.text.*"%>


<%
    FeastInfoVO feastInfoVO = (FeastInfoVO) request.getAttribute("feastInfoVO");
%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script>
function getVals() {
	// Get slider values
	var parent = this.parentNode;
	var slides = parent.getElementsByTagName("input");
	var slide1 = parseFloat(slides[0].value);
	var slide2 = parseFloat(slides[1].value);
	// Neither slider will clip the other, so make sure we determine which is larger
	if (slide1 > slide2) {
		var tmp = slide2;
		slide2 = slide1;
		slide1 = tmp;
	}

	var displayElement = parent.getElementsByClassName("rangeValues")[0];
	displayElement.innerHTML = "人數下限" + slide1 + "人 - 人數上限" + slide2 + "人";
}

window.onload = function() {
	// Initialize Sliders
	var sliderSections = document.getElementsByClassName("range-slider");
	for (var x = 0; x < sliderSections.length; x++) {
		var sliders = sliderSections[x].getElementsByTagName("input");
		for (var y = 0; y < sliders.length; y++) {
			if (sliders[y].type === "range") {
				sliders[y].oninput = getVals;
				// Manually trigger event first time to display values
				sliders[y].oninput();
			}
		}
	}
	
	
	var option = document.getElementsByClassName("typeSelect");
	var select = "<%=(feastInfoVO == null) ? "" : feastInfoVO.getFea_type()%>";
	
	if(select == ("內用"))
	{
		$('.typeSelect')[1].selected="selected";
	}
	else if(select == ("外帶"))
	{
		$('.typeSelect')[2].selected="selected";
	}
	else if(select == ("外送"))
	{
		$('.typeSelect')[3].selected="selected";
	}
}


</script>

<style type="text/css">
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

section.range-slider {
	position: relative;
	width: 300px;
	height: 100px;
	float: left;
	text-align: center;
}

section.range-slider input[type="range"] {
	pointer-events: none;
	position: absolute;
	-webkit-appearance: none;
	-webkit-tap-highlight-color: rgba(255, 255, 255, 0);
	border: none;
	border-radius: 14px;
	background: #F1EFEF;
	box-shadow: inset 0 1px 0 0 #cdc6c6, inset 0 -1px 0 0 #d9d4d4;
	-webkit-box-shadow: inset 0 1px 0 0 #cdc6c6, inset 0 -1px 0 0 #d9d4d4;
	overflow: hidden;
	left: 0;
	top: 50px;
	width: 300px;
	outline: none;
	height: 20px;
	margin: 0;
	padding: 0;
}

section.range-slider input[type="range"]::-webkit-slider-thumb {
	pointer-events: all;
	position: relative;
	z-index: 1;
	outline: 0;
	-webkit-appearance: none;
	width: 20px;
	height: 20px;
	border: none;
	border-radius: 14px;
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #dad8da),
		color-stop(100%, #413F41));
	/* android <= 2.2 */
	background-image: -webkit-linear-gradient(top, #dad8da 0, #413F41 100%);
	/* older mobile safari and android > 2.2 */
	background-image: linear-gradient(to bottom, #dad8da 0, #413F41 100%);
	/* W3C */
}

section.range-slider input[type="range"]::-moz-range-thumb {
	pointer-events: all;
	position: relative;
	z-index: 10;
	-moz-appearance: none;
	width: 20px;
	height: 20px;
	border: none;
	border-radius: 14px;
	background-image: linear-gradient(to bottom, #dad8da 0, #413F41 100%);
	/* W3C */
}

section.range-slider input[type="range"]::-ms-thumb {
	pointer-events: all;
	position: relative;
	z-index: 10;
	-ms-appearance: none;
	width: 20px;
	height: 20px;
	border-radius: 14px;
	border: 0;
	background-image: linear-gradient(to bottom, #dad8da 0, #413F41 100%);
	/* W3C */
}

section.range-slider input[type=range]::-moz-range-track {
	position: relative;
	z-index: -1;
	background-color: black;
	border: 0;
}

section.range-slider input[type=range]:last-of-type::-moz-range-track {
	-moz-appearance: none;
	background: none transparent;
	border: 0;
}

section.range-slider input[type=range]::-moz-focus-outer {
	border: 0;
}
</style>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.sql.Timestamp feastDate = null;
			java.sql.Timestamp startDate = null;
			java.sql.Timestamp endDate = null;
			try {
				startDate = feastInfoVO.getFea_startDate();

				endDate = feastInfoVO.getFea_endDate();

				feastDate = feastInfoVO.getFea_date();
			} catch (Exception e) {
				feastDate = new java.sql.Timestamp(System.currentTimeMillis());
				startDate = new java.sql.Timestamp(System.currentTimeMillis());
				endDate = new java.sql.Timestamp(System.currentTimeMillis());
			}
%>

<title>飯局資料新增 - addFeast.jsp</title>
</head>
<body>

	<%@ include file="/header.jsp"%>

<br>
<br>

	<div class="container">
		<div class="row center">
			<div class="col-md-8">

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<FORM METHOD="post" ACTION="feastinfo.do" name="form1">

					<form>
						<input type="hidden" name="fea_no" value="<%=feastInfoVO.getFea_no()%>">
						<div class="from-group row">
							<div class="col-sm-6">
								<label>餐廳名稱:</label> <input type="TEXT" name="fea_resNo"
									class="form-control"
									value="<%=(feastInfoVO == null) ? "RS000001" : feastInfoVO.getFea_resNo()%>" />
							</div>
							<div class="col-sm-6">
								<label>標題:</label> <input type="TEXT" name="fea_title"
									class="form-control"
									value="<%=(feastInfoVO == null) ? "鼎王" : feastInfoVO.getFea_title()%>">
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-12">
								<label>飯局簡介:</label>
								<textarea name="fea_text" class="form-control" rows="5"><%=(feastInfoVO == null) ? "謝師宴來吃鼎王" : feastInfoVO.getFea_text()%></textarea>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-6">

								飯局類型: <select class="browser-default custom-select" class="form-control" 
								name="fea_type" id="fea_type" onchange="otherSelect()">
									<option class="typeSelect" selected disabled>選擇飯局類型:</option>
									<option class="typeSelect" value="內用">內用</option>
									<option class="typeSelect" value="外帶">外帶</option>
									<option class="typeSelect" value="外送">外送</option>
								</select>
							</div>
							<div class="col-sm-6">
								<div id="otherBox" style="visibility: hidden;">
									<label>訂餐截止日期:</label> <input class="form-control"
										name="fea_endDate" id="enddate"
										value="<%=(feastInfoVO == null) ? "" : ((feastInfoVO.getFea_endDate() != null) ? dateFormat.format(endDate) : "")%>">
								</div>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-6">
								<label>飯局日期:</label> <input class="form-control" name="fea_date"
									id="feastdate">
							</div>
							<div class="col-sm-6">
								<label>報名截止日期:</label> <input class="form-control"
									name="fea_startDate" id="startdate"
									value="<%=(feastInfoVO == null) ? "" : dateFormat.format(startDate)%>">
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-12">
								<label>飯局地點:</label> <input type="TEXT" name="fea_loc" size="45"
									class="form-control"
									value="<%=(feastInfoVO == null) ? "我是餐廳地址我是餐廳地址" : feastInfoVO.getFea_loc()%>" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-6">

								<section class="range-slider">
									<span class="rangeValues"></span> <input
										value="<%=(feastInfoVO == null) ? "2" : feastInfoVO.getFea_lowLim()%>"
										min="2" max="20" step="1" type="range" name="fea_lowLim">
									<input
										value="<%=(feastInfoVO == null) ? "20" : feastInfoVO.getFea_upLim()%>"
										min="2" max="20" step="1" type="range" name="fea_upLim"
										class="form-control">
								</section>
							</div>
								<div class="col-sm-6">
					  			<input type="hidden" name="action" value="update" >
							<input type="submit" value="送出修改" class="form-control btn btn-primary">
							</div>
								
						</div>

					</form>
				</FORM>

			</div>
		</div>
	</div>

</body>

<script>



function otherSelect() {
	
	  var other = document.getElementById("otherBox");
	  var pick = document.getElementById("fea_type").options[document.getElementById("fea_type").selectedIndex].value;
	  if (pick != "內用" && pick != "選擇飯局類型:") { other.style.visibility = "visible";}
	  else { other.style.visibility = "hidden";
	  $( "#feastdate" ).datepicker( "option", "disabled", true );
	  $( "#feastdate" ).val() = null;}
	}
</script>



<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

$('#startdate').attr('readonly', 'readonly');
$('#enddate').attr('disabled', 'disabled');

$.datetimepicker.setLocale('zh');

$('#feastdate').datetimepicker({
	   theme: '',              //theme: 'dark',
	   timepicker:true,       //timepicker:true,
	   step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	   format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
	   value: '<%=feastDate%>',
		minDate : new Date(),
		onShow : function() {
			this.setOptions({}, $('#startdate').removeAttr('readonly'))
		}
	});


	$('#startdate').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
		minDate : '-1970-01-01 00:00:00',
		onShow : function() {
			this.setOptions({
				maxDate : $('#enddate').val() ? $('#enddate').val() : false,
				maxDate : $('#feastdate').val() ? $('#feastdate').val() : false
			}, $('#enddate').removeAttr('disabled'))
		}
	});

	//enddate的datetimepicker
	$('#enddate').datetimepicker(
			{
				theme : '', //theme: 'dark',
				timepicker : true, //timepicker:true,
				step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
				format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
				minDate : '-1970-01-01',
				// 		在小視窗打開之後做某些事
				onShow : function(){
			   this.setOptions({
						// 				   minDate:設定最小可選日期  
						// 				       冒號後為值，從其他欄位選擇過來的值，該欄位有值填該欄位的值，沒有的話false
						minDate : $('#startdate').val() ? $('#startdate').val()
								: false,
						maxDate : $('#feastdate').val() ? $('#feastdate').val()
								: false
					})
				}
			});


</script>

</body>
</html>