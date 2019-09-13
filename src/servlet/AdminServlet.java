package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminUser;
import model.User;
import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import com.google.gson.*;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UserDAO udao = new UserDAOImpl();
		List<User> ulist = udao.list();
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
		request.setAttribute("users", json);
		request.getRequestDispatcher("admin_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
