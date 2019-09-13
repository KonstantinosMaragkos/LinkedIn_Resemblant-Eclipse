package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import model.Friend;
import dao.FriendsDAO;
import dao.FriendsDAOImpl;

/**
 * Servlet implementation class ManageRequestServlet
 */
@WebServlet("/ManageRequestServlet")
public class ManageRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageRequestServlet() {
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
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		String strUId = request.getParameter("f_id");
		int f_id = Integer.valueOf(strUId);
		String strFlag = request.getParameter("flag");
		
		UserDAO udao = new UserDAOImpl();
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		if(strFlag.equals("0")) {
			//accept
			while(fdao.update(u_id, f_id, 0) == 1) { ; }
			
			while(fdao.update(f_id, u_id, 0) == 1) { ; }
		}
		else {
			for(Friend curr: flist) {
				if(curr.getUser_id() == u_id && curr.getFriend_id() == f_id) {
					while(fdao.remove(curr) == 1) { ; }
				}
				else if(curr.getUser_id() == f_id && curr.getFriend_id() == u_id) {
					while(fdao.remove(curr) == 1) { ; }
				}
			}
		}
	}

}
