<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thi trắc nghiệm</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table_not_link.css">

    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>

</head>

<body>
    <div class="left-menu-container">
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/images/user.png" alt="user" width="100" height="100">
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
            <a class="menu-item" id="active-item" href="${pageContext.request.contextPath}/DanhSachTaiKhoan">
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
            <a class="menu-item" href="${pageContext.request.contextPath}/DanhSachCauHoi">
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
            <a class="menu-item" href="${pageContext.request.contextPath}/DangXuat">
                <div class="icon-container">
                    <i class="fas fa-sign-out-alt"></i>
                </div>
                <span class="menu-item-content">Đăng xuất</span>
            </a>
        </div>
    </div>
    <div class="top-nav">
        <h3 class="title">Thêm tài khoản</h3>
    </div>
    <div class="main-container">
        <div class="func-container">
            <form action="${pageContext.request.contextPath}/ThemTaiKhoan" method="get">
                <div class="box-container">
                    <input class="box-text" type="text" name="search" placeholder="Tìm kiếm" autocomplete="off">
                    <button class="box-btn" type="submit">
                        <i class="fas fa-search" style="color: white"></i>
                    </button>
                </div>
            </form>
            <a href="${pageContext.request.contextPath}/ThemTaiKhoan" class="refresh-btn func-btn">Làm mới</a>
        </div>
        <hr>
        <div class="main-content">
        	<form id="account-info" action="${pageContext.request.contextPath}/ThemTaiKhoan" method="post" onsubmit="return isValidForm()">
        		<h5 class="sub-title">Username</h5>
                <input type="text" name="username" required readonly placeholder="Chọn người dùng trong bảng">
                <h5 class="sub-title">Mật khẩu</h5>
                <input type="password" name="password" required autocomplete="off">
                <br>
                <h5 class="sub-title">Xác nhận lại mật khẩu</h5>
                <input type="password" name="confirm-password" required autocomplete="off">
                <br>
                <input type="submit" class="save-btn func-btn" value="Lưu">
        	</form>
        	<hr>
            <h4 class="sub-title">Danh sách người dùng</h4>
            <table id="users" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Mã người dùng</th>
                        <th>Họ tên</th>
                        <th></th>

                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="user" items="${users}" varStatus="loop">
	                    <tr>
	                        <td id="userId${loop.index}">${user.id}</td>
	                        <td>${user.name}</td>
	                        <td>
	                            <button type="button" class="del-btn sub-func-btn" onclick="selectUser('${loop.index}')">Chọn</button>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

	<script>
		document.getElementById("account-info").onsubmit = function() {
		    return isValidForm();
		};
	</script>

    <script>
        function selectUser(index) {
        	var userId = document.getElementById("userId" + index).innerHTML;
        	document.getElementsByName("username")[0].value = userId;
        }
        
        function isValidForm() {
        	var username = document.getElementsByName("username")[0].value;
        	if (username == "") {
        		window.alert("Hãy chọn người dùng!");
        		return false;
        	}
        	
        	if (document.getElementsByName("password")[0].value != document.getElementsByName("confirm-password")[0].value) {
        		window.alert("Mật khẩu và xác nhận mật khẩu không trùng nhau!");
        		return false;
        	}
        	return true;
        }
    </script>
    
    <script>
        $(document).ready(function () {
            $('#users').DataTable();
        });
    </script>
</body>

</html>