<%@page import="oracle.net.aso.a"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooditem.model.*, com.mem.model.*,com.res.model.*,com.ord.model.*,com.feastinfo.model.*,com.fooditem.model.*
                ,com.ord_details.model.*,com.tools.*" %>
    
<% 
 		MemVO memVO = (MemVO)session.getAttribute("memberVO");
		OrdService ordSvc = new OrdService();
		Ord_detailsService ordDSvc = new Ord_detailsService();
		FooditemService foSvc = new FooditemService();
		
		//該會員所有訂單 > 該訂單所有商品
		//餐廳所有飯局'RS000003' > 飯局所有訂單 >訂單所有商品
	    FeastInfoService feaSvc = new FeastInfoService();
	    Map<String,List<FooditemVO>> groupMap = new LinkedHashMap<String,List<FooditemVO>>();
	    
		
	    List<OrdVO> ordList = ordSvc.getAllOrdByMem(memVO.getMem_no());//該會員所有訂單

	    for (OrdVO ordVO : ordList) {
	    	List<FooditemVO> list = new ArrayList<FooditemVO>();
	    	List<Ord_detailsVO> ordDList = ordDSvc.getAlldetByOrdno(ordVO.getOrd_no());//拿出該訂單的所有訂單明細
			for (Ord_detailsVO ord_detailsVO : ordDList) {
				FooditemVO foVO = foSvc.getOneFooditem(ord_detailsVO.getDet_fono());//拿出該訂單明細 準備加入list ==
				foVO.setFo_quantity(ord_detailsVO.getDet_quantity());
				list.add(foVO);
			}
			groupMap.put(ordVO.getOrd_no(), list); //key = 訂單編號
		}

		Set<String> set = groupMap.keySet();
		
		pageContext.setAttribute("memVO",memVO);
		pageContext.setAttribute("ordList",ordList);
		pageContext.setAttribute("ordSvc",ordSvc);
		pageContext.setAttribute("feaSvc",feaSvc);
		pageContext.setAttribute("groupMap",groupMap);
		pageContext.setAttribute("set",set);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Welcome to EGG !</title>
</head>
<body>
<%@ include file="/header.jsp"%>

    <div class="container">
    	<div class="row">
    		<div class="col-sm-12">
    		<div style="text-align:center;">
    			<h1 class="center">所有訂單</h1>
    		</div>
    		<c:forEach var="ordVO" items="${set}"> <!-- ordVO=飯局編號 -->
    		<c:if test="${groupMap.get(ordVO).size() != 0}">
    			<div class="accordion" id="accordionExample">
				  <div class="card">
				    <div class="card-header" id="heading${ordVO}">
				      <h2 class="mb-0">
				        <button class="btn" type="button" data-toggle="collapse" data-target="#collapse${ordVO}" aria-expanded="true" aria-controls="collapse${ordVO}">
				        <table><tr>
				        <td>訂單編號：${ordVO}</td>　
				        <td>飯局編號：${feaSvc.getOneFeastInfo(ordSvc.getOneOrd(ordVO).ord_fea_no).fea_no}</td>　
				        <td>飯局名稱：${feaSvc.getOneFeastInfo(ordSvc.getOneOrd(ordVO).ord_fea_no).fea_title}</td>　
				        <td>飯局人數：${feaSvc.getOneFeastInfo(ordSvc.getOneOrd(ordVO).ord_fea_no).fea_number}</td>　
				        <td>飯局用/取餐時間：${feaSvc.getOneFeastInfo(ordSvc.getOneOrd(ordVO).ord_fea_no).fea_date}</td>
				        <td>飯局訂單總金額：${ordSvc.getOneOrd(ordVO).ord_price}</td>
				        <td>訂單狀態：${FindCodeName.meaning(ordSvc.getOneOrd(ordVO).ord_status)}</td>
				        </tr></table>
				        </button>
				      </h2>
				    </div>

				    <div id="collapse${ordVO}" class="collapse" aria-labelledby="heading${ordVO}" data-parent="#accordionExample">
				        <c:forEach var="fooditemVO" items="${groupMap.get(ordVO)}">
				        <div class="list-group list-group-flush">
						  <a href="#" class="list-group-item list-group-item-action">
						  <table><tr>
						 <td> 品項：${fooditemVO.fo_name}</td>
						 <td> 單價：${fooditemVO.fo_price}</td>
						 <td> 數量：${fooditemVO.fo_quantity}</td>
						 <td> 小計：$${fooditemVO.fo_price*fooditemVO.fo_quantity}</td>
						  </tr></table>
						  </a>
						</div>
						 </c:forEach>
				    </div>
				  </div>
				</div>
				</c:if>
			</c:forEach>
			
    		</div>
    	</div>
    </div>

</body>
</html>