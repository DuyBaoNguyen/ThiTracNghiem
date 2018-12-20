package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KiemTraVaiTro {
	static final String role_thi_sinh = "Thí sinh";
	static final String role_nguoi_ra_de = "Người ra đề thi";
	static final String role_nguoi_quan_li_cau_hoi = "Người quản lý ngân hàng câu hỏi";
	static final String role_nguoi_quan_li_nguoi_dung = "Người quản lý người dùng, tài khoản và lớp học";
	static final Map<String, List<String>> mapRole = new HashMap<String, List<String>>();
	
	static {
		init();
	}
	
	private static void init() {
		List<String> urlPatterns1 = new ArrayList<String>();
		urlPatterns1.add("/DanhSachLinhVuc");
		urlPatterns1.add("/DanhSachLopHoc");
		urlPatterns1.add("/DanhSachNguoiDung");
		urlPatterns1.add("/DanhSachTaiKhoan");
		urlPatterns1.add("/DanhSachThiSinh");
		urlPatterns1.add("/DoiMatKhau");
		urlPatterns1.add("/DanhSachLopHoc/LopHoc");
		urlPatterns1.add("/SuaLinhVuc");
		urlPatterns1.add("/SuaLopHoc");
		urlPatterns1.add("/SuaNguoiDung");
		urlPatterns1.add("/SuaThiSinh");
		urlPatterns1.add("/ThemLinhVuc");
		urlPatterns1.add("/ThemLopHoc");
		urlPatterns1.add("/ThemNguoiDung");
		urlPatterns1.add("/ThemTaiKhoan");
		urlPatterns1.add("/ThemThiSinh");
		urlPatterns1.add("/ThemThiSinhVaoLop");
		urlPatterns1.add("/XoaLinhVuc");
		urlPatterns1.add("/XoaLopHoc");
		urlPatterns1.add("/XoaNguoiDung");
		urlPatterns1.add("/XoaTaiKhoan");
		urlPatterns1.add("/XoaThiSinh");
		urlPatterns1.add("/XoaThiSinhTrongLop");
		urlPatterns1.add("/ThongTinNguoiDung");
		urlPatterns1.add("/DoiMatKhauNguoiDung");
		urlPatterns1.add("/DangXuat");
		
		mapRole.put(role_nguoi_quan_li_nguoi_dung, urlPatterns1);
		
		List<String> urlPatterns2 = new ArrayList<String>();
		urlPatterns2.add("/DanhSachDeThi");
		urlPatterns2.add("/ThemDeThi");
		urlPatterns2.add("/DanhSachDeThi/DeThi");
		urlPatterns2.add("/SuaDeThi");
		urlPatterns2.add("/XoaDeThi");
		urlPatterns2.add("/ThemDeThi");
		urlPatterns2.add("/GanDeThiChoLop");
		urlPatterns2.add("/BoGanDeThiChoLop");
		urlPatterns2.add("/ThongTinNguoiDung");
		urlPatterns2.add("/DoiMatKhauNguoiDung");
		urlPatterns2.add("/DangXuat");
		
		mapRole.put(role_nguoi_ra_de, urlPatterns2);
		
		List<String> urlPatterns3 = new ArrayList<String>();
		urlPatterns3.add("/DanhSachCauHoi");
		urlPatterns3.add("/XoaCauHoi");
		urlPatterns3.add("/ThemCauHoi");
		urlPatterns3.add("/DanhSachCauHoi/CauHoi");
		urlPatterns3.add("/UploadFile");
		urlPatterns3.add("/ThongTinNguoiDung");
		urlPatterns3.add("/DoiMatKhauNguoiDung");
		urlPatterns3.add("/DangXuat");
		
		mapRole.put(role_nguoi_quan_li_cau_hoi, urlPatterns3);
		
		List<String> urlPatterns4 = new ArrayList<String>();
		urlPatterns4.add("/DanhSachLopHocThiSinh");
		urlPatterns4.add("/DanhSachLopHocThiSinh/LopHoc");
		urlPatterns4.add("/KetQua");
		urlPatterns4.add("/Thi");
		urlPatterns4.add("/ThongTinThiSinh");
		urlPatterns4.add("/DoiMatKhauThiSinh");
		urlPatterns4.add("/DangXuat");
		
		mapRole.put(role_thi_sinh, urlPatterns4);
	}
	
	public static boolean kiemTraVaiTro(String roleName, String url) {
		boolean access = false;
		Set<String> roles = mapRole.keySet();
		for (String role : roles) {
			if (role.equals(roleName)) {
				List<String> urls = mapRole.get(role);
				if (urls.contains(url)) {
					access = true;
					break;
				}
				break;
			}
		}
		return access;
	}
}
