<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportlm.model.*"%>

<!DOCTYPE html>
<html lang="en"> 

<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" -->
<!-- 	crossorigin="anonymous"> -->
<!-- <link rel="stylesheet" href="css/all.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<style>
</style>
</head>

<body>
	<%@ include file="/back-end/BackHeader.jsp"%>
	<br>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-12">
				<%-- <div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block justify-center"
								src="<%=request.getContextPath()%>/images/ooo.gif"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block"
								src="<%=request.getContextPath()%>/images/fff.gif"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100"
								src="<%=request.getContextPath()%>/images/bossII.jpg"
								alt="Third slide">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div> --%>
				
				<canvas id="myCanvas" width="480" height="450"></canvas>
			</div>
		</div>
	</div>
<!-- 	game -->


<script>
     
            
    var canvas = document.getElementById("myCanvas");//宣告
    var ctx = canvas.getContext("2d");
    var ballRadius = 10;
    var x = canvas.width / 2;
    var y = canvas.height - 30;
    var dx = 2;
    var dy = -2;
    var paddleHeight = 10;//板子
    var paddleWidth = 75;
    var paddleX = (canvas.width - paddleWidth) / 2;
    var rightPressed = false;
    var leftPressed = false;
    var brickRowCount = 5;
    var brickColumnCount = 3;
    var brickWidth = 75;//
    var brickHeight = 30;//20
    var brickPadding = 10;
    var brickOffsetTop = 30;
    var brickOffsetLeft = 30;
    var score = 0;
    var lives = 3;
    var countdown;//向下移動用變數
    var RorL=0;//左右用變數    
    var bricks = [];
    for (c = 0; c < brickColumnCount; c++) {
        bricks[c] = [];
        for (r = 0; r < brickRowCount; r++) {
            bricks[c][r] = { x: 0, y: 0, status: 1 };
        }
    }

    document.addEventListener("keydown", keyDownHandler, false);
    document.addEventListener("keyup", keyUpHandler, false);
    document.addEventListener("mousemove", mouseMoveHandler, false);
    
    function movedown(){//使ufo向下移動
        brickOffsetTop+=15;
        countdown=setTimeout(movedown,3000);
        
        if(RorL==0){
            brickOffsetLeft-=20;
            RorL++;
        }else if(RorL==1){
            brickOffsetLeft+=10;
            RorL++;
        }
        else if(RorL==2){
            brickOffsetLeft+=10;
            RorL-=2;
        }
    }
    //
    
    
    function keyDownHandler(e) {
        if (e.keyCode == 39) {
            rightPressed = true;
        }
        else if (e.keyCode == 37) {
            leftPressed = true;
        }
    }
    function keyUpHandler(e) {
        if (e.keyCode == 39) {
            rightPressed = false;
        }
        else if (e.keyCode == 37) {
            leftPressed = false;
        }
    }
    function mouseMoveHandler(e) {
        var relativeX = e.clientX - canvas.offsetLeft;
        if (relativeX > 0 && relativeX < canvas.width) {
            paddleX = relativeX - paddleWidth / 2;
        }
    }
    function collisionDetection() {//碰撞判斷
        for (c = 0; c < brickColumnCount; c++) {             
            for (r = 0; r < brickRowCount; r++) {
                var b = bricks[c][r];
                if (b.status == 1) {
                    if (x > b.x && x < b.x + brickWidth && y > b.y && y < b.y + brickHeight) {
                        dy = -dy;
                        b.status = 0;//使圖片不再顯示
                        score++;//分數加
                        //繪製爆炸
                        //setTimeout(drawboom(), 1000);
                        //
                        var s = new Audio("img/game/bomb.mp3");
                        s.play();
                        if (score == brickRowCount * brickColumnCount) {
                            alert("YOU WIN, CONGRATS!");
                            document.location.reload();
                        }
                    }
                }
            }
        }
    }
    
    function drawboom(){
        var imgboom = new Image();
        imgboom.src = "img/game/explode.png";
        ctx.drawImage(imgboom,brickX, brickY, brickWidth, brickHeight);
    }

    function drawBall() {
        ctx.beginPath();
        ctx.arc(x, y, ballRadius, 0, Math.PI * 2);
        ctx.fillStyle = "#0095DD";
        ctx.fill();
        ctx.closePath();
    }
    function drawPaddle() {
        ctx.beginPath();
        ctx.rect(paddleX, canvas.height - paddleHeight, paddleWidth, paddleHeight);
        ctx.fillStyle = "#0095DD";
        ctx.fill();
        ctx.closePath();
    }
    function drawBricks() {
        for (c = 0; c < brickColumnCount; c++) {
            for (r = 0; r < brickRowCount; r++) {
                if (bricks[c][r].status == 1) {
                    var brickX = (r * (brickWidth + brickPadding)) + brickOffsetLeft;
                    var brickY = (c * (brickHeight + brickPadding)) + brickOffsetTop;
                    bricks[c][r].x = brickX;
                    bricks[c][r].y = brickY;
                    //ctx.beginPath();
                    //ctx.rect(brickX, brickY, brickWidth, brickHeight);
                    //ctx.fillStyle = "#0095DD";
                    //ctx.fill();
                    //ctx.closePath();
                    var image = new Image();
                    image.src = "img/game/ufo-512.png";
                    ctx.drawImage(image,brickX, brickY, brickWidth, brickHeight);
                    if(brickY>380){
                         var alertAttact = new Audio("img/game/blip1.mp3");
                        alertAttact.play();
                    }
                    if(brickY>=400){
                        alert("Alien Arived YOU LOSE");
                        document.location.reload();
                    }
                }
            }
        }
    }
    function drawScore() {
        ctx.font = "16px Arial";
        ctx.fillStyle = "#ff0000";
        ctx.fillText("Score: " + score, 8, 20);
    }
    function drawLives() {
        ctx.font = "16px Arial";
        ctx.fillStyle = "#ff0000";
        ctx.fillText("Lives: " + lives, canvas.width - 65, 20);
    }

    function draw() {
        var s = new Audio("img/game/Hidden_Agenda");s.play();
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawBricks();
        drawBall();
        drawPaddle();
        drawScore();
        drawLives();
        collisionDetection();
    
        if (x + dx > canvas.width - ballRadius || x + dx < ballRadius) {
            dx = -dx;
        }
        if (y + dy < ballRadius) {
            dy = -dy;
        }
        else if (y + dy > canvas.height - ballRadius) {
            if (x > paddleX && x < paddleX + paddleWidth) {
                dy = -dy;
            }
            else {
                lives--;
                if (!lives) {
                    alert("GAME OVER");
                    document.location.reload();
                }
                else {
                    x = canvas.width / 2;
                    y = canvas.height - 30;
                    dx = 3;
                    dy = -3;
                    paddleX = (canvas.width - paddleWidth) / 2;
                }
            }
        }

        if (rightPressed && paddleX < canvas.width - paddleWidth) {
            paddleX += 7;
        }
        else if (leftPressed && paddleX > 0) {
            paddleX -= 7;
        }

        x += dx;
        y += dy;
        requestAnimationFrame(draw);
    }

    draw();
    movedown();
</script>

	
	
<!-- 	<script src="vendor/jquery/jquery.min.js"></script> -->
<!-- 	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script> -->


<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 		crossorigin="anonymous"></script> -->
<!-- 	<script -->
<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" -->
<!-- 		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" -->
<!-- 		crossorigin="anonymous"></script> -->
<!-- 	<script -->
<!-- 		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" -->
<!-- 		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" -->
<!-- 		crossorigin="anonymous"></script> -->
</body>
</html>
