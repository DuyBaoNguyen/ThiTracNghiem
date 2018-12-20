package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.AccountEntry;

@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String role_thi_sinh = "Thí sinh";
	static final String role_nguoi_ra_de = "Người ra đề thi";
	static final String role_nguoi_quan_li_cau_hoi = "Người quản lý ngân hàng câu hỏi";
	static final String role_nguoi_quan_li_nguoi_dung = "Người quản lý người dùng, tài khoản và lớp học";

	public DangNhap() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountEntry account = (AccountEntry) request.getSession().getAttribute("account");
		if (account == null) {
			Boolean loginSuccess = (Boolean)getServletContext().getAttribute("loginSuccess");
			if (loginSuccess != null) {
				getServletContext().removeAttribute("loginSuccess");
			}
			else {
				loginSuccess = true;
			}
			request.setAttribute("loginFail", !loginSuccess);
			
			request.getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(request, response);
		} else {
			switch (account.getUser().getRole().getName()) {
			case role_thi_sinh:
				response.sendRedirect(request.getContextPath() + "/DanhSachLopHocThiSinh");
				break;
			case role_nguoi_ra_de:
				response.sendRedirect(request.getContextPath() + "/DanhSachDeThi");
				break;
			case role_nguoi_quan_li_cau_hoi:
				response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc");
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		AccountEntry account = new AccountEntry(user, pass);

		HttpSession session = request.getSession();
		boolean loginSuccess = AccountDAO.checkAccount(account);
		if (loginSuccess) {
			session.setAttribute("account", account);
			switch (account.getUser().getRole().getName()) {
			case role_thi_sinh:
				response.sendRedirect(request.getContextPath() + "/DanhSachLopHocThiSinh");
				break;
			case role_nguoi_ra_de:
				response.sendRedirect(request.getContextPath() + "/DanhSachDeThi");
				break;
			case role_nguoi_quan_li_cau_hoi:
				response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc");
			}
		} else {
			getServletContext().setAttribute("loginSuccess", loginSuccess);
			doGet(request, response);
		}
	}
}
