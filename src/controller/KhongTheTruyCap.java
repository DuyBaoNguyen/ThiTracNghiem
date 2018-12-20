package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KhongTheTruyCap")
public class KhongTheTruyCap extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public KhongTheTruyCap() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/KhongTheTruyCap.jsp").forward(request, response);
	}
}
