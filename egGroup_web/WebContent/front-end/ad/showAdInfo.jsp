<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*,com.tools.*,com.res.model.*"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
	ResService resSvc = new ResService();
	ResVO resVO = resSvc.getOneRes(adVO.getAd_resno());
	
	pageContext.setAttribute("resVO", resVO);
	String res_city = null;
	
	try{
		res_city = resVO.getRes_adrs().substring(0,3);
	}catch(Exception e){
		res_city = null;
	}

	String res_town = null;
	String address = null;
	int x = 0;
	if(resVO!=null && resVO.getRes_adrs().length() !=0){
		if(resVO.getRes_adrs().indexOf("鄉", 3)==4 ||
				   resVO.getRes_adrs().indexOf("鎮", 3)==4 ||
				   resVO.getRes_adrs().indexOf("市", 3)==4 ||
				   resVO.getRes_adrs().indexOf("區", 3)==4){
					x=5;
				}else if(resVO.getRes_adrs().indexOf("鄉", 3)==5 ||
						 resVO.getRes_adrs().indexOf("鎮", 3)==5 ||
						 resVO.getRes_adrs().indexOf("市", 3)==5 ||
						 resVO.getRes_adrs().indexOf("區", 3)==5){
					x=6;
				}else if(resVO.getRes_adrs().indexOf("鄉", 3)==6 ||
						 resVO.getRes_adrs().indexOf("鎮", 3)==6 ||
						 resVO.getRes_adrs().indexOf("市", 3)==6 ||
						 resVO.getRes_adrs().indexOf("區", 3)==6 ||
						 resVO.getRes_adrs().indexOf("島", 3)==6){
					x=7;
				}else{
					x=8;
				}
			}
	
	try{
		res_town = resVO.getRes_adrs().substring(3,x);
		address = resVO.getRes_adrs().substring(x);
	}catch(Exception e){
		res_town = null;
	}
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Welcome to EGG !</title>
    
  </head>
  <body>
  <%@ include file="/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2">
				<a class="pull-right"><img class="rounded-circle" alt="Bootstrap Image Preview" width="200" height="200"
					src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=<%=resVO.getRes_no()%>"></a>
				</div>
				<div class="page-header col-md-10">
					<h1>
						${resVO.res_name}
					</h1>
				</div>
			</div>
			<br>
			<div class="carousel slide" id="carousel-478122">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img style="width:980px; height:700px;" alt="Carousel Bootstrap First" src="<%=request.getContextPath()%>/back-end/resAd/resAdPhoto.do?ad_no=${adVO.ad_no}" />
					</div>
				</div> 
			</div>
			<br>
			<h3 class="text-center text-primary">
				${adVO.ad_title}
			</h3>
			<br>
<!-- 			<p class="lead"> -->
			<pre class="lead" style="word-break:break-all">${adVO.ad_text}</pre>
<%-- 				<textarea class="lead" style="resize:none;border: 0px;outline : 0;background-color: #D9C5A8; width:1080px;">${adVO.ad_text}</textarea> --%>
<!-- 			</p>  -->
			<br>
			<address>
				 <strong>餐廳聯絡資訊</strong>
				 <br />
				 <br /><%=res_city%><%=res_town%>
				 <br /><%=address%>
				 <br /><abbr>營業時間 :</abbr><%=resVO.getRes_start()%> ~ <%=resVO.getRes_end()%>
				 <br /><abbr>連絡電話 :</abbr><%=resVO.getRes_ph()%>
			</address> 
			<br>
			<a>
			<button 
			onclick="location.href='<%=request.getContextPath()%>/front-end/res/res.do?res_no=${resVO.res_no}&action=showResInfo'"
			type="button" class="btn btn-block btn-md btn-outline-primary">
				餐廳頁面
			</button>
		</div>
	</div>
</div>
<br>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <%@ include file="/footer.jsp"%>
  </body>
</html>