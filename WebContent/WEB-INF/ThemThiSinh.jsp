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
            <a class="menu-item" id="active-item" href="${pageContext.request.contextPath}/DanhSachThiSinh">
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
        <h3 class="title">Thêm thí sinh</h3>
    </div>
    <div class="main-container">
        <div class="main-content">
            <form action="${pageContext.request.contextPath}/ThemThiSinh" method="post" accept-charset="UTF-8">
                <h5 class="sub-title">Họ tên</h5>
                <input type="text" name="cddName" required autocomplete="off">
                <br>
                <h5 class="sub-title">Giới tính</h5>
                <label class="">
                    <input type="radio" name="cddSex" value="male" checked>
                    Nam
                </label>
                <label>
                    <input type="radio" name="cddSex" value="female">
                    Nữ
                </label>
                <br>
                <h5 class="sub-title">Địa chỉ</h5>
                <input type="text" name="cddAddress" autocomplete="off">
                <br>
                <h5 class="sub-title">Ngày sinh</h5>
                <input type="date" name="cddBirthday">
                <br>
                <h5 class="sub-title">Số điện thoại</h5>
                <input type="text" name="cddPhone" autocomplete="off" maxlength="10">
                <br>
                <input type="submit" class="save-btn func-btn" value="Lưu">
            </form>
        </div>
    </div>
</body>

</html>