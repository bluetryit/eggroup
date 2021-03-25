<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


<%
  MemVO memVO = (MemVO)request.getAttribute("memVO");
%>


<!doctype html>

<html lang="en">
<head>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/js/address.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">






<title>�|���ӤH���</title>

<c:if test="${not empty errorMsgs}">
	<font style="color: red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">



<title>�ӤH�|�����</title>
<style type="text/css">
body {
	font-family: "�L�n������";
}

.title {
	display: flex;
	justify-content: space-between;
}
</style>
<script>
	$(document).ready(function() {

		var readURL = function(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.avatar').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$(".file-upload").on('change', function() {
			readURL(this);
		});
	});
</script>


</head>
<body>
	<%@ include file="/header.jsp"%>
	<br>
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<jsp:useBean id="MemVO" scope="page" class="com.mem.model.MemVO" />
	<div class="container">

			<!--/col-3-->
			<div class="col-sm-9">


					


						<form class="form" action="<%=request.getContextPath()%>/front-end/mem/mem.do" method="post"
							id="registrationForm" enctype="multipart/form-data">
							<input type="hidden" name="action" value="insert">
							<div class="row">
								<div class="form-group col-sm-6">
									<label for="first_name"><h4>�|���m�W</h4></label> <input
										class="form-control" name="mem_name" id="first_name"
										placeholder="�A���W�r" title="enter your  name ."
										value="${memberVO.mem_name}">
								</div>
								<div class="form-group col-sm-6">
									<label for="last_name"><h4>�|���ʧO</h4></label> <input type="text"
										class="form-control" name="mem_sex" id="last_name"
										placeholder="�p:�k�ʡB�k��" title="sex" value="${memberVO.mem_sex}">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6">
									<label for="phone"><h4>�X�� �~/��/��</h4></label> <input type="date"
										class="form-control" name="mem_bd" id="phone"
										placeholder="�~/��/��" title="Birthday"
										value="${memberVO.mem_bd}">
								</div>
								<div class="form-group col-sm-6">
									<label for="mobile"><h4>�s���q��</h4></label> <input type="text" maxlength="10"
										class="form-control" name="mem_ph" id="mobile"
										placeholder="�A���q�ܸ��X" title="PhoneNumber"
										value="${memberVO.mem_ph}">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6">
									<label for="email"><h4>Email</h4></label> <input type="email"
										class="form-control" name="mem_email" id="email"
										placeholder="you@email.com" title="enter your email."
										value="${memberVO.mem_email}">
								</div>
								
								<div class="form-group col-sm-6">
									<label for="location"><h4>�a�}</h4></label> <br>
<!-- 									<input type="text" -->
<!-- 										class="form-control" name="mem_adrs" id="location" placeholder="�Y�a" -->
<%-- 										title="enter a location" value="${memberVO.mem_adrs}"> --%>
										
									<select id="zone1"  name="zone1" style="width: 110px;"></select>
									<select id="zone2"  name="zone2" style="width: 110px;"></select>
							        <input	type="text" id="zipcode" name="zipcode" style="width: 40px;"  readonly='true'/>
							        <input	type="text" name="address" value="�������@�q�@��1��" style="width: 268px;"><p>

								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6">
									<label for="ac"><h4>�b��</h4></label><span id="isE"></span>
									 <input type="text"
										class="form-control" name="mem_ac" id="ac" placeholder="�A���b��"
										title="enter your password." value="${memberVO.mem_ac}">
								</div>

								<div class="form-group col-sm-6">

									<label for="password2"><h4>�K�X</h4></label> <input
										type="password" class="form-control" name="mem_pass"
										id="password2" placeholder="�A���K�X"
										title="enter your password2." value="${memberVO.mem_pass}">
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">

									<label for="AboutMe"><h4>�ۧڤ���</h4></label>
									<textarea name="mem_intro" class="form-control"
										placeholder="Say~Something" id="AboutMe"
										style="height: 150px;">${memberVO.mem_intro}</textarea>
								</div>
								<div class="col-xs-3">
									<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
										class="avatar img-circle-top" alt="avatar">
									<h6>�|���j�Y��</h6>
									<input name="mem_img" type="file"
										class="text-center center-block file-upload">

								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-3">
									<br>
									<button id="submit" class="btn btn-lg btn-outline-success" type="submit">
										<i class="glyphicon glyphicon-ok-sign">����</a></i> 
									</button>>
									<button class="btn btn-lg btn-outline-secondary" type="reset">
										<i class="glyphicon glyphicon-repeat"></i> �^�W��
									</button>
									
								</div>
							</div>
						</form>
	<button id="magic" style="opacity:0.1" onclick="setData()">�o��</button>
					</div>
					<!--/tab-pane-->
					<!---------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</div>

	</div>
	<!--/tab-pane-->
	</div>
	<!--/tab-content-->

	</div>
	<!--/col-9-->
	</div>
	<!--/row-->


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script type="text/javascript">
		$(function() {
			init_address();
			$("#zone1").val(''); //����
			for ( var i in zip['']) {
				o = document.createElement('option');
				o.text = i;
				o.value = i;
				zone2.options.add(o);
			}
			$("#zone2").val(''); //�m����
			$("input[name='zipcode']").val(''); //�l���ϸ�
		})
	</script>
<script type="text/javascript">
		function setData(){
			$('#first_name').val('�j�î��A');
			$('#last_name').val('�k��');
			$('#mobile').val('0955702775');
			$('#phone').val('2007-04-26');
			$('#email').val('z0982282007@gmail.com');
			$('#ac').val('09');
			$('#password2').val('999');
			$('#AboutMe').val('�j�a�n�A�ڬO�j�áA�i�H�s��David�C');
			
				init_address();
				$("#zone1").val('�x�_��').change(); //����
				for ( var i in zip['']) {
					o = document.createElement('option');
					o.text = i;
					o.value = i;
					zone2.options.add(o);
				}
				$('#zone2').val('������');
				$("input[name='zipcode']").val('100'); //�l���ϸ�
			
			
			
			
			
			
		}
</script>
	
	<!--�ޥ�jQuery-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!--�ޥ�SweetAlert2-->
<script type="text/javascript" src="https://unpkg.com/sweetalert2@7.0.7/dist/sweetalert2.all.js"></script>


<script>
$("#submit").click(function () {
	swal("�w�H�e���ҫH","�Цܱz���H�c�i������", "info").then(function (result) {
	//�ɭ��g�b��
	window.location.href = "<%=request.getContextPath()%>/hometag.jsp";
	});
	});
	


$('#ac').on('blur', function (){
    var xmlhttp = new XMLHttpRequest();
    var mem_ac = $('#ac').val(); 
    if(mem_ac.trim().length!=0){
        var url = "memExist.jsp?mem_ac=" + mem_ac;
        xmlhttp.onreadystatechange = function(){
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
//             	alert(xmlhttp.responseText == 'true');
            	
             	var flag = xmlhttp.responseText.trim();
                if(flag == 'true')
                {
                    document.getElementById("isE").style.color = "red";
                    document.getElementById("isE").innerHTML = '�b���w�s�b�A�Э��s��J';
                }
                else
                {
                	document.getElementById("isE").style.color = "green";
                	document.getElementById("isE").innerHTML = '���b���i���U';
                }
                   
                
            }
            
        };
        try{
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
    	}catch(e){alert("unable to connect to server");
    }
}else
{
	document.getElementById("isE").style.color = "red";
	document.getElementById("isE").innerHTML = '�п�J�b��';
}
}) 


</script>

</body>
</html>