<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css">

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
        <h3 class="title">Danh sách thí sinh</h3>
    </div>
    <div class="main-container">
        <div class="func-container">
            <form action="${pageContext.request.contextPath}/DanhSachThiSinh" method="get">
                <div class="box-container">
                    <input class="box-text" type="text" name="search" placeholder="Tìm kiếm" autocomplete="off">
                    <button class="box-btn" type="button">
                        <i class="fas fa-search" style="color: white"></i>
                    </button>
                </div>
            </form>
            <a href="${pageContext.request.contextPath}/ThemThiSinh" class="add-btn func-btn">Thêm</a>
            <a href="${pageContext.request.contextPath}/DanhSachThiSinh" class="refresh-btn func-btn">Làm mới</a>
        </div>
        <hr>
        <div class="main-content">
            <table id="candidates" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Mã thí sinh</th>
                        <th>Họ tên</th>
                        <th>Nữ</th>
                        <th>Địa chỉ</th>
                        <th>Ngày sinh</th>
                        <th>Số điện thoại</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="cdd" items="${cdds}">
	                    <tr>
	                        <td>${cdd.id}</td>
	                        <td>${cdd.name}</td>
	                        <td class="align-item">
		                        <input type="checkbox" name="cddSex" readonly 
		                        	<c:if test="${cdd.sex == false}">
		                        		checked
		                        	</c:if>
		                        >
	                        </td>
	                        <td>${cdd.address}</td>
	                        <td>
	                        	<fmt:formatDate pattern="dd-MM-yyyy" value="${cdd.birthday}"/>
	                        </td>
	                        <td>${cdd.phone}</td>
	                        <td>
	                            <button type="button" class="del-btn sub-func-btn" onclick="delCandidate('${cdd.id}')">Xóa</button>
	                            <a href="${pageContext.request.contextPath}/SuaThiSinh?cddId=${cdd.id}" class="update-btn sub-func-btn">Sửa</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <script>
        function delCandidate(cddId) {
            if (window.confirm("Bạn chắc chắn muốn xóa?")) {
                window.location = "${pageContext.request.contextPath}/XoaThiSinh?cddId=" + cddId;
            }
        }
    </script>

	<c:if test="${cddError == true}">
	    <script>
	        $(document).ready(function () {
	            setTimeout(function () {
	                window.alert("Đã có lỗi xảy ra");
	            }, 100);
	        });
	    </script>
	</c:if>
	
    <script>
        $(document).ready(function () {
            $('#candidates').DataTable();
        });
    </script>
</body>

</html>