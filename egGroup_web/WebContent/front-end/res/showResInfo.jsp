<%@page import="javax.naming.Context"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*,com.tools.*,com.res.model.*,com.tools.*,
				com.fooditem.model.*"%>

<% 
	ResVO resVO = (ResVO)request.getAttribute("resVO");
	pageContext.setAttribute("resVO", resVO);
	FooditemService fooditemSvc = new FooditemService();
	List<FooditemVO> list = fooditemSvc.getByResNOFooditem(resVO.getRes_no());
	String level[] = {"Free","Inexpensive","Moderate","Expensive","Very Expensive"};
	pageContext.setAttribute("level",level);
	pageContext.setAttribute("list",list);
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
    
    <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <style>
    .map-box {
	background-color: #A3CCFF;
	z-index: 3;
	position: absolute;
	height: 600px;
	width: 100%;
	padding: 0px;
	border-width: 0px;
	margin: 0px;
	touch-action: pan-x pan-y;
}
    </style>
    
    <title>Welcome to EGG !</title>
  </head>
  <body>
  <%@ include file="/header.jsp"%>
	<div class="container">

		<div class="col-md-12">
			<div class="row">
				<div class="col-md-3">
					<img alt="Bootstrap Image Preview" src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=<%=resVO.getRes_no()%>" class="img-thumbnail" />
				</div>
				<div class="col-md-9">
				<h2><%=resVO.getRes_name()%></h2>
					<table>
						<tr>
							<td>
								類別：
							</td>
							<td>
								<%=resVO.getRes_type() %>
							</td>
						</tr>
						<tr>
							<td>
								地址：
							</td>
							<td>
								<%=resVO.getRes_adrs() %>
							</td>
						</tr>
						<tr>
							<td>
								電話：
							</td>
							<td>
								<%=resVO.getRes_ph() %>
							</td>
						</tr>
						<tr>
							<td>
								消費水準：
							</td>
							<td>
								${(resVO.res_cost == 0)? level[0]:''}
								${(resVO.res_cost == 1)? level[1]:''}
								${(resVO.res_cost == 2)? level[2]:''}
								${(resVO.res_cost == 3)? level[3]:''}
								${(resVO.res_cost == 4)? level[4]:''}
							</td>
						</tr>
						<tr>
							<td>
								餐廳介紹：
							</td>
							<td>
								<pre class="lead" style="word-break:break-all"><%=resVO.getRes_intro() %></pre>
							</td>
						</tr>
						<tr>
							<td>
								餐廳評分：
							</td>
							<td>
					 			<c:forEach begin="1" end="${resVO.res_score/resVO.res_comcount}">
									<i class="fa fa-star"></i>
								</c:forEach>
							</td>
						</tr>						
					</table>
					<a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/feast/addFeast.jsp?res_no=${resVO.res_no}">開新飯局</a>
				</div>
			</div>
			<div class="row">
			<c:forEach var="fooditemVO" items="${list}">
				<div class="col-3">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/back-end/ord/resOrdPhoto.do?fo_no=${fooditemVO.fo_no}"
							class="card-img-top" width="100" height="100">
							<h5 class="card-header">${fooditemVO.fo_name}</h5>
						<div class="card-body">
							<p>${fooditemVO.fo_intro}</p>
						</div>
						<div class="card-footer">
						<div class="row">
								<div class="form-group col-sm-6">
									<label>價格:</label>
									<p>$ ${fooditemVO.fo_price}</p>
								</div>
							</div>
							</div>
						</div>
					</div>
			</c:forEach>
			<br>
			</div>	
			<div class="row">
				<div id="map" class="map-box"></div>
			</div>	
	</div>
	</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script>
var resJson = [
			{"lat": <%=resVO.getRes_lat()%>,
		 	 "lot": <%=resVO.getRes_lot()%>,
		 	 "resNo": "<%=resVO.getRes_no()%>",
		 	 "resName": "<%=resVO.getRes_name()%>",
		  	 "resAdrs": "<%=resVO.getRes_adrs()%>",
			 "resPh": "<%=resVO.getRes_ph()%>"},
	];
</script>

	<script>
			
		  var ready; // Where to store the function

		  ready = function() {
	      var script = document.createElement('script');
	      script.type = 'text/javascript';
	      script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBInFfSY1JNb6TnRXeODk1XpgWK84J_kjs&callback=initMap&language=zh-TW&region=TW';
	      document.body.appendChild(script);
	    };
	

	    $('body').ready(ready);    
	    		var map;
	    		
	    		// marker陣列
	    		var marker = [];
	    		
//	    	 	小視窗變數
	    		var infowindow;
	    		
	    		var locations = [];
	    		
	    		
	    		
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				//初始中心點
				center : {
					lat : <%=resVO.getRes_lat()%>,
					lng : <%=resVO.getRes_lot()%>
				},
				//是否可以用滾輪縮放
				scrollwheel: false,
				//初始縮放範圍
				zoom : 17,
				//地圖樣式
				styles : [
					  {
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#ebe3cd"
						      }
						    ]
						  },
						  {
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#523735"
						      }
						    ]
						  },
						  {
						    "elementType": "labels.text.stroke",
						    "stylers": [
						      {
						        "color": "#f5f1e6"
						      }
						    ]
						  },
						  {
						    "featureType": "administrative",
						    "elementType": "geometry.stroke",
						    "stylers": [
						      {
						        "color": "#c9b2a6"
						      }
						    ]
						  },
						  {
						    "featureType": "administrative.land_parcel",
						    "elementType": "geometry.stroke",
						    "stylers": [
						      {
						        "color": "#dcd2be"
						      }
						    ]
						  },
						  {
						    "featureType": "administrative.land_parcel",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#ae9e90"
						      }
						    ]
						  },
						  {
						    "featureType": "landscape.natural",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#dfd2ae"
						      }
						    ]
						  },
						  {
						    "featureType": "poi",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#dfd2ae"
						      }
						    ]
						  },
						  {
						    "featureType": "poi",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#93817c"
						      }
						    ]
						  },
						  {
						    "featureType": "poi.park",
						    "elementType": "geometry.fill",
						    "stylers": [
						      {
						        "color": "#a5b076"
						      }
						    ]
						  },
						  {
						    "featureType": "poi.park",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#447530"
						      }
						    ]
						  },
						  {
						    "featureType": "road",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#f5f1e6"
						      }
						    ]
						  },
						  {
						    "featureType": "road.arterial",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#fdfcf8"
						      }
						    ]
						  },
						  {
						    "featureType": "road.highway",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#f8c967"
						      }
						    ]
						  },
						  {
						    "featureType": "road.highway",
						    "elementType": "geometry.stroke",
						    "stylers": [
						      {
						        "color": "#e9bc62"
						      }
						    ]
						  },
						  {
						    "featureType": "road.highway.controlled_access",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#e98d58"
						      }
						    ]
						  },
						  {
						    "featureType": "road.highway.controlled_access",
						    "elementType": "geometry.stroke",
						    "stylers": [
						      {
						        "color": "#db8555"
						      }
						    ]
						  },
						  {
						    "featureType": "road.local",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#806b63"
						      }
						    ]
						  },
						  {
						    "featureType": "transit.line",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#dfd2ae"
						      }
						    ]
						  },
						  {
						    "featureType": "transit.line",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#8f7d77"
						      }
						    ]
						  },
						  {
						    "featureType": "transit.line",
						    "elementType": "labels.text.stroke",
						    "stylers": [
						      {
						        "color": "#ebe3cd"
						      }
						    ]
						  },
						  {
						    "featureType": "transit.station",
						    "elementType": "geometry",
						    "stylers": [
						      {
						        "color": "#dfd2ae"
						      }
						    ]
						  },
						  {
						    "featureType": "water",
						    "elementType": "geometry.fill",
						    "stylers": [
						      {
						        "color": "#b9d3c2"
						      }
						    ]
						  },
						  {
						    "featureType": "water",
						    "elementType": "labels.text.fill",
						    "stylers": [
						      {
						        "color": "#92998d"
						      }
						    ]
						  }
						]
			});
				
			
			infowindow = new google.maps.InfoWindow;
			
<%-- // 			座標 = new google.maps.LatLng(<%=list.get(i + (whichPage - 1) * 20).getRes_lat()%> --%>
// 					之後一長串都是小視窗內容
// 			var locations = [
// 				[座標, 小視窗內容,], [座標, 小視窗內容,], [座標, 小視窗內容,]]
			
			setAllLocations(resJson);
				 
			setAllListener(locations);

		
};
	$body = $("body");

	$(document).on({
	    ajaxStart: function() { $body.addClass("loading");    },
	     ajaxStop: function() 
	     { $body.removeClass("loading"); }    
	     
	});
	
	
	
	function setAllLocations(resJson){
		
		console.log('setAllLocations');
		

		resJson.forEach(function(element) {
			locations.push([new google.maps.LatLng(element['lat'], element['lot']), "<div class='map_info_wrapper'>"+ 
		    	"<div class='img_wrapper'><div class='media2'>"+
						    	"<img width='400' height='200' src=<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=" + element['resNo'] + "><div class='media-body pl-3'>" +
						    	"<div class='price'>" + element['resName'] +
			 			    	"</div>"+
			 			    	"<div class='stats'>"+
						    		"<i class='fa fa-building'>"+ element['resAdrs'] +
			 			    		 "<br>"+
						    		"<i class='fa fa-phone'></i>" + element['resPh'] +
			 			    	"</div>"+
			 			    	"<div class='address'>"+
			 			    		"<a href='#'>"+"點我進入餐廳貼文"+"</a>"+
			 			    	"</div>"+
			 			    "</div>"+
			 			    "</div>"]);
			});
	};
	
	
	function setAllListener(locations){
		console.log("setAllListener");
//	 	自訂marker樣式及大小
		var iconBefore = {url:'<%=request.getContextPath()%>/images/ook.png', scaledSize: new google.maps.Size(36, 48)};
		var iconAfter = {url:'<%=request.getContextPath()%>/images/ook.png', scaledSize: new google.maps.Size(48, 60)};

		//事先清空marker陣列
		marker = [];
		
		// $.each迴圈 前面放變數, 後面放方法
		// location為上面的座標陣列，i為迭代變數，item為迭代location內每個物件之暫存變數名稱
		$.each(locations, function(i, item) {

//		 	把marker放進marker陣列裡
		// item[0]為座標, icon為自訂義marker樣式
		      marker.push((new google.maps.Marker({
		      position: item[0],
		      map: map,
		      icon: iconBefore,
		      
		      opened:false,
		    })));
		    
		  	

		//外部div mouseover 打開視窗
		// setContent設定視窗內容, open打開視窗


		//以下方法皆為改變marker大小的行為     
		    google.maps.event.addListener(marker[i], 'click', (function(marker) {

		      return function() {
		   	  	
		    	infowindow.setContent(item[1]);
			
		        if (infowindow.getMap() == null){
		        	
		    		infowindow.open(map, marker);
		    		marker.setIcon(iconAfter);
		    		
		    	}
		        
		      }

		    })(marker[i]));
		    
		    google.maps.event.addListener(marker[i], 'mouseover', (function(marker) {

		        return function() {
		        	if (marker.getIcon().scaledSize.equals(iconBefore.scaledSize) && !(marker.opened)){
		        			
		        	 		marker.setIcon(iconAfter);
		        	 		
		       	 	}
		        	
		        }

		      })(marker[i]));

		    google.maps.event.addListener(marker[i], 'mouseout', (function(marker) {

		        return function() {
		          
		        	if (!marker.getIcon().scaledSize.equals(iconBefore.scaledSize) && !(marker.opened)){
		        		marker.setIcon(iconBefore);
		        	}
		        	
		        }

		      })(marker[i]));
		    
		  });

		google.maps.event.addListener(map, 'click', (function(marker) {

		    return function() {
		    	
		    	marker.forEach(function(element) {
		    		element.setIcon(iconBefore);
		    		element.opened = false;
		    		});
		    	
		    infowindow.close(map, marker);
		}

		  })(marker));
		  
		google.maps.event.addListener(infowindow, 'closeclick', (function(marker) {

		    return function() {
		    	
		    	marker.forEach(function(everyElements) {
		    		everyElements.setIcon(iconBefore);
		    		everyElements.opened = false;
		    		});
		    	
		    	if(infowindow.getMap() != null){
		    		
		    		infowindow.close(map, marker);
		    	}
		      
		    }

		  })(marker));



		marker.forEach(function(element) {
				google.maps.event.addListener(element, 'click', (function(element) {
			
				    return function() {
				    		
					    	marker.forEach(function(everyElements) {
					    		
					    		everyElements.setIcon(iconBefore);
					    		everyElements.opened = false;
					    		
					    		});
					    	
					    	element.setIcon(iconAfter);
					    	element.opened = true;
					    	infowindow.open(map, element);
					    	
				    }
			
				  })(element));
		});
		
	};
</script>
</body>
</html>