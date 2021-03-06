<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <a class="menu-item" id="active-item" href="${pageContext.request.contextPath}/DanhSachNguoiDung">
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
            <a class="menu-item" href="#">
                <div class="icon-container">
                    <i class="fas fa-sign-out-alt"></i>
                </div>
                <span class="menu-item-content">Đăng xuất</span>
            </a>
        </div>
    </div>
    <div class="top-nav">
        <h3 class="title">Sửa người dùng</h3>
    </div>
    <div class="main-container">
        <div class="main-content">
            <form action="${pageContext.request.contextPath}/SuaNguoiDung" method="post" accept-charset="UTF-8">
                <h5 class="sub-title">Mã người dùng</h5>
                <input type="text" name="userId" value="${user.id}" readonly>
                <h5 class="sub-title">Họ tên</h5>
                <input type="text" name="userName" value="${user.name}" required autocomplete="off">
                <br>
                <h5 class="sub-title">Giới tính</h5>
                
                <label class="">
                    <input type="radio" name="userSex" value="male" checked
                    	<c:if test="${user.sex == true}">
                    		checked
                    	</c:if>>
                    Nam
                </label>
                <label>
                    <input type="radio" name="userSex" value="female"
                    	<c:if test="${user.sex == false}">
                    		checked
                    	</c:if>>
                    Nữ
                </label>
                <br>
                <h5 class="sub-title">Địa chỉ</h5>
                <input type="text" name="userAddress" value="${user.address}" autocomplete="off">
                <br>
                <h5 class="sub-title">Ngày sinh</h5>
                <input type="date" name="userBirthday" value="${user.birthday}">
                <br>
                <h5 class="sub-title">Số điện thoại</h5>
                <input type="text" name="userPhone" value="${user.phone}" autocomplete="off" maxlength="10">
                <br>
                <h5 class="sub-title">Vai trò</h5>
                <select name="roleId">
                 	<c:forEach var="role" items="${roles}">
	                    <option value="${role.id}"
	                    	<c:if test="${user.role.id == role.id}">
	                    		selected
	                    	</c:if>
	                    >${role.name}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="submit" class="save-btn func-btn" value="Lưu">
            </form>
        </div>
    </div>
</body>

</html>