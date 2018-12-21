<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/examPage.css">
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
        </script>
	
<title>Thi</title>
</head>

<body>
	<div class="header">
		<h1>${exam.name}</h1>
		<p>Mã số: ${exam.id}. Thời gian: ${exam.totalTime} phút.</p>
	</div>
	<div class="main-container">
		<div class="right-container">
			<p>${candidate.name}</p>
			<div class="total-question">
				<c:forEach items="${questions}" var="quest" varStatus="status">
					<a href="#quest${status.index}">${status.index+1}</a>
				</c:forEach>
			</div>

			<h3 id="timer"></h3>

			<form id="form-id" name="submit-form"
				action="${pageContext.request.contextPath}/Thi" method="POST"
				onsubmit="return deleteTime()">
				<input type="hidden" name="examId" value="${exam.id}"> <input
					id="btn-submit" class="button-submit" type="submit" value="Nộp bài"
					onsubmit="">
			</form>
		</div>

		<div class="question">
			<c:forEach items="${questions}" var="quest" varStatus="status">
				<div id="quest${status.index}" class="question-item">
					<p>Câu hỏi ${status.index+1}: ${quest.content}</p>

					<input form="form-id" type="hidden" name="quest${status.index}"
						value="${quest.id}">

					<c:forEach items="${quest.answers}" var="item" varStatus="i">
						<label> <input form="form-id" type="radio"
							name="answer${status.index}" value="${item.id}">
							${item.content}
						</label>
					</c:forEach>
				</div>

			</c:forEach>
		</div>
	</div>

	<script>
		document.getElementById("form-id").onsubmit = function() {
		    return deleteTime();
		};
	</script>

	<script>
        function deleteTime() {
        	deleteLocalStorage('count');
        	return true;
        }
    </script>

	<script type="text/javascript">
		// properties
		var count = 0;
		var counter = null;
		
		window.onload = function() {
		  	initCounter();
		};
		
		function initCounter() {
		  	// get count from localStorage, or set to initial value of 3600
		  	count = getLocalStorage('count') || ${exam.totalTime} * 60;
		  	//count = ${exam.totalTime};
		  	counter = setInterval(timer, 1000); //1000 will  run it every 1 second
		}
		
		function setLocalStorage(key, val) {
		  	if (window.localStorage) {
		    	window.localStorage.setItem(key, val);
		  	}
		  	return val;
		}
		
		function getLocalStorage(key) {
		  	return window.localStorage ? window.localStorage.getItem(key) : '';
		}
		
		function deleteLocalStorage(key){
		 	return localStorage.removeItem(key);
		}
		
		function timer() {
		  	count = setLocalStorage('count', count - 1);
		  	if (count == -1) {
			    clearInterval(counter);
			    deleteLocalStorage('count');
			    document.getElementById("form-id").submit();
			    return;
		  	}
		
		  	var seconds = count % 60;
		  	var minutes = Math.floor(count / 60);
		  	var hours = Math.floor(minutes / 60);
		  	minutes %= 60;
		  	hours %= 60;
		
		  	document.getElementById("timer").innerHTML = hours +  ":"  + minutes +  ":"   + seconds;
		}
	 </script>


</body>

</html>

