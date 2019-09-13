package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.AdminUser;
import model.User;
import com.google.gson.*;

/**
 * Servlet implementation class errikosServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		int id = 0;
		try {
			id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		String strName = request.getParameter("fname");
		
		//get current user, and get the results from table excluding him - and admin
		User user = new User();
		UserDAO udao = new UserDAOImpl();
		user = udao.find(id);
		
		List<User> ulist = udao.search_UserName(strName, user.getFirst_Name(), user.getLast_Name());
		List<User> out = new ArrayList<User>();
		
		AdminDAO adao = new AdminDAOImpl();
		AdminUser admin = new AdminUser();
		for(User u: ulist) {
			admin = adao.find(u.getUser_id());
			if(admin == null) {
				out.add(u);
			}
		}
		
		String json = new Gson().toJson(out);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

}
