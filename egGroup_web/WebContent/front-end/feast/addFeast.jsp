<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feastinfo.model.*, java.util.*, java.text.*, com.res.model.*"%>


<%
    FeastInfoVO feastInfoVO = (FeastInfoVO) request.getAttribute("feastInfoVO");
	String resNo = (String) request.getParameter("res_no");
	ResService resService = new ResService();
	ResVO resVO =  resService.getOneRes(resNo);
%>
<% 
	String res_city = null;
	
	try{
		res_city = feastInfoVO.getFea_loc().substring(0,3);
	}catch(Exception e){
		res_city = null;
	}

	String res_town = null;
	
	int x = 0;
	if(feastInfoVO!=null && feastInfoVO.getFea_loc().length() !=0){
		if(feastInfoVO.getFea_loc().indexOf("鄉", 3)==4 ||
				   feastInfoVO.getFea_loc().indexOf("鎮", 3)==4 ||
				   feastInfoVO.getFea_loc().indexOf("市", 3)==4 ||
				   feastInfoVO.getFea_loc().indexOf("區", 3)==4){
					x=5;
				}else if(feastInfoVO.getFea_loc().indexOf("鄉", 3)==5 ||
						 feastInfoVO.getFea_loc().indexOf("鎮", 3)==5 ||
						 feastInfoVO.getFea_loc().indexOf("市", 3)==5 ||
						 feastInfoVO.getFea_loc().indexOf("區", 3)==5){
					x=6;
				}else if(feastInfoVO.getFea_loc().indexOf("鄉", 3)==6 ||
						 feastInfoVO.getFea_loc().indexOf("鎮", 3)==6 ||
						 feastInfoVO.getFea_loc().indexOf("市", 3)==6 ||
						 feastInfoVO.getFea_loc().indexOf("區", 3)==6 ||
						 feastInfoVO.getFea_loc().indexOf("島", 3)==6){
					x=7;
				}else{
					x=8;
				}
			}
	
	try{
		res_town = feastInfoVO.getFea_loc().substring(3,x);
	}catch(Exception e){
		res_town = null;
	}
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
}
</script>
<style>
.city, .town{width: 100%;}
.f1{float:left;margin-left:5px;margin-right:5px;width:calc(5% - 10px)}
.f2{float:left;margin-left:5px;margin-right:5px;width:calc(10% - 10px)}
.f3{float:left;margin-left:5px;margin-right:5px;width:calc(15% - 10px)}
.f4{float:left;margin-left:5px;margin-right:5px;width:calc(20% - 10px)}
.f5{float:left;margin-left:5px;margin-right:5px;width:calc(25% - 10px)}
.f6{float:left;margin-left:5px;margin-right:5px;width:calc(30% - 10px)}
.f7{float:left;margin-left:5px;margin-right:5px;width:calc(35% - 10px)}
.f8{float:left;margin-left:5px;margin-right:5px;width:calc(40% - 10px)}
.f9{float:left;margin-left:5px;margin-right:5px;width:calc(45% - 10px)}
.f10{float:left;margin-left:5px;margin-right:5px;width:calc(50% - 10px)}
.f11{float:left;margin-left:5px;margin-right:5px;width:calc(55% - 10px)}
.f12{float:left;margin-left:5px;margin-right:5px;width:calc(60% - 10px)}
.f13{float:left;margin-left:5px;margin-right:5px;width:calc(65% - 10px)}
.f14{float:left;margin-left:5px;margin-right:5px;width:calc(70% - 10px)}
.f15{float:left;margin-left:5px;margin-right:5px;width:calc(75% - 10px)}
.f16{float:left;margin-left:5px;margin-right:5px;width:calc(80% - 10px)}
.f17{float:left;margin-left:5px;margin-right:5px;width:calc(85% - 10px)}
.f18{float:left;margin-left:5px;margin-right:5px;width:calc(90% - 10px)}
.f19{float:left;margin-left:5px;margin-right:5px;width:calc(95% - 10px)}
.f20{float:left;margin-left:5px;margin-right:5px;width:calc(100% - 10px)}
</style>
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
			<div class="col md-8 xs-4">

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
						<input type="hidden" name="fea_resNo" class="form-control" value="<%=resVO.getRes_no()%>" />
						<div class="from-group row">
							<div class="col-sm-6">
								<label>餐廳名稱:</label><br>
								 <%=resVO.getRes_name()%>
							</div>
							<div class="col-sm-6">
								<label>標題:</label> <input type="TEXT" name="fea_title" id="fea_title"
									class="form-control"
									value="<%=(feastInfoVO == null) ? "" : feastInfoVO.getFea_title()%>">
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-12">
								<label>飯局簡介:</label>
								<textarea name="fea_text" id="fea_text" class="form-control" rows="5"><%=(feastInfoVO == null) ? "" : feastInfoVO.getFea_text()%></textarea>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-6">

								<label>飯局類型:</label> <select class="browser-default custom-select"
									class="form-control" name="fea_type" id="fea_type"
									onchange="otherSelect()">
									<option selected>選擇飯局類型:</option>
									<option value="內用">內用</option>
									<option value="外帶">外帶</option>
									<option value="外送">外送</option>
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
								<label>飯局地點:</label> <div id="zipcode">
					<div class="f6" data-role="county"></div>
					<div class="f8" data-role="district"></div>
				</div><input name="address" type="text" class="f5 address form-control" id="address"
				value="<%=(feastInfoVO == null) ? "" : feastInfoVO.getFea_loc().substring(x)%>">

			
			
			
			
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
					  			<input type="hidden" name="action" value="insert" >
								<input type="submit" value="送出新增" class="form-control btn btn-primary">
								</div>
								
						</div>

					</form>
				</FORM>
					<button id="magic"  style="opacity:0.1" onclick="setData()">這個</button>
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
		minDate : '-1970-01-01',
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
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>

<script>
$("#zipcode").twzipcode({
	"countySel": '<%=res_city%>', // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
	"districtSel": '<%=res_town%>', // 地區預設值
	"zipcodeIntoDistrict": true,
	"css": ["city form-control", "town form-control"],
	"countyName": "res_city", // 指定城市 select name
	"districtName": "res_town" // 指定地區 select name
	});
</script>
<script type="text/javascript">
		function setData(){
			$('#fea_title').val('JAVA飯吃到飽~');
			$('#fea_text').val('爪哇風味超好吃，大推蜂蜜啤酒只要200');
			$('#fea_type').val('外送').change();
			$('#feastdate').trigger('focus'); //飯局日期
			$('#feastdate').val('2019-08-01 14:50'); //飯局日期
			$('#startdate').trigger('focus'); //報名截止日期
			$('#startdate').val('2019-08-01 14:30'); //報名截止日期
			$('#enddate').trigger('focus'); //訂餐截止日期
			$('#enddate').val('2019-08-01 14:40'); //訂餐截止日期
			$('#address').val('中大路300號'); // 不含縣市區
		}
</script>
</body>
</html>