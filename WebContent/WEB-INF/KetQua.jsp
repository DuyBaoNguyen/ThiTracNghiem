<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
        crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/resultPage.css">


</head>

<body>
<div  class="print" id ="div-image">
	<button class="print-btn" onclick="printResult()">
		<i class="fas fa-print"></i>
		In
	</button>
	<a class="class-btn" href="${pageContext.request.contextPath}/DanhSachLopHocThiSinh">
		<i class="fas fa-chalkboard-teacher"></i>
		Lớp học
	</a>
	
	</div>
	<div class="header"></div>
	<div class="main-container">
		
		
		<div class="header1">
			<p class="title">${result.exam.name}</p>
			<div class="header1-item">
<%-- 				<p>${result.classEntry.name}</p> --%>
				<p>Mã số thí sinh: ${result.candidate.id}</p>
				<p>Họ tên: ${result.candidate.name}</p>
			</div>
			<div class="header1-item">
				<p>Kết quả: ${result.totalRightAnswers} / ${result.totalQuestion}</p>
				<p class="score">Điểm: ${result.score} / 10</p>
				<p>
					Thời gian nộp bài: 
					<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${result.time}"/>
				</p>
			</div>
		</div>


		<div class="question">
			<c:forEach items="${result.resultDetails}" var="rsd" varStatus="status">
				<hr>

				<div id="quest${rsd.question.id}" class="question-item">
					<p>Câu hỏi ${status.index+1}: ${rsd.question.content}</p>

					<input type="hidden" name="quest${rsd.question.id}" value="quest${rsd.question.id}">

					<c:forEach items="${rsd.question.answers}" var="an" varStatus="i">
						<label
							<c:if test="${rsd.status == true && rsd.userAnswer.id == an.id}">
								class="right-answer"
							</c:if>
							<c:if test="${rsd.question.rightAnswer.id == an.id}">
									style="color:red"
							</c:if>
						>
							<input readonly type="radio" name="answer${rsd.question.id}" value="${an.id}" onclick="javascript: return false;"
								<c:if test="${rsd.userAnswer.id == an.id}">
									checked
								</c:if>
							> ${an.content}
						</label>
					</c:forEach>
				</div>


			</c:forEach>

		</div>

	</div>

	<script type="text/javascript">
		function printResult() {
			var header = document.getElementsByClassName("header")[0];
			header.style.backgroundColor = "white";
			
			var content = document.getElementsByClassName("main-container")[0];
			content.style.borderRadius = "none";
			content.style.marginTop = "-300px";
			
			var printBtn = document.getElementsByClassName("print-btn")[0];
			printBtn.style.display = "none";
			
			window.print();
			
			header.style.backgroundColor = "#2979ff";
			content.style.borderRadius = "10px";
			content.style.marginTop = "-200px";
			printBtn.style.display = "block";
		}
	</script>

</body>

</html>