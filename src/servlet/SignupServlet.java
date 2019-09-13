package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAOImpl();
		String strUser = request.getParameter("user");
		HttpSession session = request.getSession(false);		//false????
		session.setAttribute("exist", "ok");
		session.setAttribute("input_f", "ok");
		int found = 0;
		
		List<User> ulist = dao.list();
		for (User user: ulist) {
			if (user.getE_Mail().equals(strUser)) {
				found = 1;
				break;
			}
		}
		if (found == 1) {
			session.setAttribute("exist", "no");
			response.sendRedirect("welcome_page.jsp");
		} else if(strUser == ""){
			session.setAttribute("input_f", "no");
			response.sendRedirect("welcome_page.jsp");
		}
		else {
			session.setAttribute("email", strUser);
			response.sendRedirect("signup_page.jsp");
		}
	}

}
