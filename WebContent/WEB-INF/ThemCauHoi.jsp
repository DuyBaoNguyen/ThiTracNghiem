<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thi trắc nghiệm</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/other.css">
</head>

<body>
	<div class="left-menu-container">
		<div class="image-container">
			<img src="${pageContext.request.contextPath}/images/user.png"
				alt="user" width="100" height="100">
		</div>
		<div class="left-menu">
			<a class="menu-item" href="${pageContext.request.contextPath}/DanhSachLopHoc">
                <div class="icon-container">
                    <i class="fas fa-chalkboard-teacher"></i>
                </div>
                <span class="menu-item-content">Lớp học</span>
            </a>
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachLinhVuc">
                <div class="icon-container">
                   	<i class="fas fa-code-branch"></i>
                </div>
                <span class="menu-item-content">Lĩnh vực</span>
            </a>
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachThiSinh">
                <div class="icon-container">
                    <i class="fas fa-user"></i>
                </div>
                <span class="menu-item-content">Thí sinh</span>
            </a>
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachNguoiDung">
                <div class="icon-container">
                    <i class="fas fa-user"></i>
                </div>
                <span class="menu-item-content">Người dùng</span>
            </a>
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachTaiKhoan">
                <div class="icon-container">
                    <i class="fas fa-user-circle"></i>
                </div>
                <span class="menu-item-content">Tài khoản</span>
            </a>
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachDeThi">
                <div class="icon-container">
                   	<i class="fas fa-file"></i>
                </div>
                <span class="menu-item-content">Đề Thi</span>
            </a>
            <a class="menu-item" id="active-item" href="${pageContext.request.contextPath}/DanhSachCauHoi">
				<div class="icon-container">
					<i class="fas fa-question"></i>
				</div>
				<span class="menu-item-content">Câu hỏi</span>
			</a>
			<a class="menu-item" href="${pageContext.request.contextPath}/ThongTinNguoiDung">
				<div class="icon-container">
					<i class="fas fa-info"></i>
				</div>
				<span class="menu-item-content">Thông tin cá nhân</span>
			</a>
			<a class="menu-item" href="${pageContext.request.contextPath}/DoiMatKhauNguoiDung">
				<div class="icon-container">
					<i class="fas fa-key"></i>
				</div>
				<span class="menu-item-content">Đổi mật khẩu</span>
			</a>
			<hr>
			<a class="menu-item"
				href="${pageContext.request.contextPath}/DangXuat">
				<div class="icon-container">
					<i class="fas fa-sign-out-alt"></i>
				</div> <span class="menu-item-content">Đăng xuất</span>
			</a>
		</div>
	</div>
	<div class="top-nav">
		<h3 class="title">Thêm câu hỏi</h3>
	</div>
	<div class="main-container">
		<div class="main-content">
			<form id="question-info" action="${pageContext.request.contextPath}/ThemCauHoi"
				method="post" accept-charset="UTF-8" onsubmit="return isValidForm()">
				<h5 class="sub-title">Câu hỏi</h5>
				<textarea class="first-textarea" name="question" required
					autocomplete="off"></textarea>
				<hr>
				<button type="button" class="sub-func-btn select-right-answer"
					onclick="selectAnswer('answer1')">Chọn án đúng</button>
				<h5 class="sub-title">Đáp án 1</h5>
				<textarea name="answer1" required
					autocomplete="off"></textarea>
				<button type="button" class="sub-func-btn select-right-answer"
					onclick="selectAnswer('answer2')">Chọn án đúng</button>
				<h5 class="sub-title">Đáp án 2</h5>
				<textarea name="answer2" required autocomplete="off"></textarea>
				<button type="button" class="sub-func-btn select-right-answer"
					onclick="selectAnswer('answer3')">Chọn án đúng</button>
				<h5 class="sub-title">Đáp án 3</h5>
				<textarea name="answer3" required autocomplete="off"></textarea>
				<button type="button" class="sub-func-btn select-right-answer"
					onclick="selectAnswer('answer4')">Chọn án đúng</button>
				<h5 class="sub-title">Đáp án 4</h5>
				<textarea class="last-textarea" name="answer4" required
					autocomplete="off"></textarea>
				<hr>
				<h5 class="sub-title">Lĩnh vực</h5>
				<select name="fieldId">
					<c:forEach var="field" items="${fields}">
						<option value="${field.id}">${field.name}</option>
					</c:forEach>
				</select>
				<h5 class="sub-title">Loại câu hỏi</h5>
				<select name="typeQuestionId">
					<c:forEach var="typeQuestion" items="${typeQuestions}">
						<option value="${typeQuestion.id}">${typeQuestion.name}</option>
					</c:forEach>
				</select>
				<input type="hidden" name="right-answer">
				<input type="submit" class="save-btn func-btn" value="Lưu">
			</form>
		</div>
	</div>
	
	<script>
		document.getElementById("question-info").onsubmit = function() {
		    return isValidForm();
		};
	</script>
	
	<script>
		function isValidForm() {
			var answerlist = document.getElementsByTagName("textarea");
			for (var i = 0; i < answerlist.length; i++) {
				if (answerlist[i].value.trim() == "") {
					window.alert("Hãy nội dung cho đáp án!");
					answerlist[i].focus();
					return false;
				}
			}
			
			var right_answer = document.getElementsByName("right-answer")[0].value;
			if (right_answer == "") {
				window.alert("Hãy chọn đáp án đúng!");
				return false;
			}
			return true;
		}
	</script>

	<script>
		function selectAnswer(answer) {
			var right_answer = document.getElementsByName("right-answer")[0];
			var textarea = document.getElementsByName(answer)[0];
			if (textarea.value.trim() != "") {
				right_answer.value = textarea.value;
	
				var testlist = document.getElementsByTagName("textarea");
				var i;
				for (i = 0; i < testlist.length; i++) {
					testlist[i].id = "";
				}
				textarea.id = "right-answer-id";
			}
		}
	</script>
</body>

</html>