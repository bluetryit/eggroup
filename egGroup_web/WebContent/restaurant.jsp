<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@page import="com.res.model.ResVO"%>
<%@page import="com.res.model.ResService"%>
<%@page import="java.util.*"%>
<html lang="en">
<head>


<style>
body{
	padding-top:33px;
}
.listing-block {
	background: #fff;
	overflow-y: scroll;
	font-family: "微軟正黑體";
}

.media {
	background: #fff;
	position: relative;
	font-family: "微軟正黑體";
}

.media img {
	width: 200px;
	margin: 0;
	height: 136px;
}

.media .price small {
	display: block;
	font-size: 17px;
	color: #232323;
	font-family: "微軟正黑體";
}

.media .stats {
	float: left;
	width: 100%;
	margin-top: 10px;
	font-family: "微軟正黑體";
}

.media .stats span {
	float: left;
	margin-right: 10px;
	font-size: 15px;
	font-family: "微軟正黑體";
}

.media .stats span i {
	margin-right: 7px;
	color: #4765AB;
	font-family: "微軟正黑體";
}

.media .address {
	float: left;
	width: 100%;
	font-size: 14px;
	margin-top: 5px;
	color: #888;
	font-family: "微軟正黑體";
}

.media .fav-box {
	position: absolute;
	right: 10px;
	font-size: 20px;
	top: 4px;
	color: #E74C3C;
	font-family: "微軟正黑體";
}

.listing-block {
	z-index: 1;
	position: absolute;
	height: 100%;
	width: 40%;
	padding: 0px;
	border-width: 0px;
	margin: 0px;
	left: 0px;
	op: 0px;
	ouch-action: pan-x pan-y;;
}

.map-box {
	background-color: #A3CCFF;
	z-index: 3;
	position: absolute;
	height: 100%;
	width: 60%;
	padding: 0px;
	border-width: 0px;
	margin: 0px;
	left: 40%;
	touch-action: pan-x pan-y;
}

.media {
	border: 2px solid #d2d2d2;;
}

.media:hover {
	border: 2px solid #EA771B;
}

.media2 {
	width: auto;
	height: auto;
}

.media2 .price {
	display: block;
	font-size: 25px;
	color: #232323;
	font-family: "微軟正黑體";
}

.media2 .stats {
	font-size: 15px;
	font-family: "微軟正黑體";
	margin: 1px;
}

.media2 img {
	float: left;
	width: 350px;
	margin: 5px;
	height: 200px;
}

.media2 .address {
	width: 100%;
	font-size: 14px;
	margin-top: 5px;
	color: #888;
	font-family: "微軟正黑體";
}

.madia2 .address a {
	width: 100%;
	font-size: 14px;
	margin-top: 5px;
	color: #888;
	font-family: "微軟正黑體";
}

.modalForLoad {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      40%;
    background: rgba( 255, 255, 255, .5 ) 
                url('http://i.stack.imgur.com/FhHRx.gif') 
                50% 50% 
                no-repeat;
}

/* When the body has the loading class, we turn
   the scrollbar off with overflow:hidden */
body.loading .modalForLoad {
    overflow: hidden;   
}

/* Anytime the body has the loading class, our
   modal element will be visible */
body.loading .modalForLoad {
    display: block;
}
</style>

</head>



<body>

	<%@ include file="\header.jsp"%>

	<div class="listing-block">

<%  int rowsPerPage = 20;  
    int rowNumber=0;      
    int pageNumber=0;      
    int whichPage=1;      
    int pageIndexArray[]=null;
    int pageIndex=0; 
	int pageElementCount=20;
	String search = "";
	boolean queryFail = false;
	
    ResService resService = new ResService();
	List<ResVO> list = new ArrayList<ResVO>();
	request.setCharacterEncoding("UTF-8");
	search = (String) request.getParameter("search");
    if(search != null && search.trim().length() != 0)
    {
		list = resService.getAllByQuery(search);
		if(list.size() == 0)
		{
		    queryFail = true;
		    list = resService.getAllOnlineRes();
		}
    }
    else
    {
        list = resService.getAllOnlineRes();
    }
    pageContext.setAttribute("list", list);
%>

<%  
    rowNumber=list.size();
    if (rowNumber%rowsPerPage != 0)
         pageNumber = rowNumber / rowsPerPage + 1;
    else pageNumber = rowNumber / rowsPerPage;    

    if(pageElementCount > rowNumber)
    {
        pageElementCount = (rowNumber % rowsPerPage);
    }
    
    pageIndexArray=new int[pageNumber]; 
    for (int i = 1; i <= pageIndexArray.length; i++)
         pageIndexArray[i - 1]=i * rowsPerPage - rowsPerPage;
%>

<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
       
       if (whichPage >= pageNumber)
        {
            whichPage = pageNumber;
            pageElementCount = (rowNumber % rowsPerPage);
        }
       
    } catch (NumberFormatException e) {
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) {
         if (pageNumber > 0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
              pageElementCount = (rowNumber % rowsPerPage);
         }
    } 
%>
		<div class="container">
		<br>
			<div class="form-row align-items-center">
				<div class="col-9">
					<input type="text" id="searchInput" class="form-control mb-2" placeholder="查詢餐廳" name="search">
				</div>
				
				<div class=" mb-2">
					<button type="submit" id="searchButton" class="btn btn-primary">&nbsp;<i class="fa fa-search"></i>&nbsp;</button>
				</div>
				
				<span id="searchResult"></span>
			</div>
		</div>



	<%if (pageNumber > 0){%>
	<b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁
	</font></b>
	<%}%>

	<c:forEach var="resVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<div class="media">
			<a class="fav-box" herf=""> <i class="fa fa-heart-o"
				aria-hidden="true"></i>
			</a> <img width="300" height="200"
				src="<%=request.getContextPath()%>/back-end/res/resPhoto.do?res_no=${resVO.res_no}">

			<div class="media-body pl-3">
				<div class="price">
					${resVO.res_name} <small>評分: </small>
					<c:if test="${resVO.res_comcount != 0}">
						<c:forEach begin="1" end="${resVO.res_score/resVO.res_comcount}">
							<i class="fa fa-star"></i>
						</c:forEach>
					</c:if>
					<c:if test="${resVO.res_comcount == 0}">
							<p>無評價</p>
					</c:if>

				</div>
				<div class="stats">
					<i class="fa fa-building"></i>${resVO.res_adrs} <br> 
					<i class="fa fa-phone"></i>${resVO.res_ph}
				</div>
				<div>
					<a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/res/res.do?res_no=${resVO.res_no}&action=showResInfo">點我進入餐廳頁面</a>
				</div>
			</div>
		</div>
	</c:forEach>
	<%if (pageNumber>1) {%>
	<!-- 底下換頁標籤 -->
	<ul class="pagination justify-content-center">

		<li class="page-item"><button class="page-link"
			value="1" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
		</button></li>

		<li class="page-item"><button class="page-link"
			value="<%=whichPage-1%>"
			aria-label="Previous"> <span aria-hidden="true">&lt;</span> <span
				class="sr-only">Previous</span>
		</button></li>

		<%if (pageNumber <= 3) {%>
		<%for(int i = 1; i <= pageNumber; i++){%>
		<li class="page-item"><button class="page-link"
			value="<%=i%>"><%=i%></button></li>
		<%}%>
		<%}else if (whichPage == 1) {%>
		<%for(int i = 1; i <= 3; i++){%>
		<li class="page-item"><button class="page-link"
			value="<%=i%>"><%=i%></button></li>
		<%}%>

		<%}else if (whichPage == pageNumber){%>
		<%for(int i = pageNumber - 2; i <= pageNumber; i++){%>
		<li class="page-item"><button class="page-link"
			value="<%=i%>"><%=i%></button></li>
		<%}%>

		<%}else{%>
		<%for(int i = 1; i <= 3; i++){%>
		<li class="page-item"><button class="page-link"
			value="<%=whichPage+i-2%>"><%=whichPage+i-2%></button></li>
		<%}%>
		<%}%>

		<li class="page-item"><button class="page-link"
			value="<%=whichPage+1%>"
			aria-label="Next"> <span aria-hidden="true">&gt;</span> <span
				class="sr-only">Next</span>
		</button></li>

		<li class="page-item"><button class="page-link"
			value="<%=pageNumber%>"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
				class="sr-only">Next</span>
		</button></li>
	</ul>
	
	<%}%>
	<div class="modalForLoad"><!-- Place at bottom of page --></div>
	</div>


	<div id="map" class="map-box"></div>


	
<script>
var resJson = [
	<%if(list.size() != 0){%>
	<%for(int i = 0; i < pageElementCount; i++){%>
			{"lat": <%=list.get(i + (whichPage - 1) * 20).getRes_lat()%>,
		 	 "lot": <%=list.get(i + (whichPage - 1) * 20).getRes_lot()%>,
		 	 "resNo": "<%=list.get(i + (whichPage - 1) * 20).getRes_no()%>",
		 	 "resName": "<%=list.get(i + (whichPage - 1) * 20).getRes_name()%>",
		  	 "resAdrs": "<%=list.get(i + (whichPage - 1) * 20).getRes_adrs()%>",
			 "resPh": "<%=list.get(i + (whichPage - 1) * 20).getRes_ph()%>"},
	<%}}%>
	];
</script>

	<script>
			
		  <%if(queryFail == true){%>
		  <%queryFail=false;%>
			document.getElementById("searchResult").style.color = "red";
            document.getElementById("searchResult").innerHTML = '查無結果';
			<%}%>
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
					lat : 25.0440299,
					lng : 121.537469
				},
				//是否可以用滾輪縮放
				scrollwheel: false,
				//初始縮放範圍
				zoom : 10,
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
			
			 // Try HTML5 geolocation.
	        if (navigator.geolocation) {
	          navigator.geolocation.getCurrentPosition(function(position) {
	            var pos = {
	              lat: position.coords.latitude,
	              lng: position.coords.longitude
	            };
	            
	            map.setCenter(pos);
	          }, function() {
	            handleLocationError(true, infoWindow, map.getCenter());
	          });
	        } else {
	          // Browser doesn't support Geolocation
	          handleLocationError(false, infoWindow, map.getCenter());
	        }
	      

	      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	        infoWindow.setPosition(pos);
	        infoWindow.setContent(browserHasGeolocation ?
	                              'Error: The Geolocation service failed.' :
	                              'Error: Your browser doesn\'t support geolocation.');
	        infoWindow.open(map);
	      }
			
			
			
<%-- // 			座標 = new google.maps.LatLng(<%=list.get(i + (whichPage - 1) * 20).getRes_lat()%> --%>
// 					之後一長串都是小視窗內容
// 			var locations = [
// 				[座標, 小視窗內容,], [座標, 小視窗內容,], [座標, 小視窗內容,]]
			
			setAllLocations(resJson);
				 
			setAllListener(locations);

		
};
//換頁標籤
$('.listing-block').on('click', '.page-link', (function() {

<%--     $(".listing-block").load("/DA101_G6/restaurant.jsp?whichPage="+ $(this).val() + "&search=<%=search%>" + " .listing-block > *"); --%>
    $('.listing-block').ready($('.listing-block').animate({ scrollTop: 0 }, 0));
	
	$.get("/DA101_G6/restaurant.jsp?whichPage=" + $(this).val() + "&search=<%=search%>",
        	function(data) {
			var $data = $(data);
			
			var $elem = $data.filter('div.listing-block').html();
			
			$("div.listing-block").empty();
			$("div.listing-block").append($elem);
			
			var $script = $data.filter("script:contains('var resJson = [')").first();
			eval($script.text());
			resJson = resJson;
			
			locations = [];
			
			setAllLocations(resJson);
		    
			    marker.forEach(function (eachMarker){
			    	
			    eachMarker.setMap(null);
			    
			    });
			    
			setAllListener(locations);
			
            });
    
  })
);
//搜尋ajax
$('.listing-block').on('click', '#searchButton', (function() {

	<%--     $(".listing-block").load("/DA101_G6/restaurant.jsp?whichPage="+ $(this).val() + "&search=<%=search%>" + " .listing-block > *"); --%>
	    $('.listing-block').ready($('.listing-block').animate({ scrollTop: 0 }, 0));
		
		$.get("/DA101_G6/restaurant.jsp?&search=" + $('#searchInput').val(),
	        	function(data) {
				var $data = $(data);
				
				var $elem = $data.filter('div.listing-block').html();
				
				$("div.listing-block").empty();
				$("div.listing-block").append($elem);
				
				var $script = $data.filter("script:contains('var resJson = [')").first();
				eval($script.text());
				resJson = resJson;
				
				locations = [];
				
				setAllLocations(resJson);
			    
				    marker.forEach(function (eachMarker){
				    	
				    eachMarker.setMap(null);
				    
				    });
				    
				setAllListener(locations);
				
	            });
	    
	  })
	);


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
					    		"<i class='fa fa-building'></i>"+ element['resAdrs'] +
		 			    		 "<br>"+
					    		"<i class='fa fa-phone'></i>" + element['resPh'] +
		 			    	"</div>"+
		 			    	"<div>"+
		 			    		"<a class='btn btn-primary' href='<%=request.getContextPath()%>/front-end/res/res.do?res_no=" + element['resNo'] + "&action=showResInfo'>點我進入餐廳頁面</a>"+
		 			    	"</div>"+
		 			    "</div>"+
		 			    "</div>"]);
		});
};

function setAllListener(locations){
	console.log("setAllListener");
// 	自訂marker樣式及大小
	var iconBefore = {url:'images/ook.png', scaledSize: new google.maps.Size(36, 48)};
	var iconAfter = {url:'images/ook.png', scaledSize: new google.maps.Size(48, 60)};

	//事先清空marker陣列
	marker = [];
	
	// $.each迴圈 前面放變數, 後面放方法
	// location為上面的座標陣列，i為迭代變數，item為迭代location內每個物件之暫存變數名稱
	$.each(locations, function(i, item) {

//	 	把marker放進marker陣列裡
	// item[0]為座標, icon為自訂義marker樣式
	      marker.push((new google.maps.Marker({
	      position: item[0],
	      map: map,
	      icon: iconBefore,
	      
	      opened:false,
	    })));
	    
	  	// 拿到地圖外部div以打開小視窗
	  	 var listing = document.getElementsByClassName('media');
	  	

	//外部div mouseover 打開視窗
	// setContent設定視窗內容, open打開視窗

		listing[i].addEventListener('mouseover', function() {
			infowindow.setContent(item[1]);
			infowindow.open( map, marker[i] );
			map.setCenter(item[0]);
		});

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