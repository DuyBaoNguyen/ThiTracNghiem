<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	
	<title>Đăng nhập</title>
	
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" action="${pageContext.request.contextPath}/DangNhap" method="POST">
					<span class="login100-form-title p-b-33">
						Đăng nhập tài khoản
					</span>

					<div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
						<input class="input100" type="text" name="user" placeholder="User">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="pass" placeholder="Password">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" type="submit">
							Đăng nhập
						</button>
					</div>

					<div class="text-center p-t-45 p-b-4">
						<span class="txt1">
							Quên
						</span>

						<a href="#" class="txt2 hov1">
							Username / Password?
						</a>
					</div>

					<div class="text-center">
						<span class="txt1">
							Tạo tài khoản?
						</span>

						<a href="#" class="txt2 hov1">
							Đăng kí
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<c:if test="${loginFail == true}">
	    <script>
	        $(document).ready(function () {
	            setTimeout(function () {
	                window.alert("Tên đăng nhập hoặc mật khẩu không chính xác");
	            }, 100);
	        });
	    </script>
    </c:if>
</body>
</html>