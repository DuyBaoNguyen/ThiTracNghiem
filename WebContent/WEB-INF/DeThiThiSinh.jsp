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
            <a class="menu-item" id="active-item" href="${pageContext.request.contextPath}/DanhSachLopHocThiSinh">
				<div class="icon-container">
					<i class="fas fa-chalkboard-teacher"></i>
				</div>
				<span class="menu-item-content">Danh sách lớp học</span>
			</a>
			<a class="menu-item" href="${pageContext.request.contextPath}/ThongTinThiSinh">
				<div class="icon-container">
					<i class="fas fa-info"></i>
				</div>
				<span class="menu-item-content">Thông tin cá nhân</span>
			</a>
			<a class="menu-item" href="${pageContext.request.contextPath}/DoiMatKhauThiSinh">
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
        <h3 class="title">Lớp ${classEntry.name}</h3>
    </div>
    <div class="main-container">
        <div class="main-content">
        	<h5>Đề thi chưa thi</h5>
            <table id="exams1" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Tên đề thi</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="exam" items="${exams}">
	                    <tr>
	                        <td>${exam.name}</td>
	                        <td>
	                            <a href="${pageContext.request.contextPath}/Thi?classId=${classEntry.id}&examId=${exam.id}" class="update-btn sub-func-btn">Vào thi</a>
	                        </td>
	                    </tr>
	               	</c:forEach>
                </tbody>
            </table>
            <hr>
            <h5>Đề thi đã thi</h5>
            <table id="exams2" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Tên đề thi</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="exam" items="${takenExams}">
	                    <tr>
	                        <td>${exam.name}</td>
	                        <td>
	                            <a href="${pageContext.request.contextPath}/KetQua?examId=${exam.id}" class="update-btn sub-func-btn">Xem kết quả</a>
	                        </td>
	                    </tr>
	               	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $('#exams1').DataTable();
        });
    </script>
    
    <script>
        $(document).ready(function () {
            $('#exams2').DataTable();
        });
    </script>
</body>

</html>