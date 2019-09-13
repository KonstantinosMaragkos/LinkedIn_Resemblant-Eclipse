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
import com.google.gson.*;

/**
 * Servlet implementation class NetworkServlet
 */
@WebServlet("/NetworkServlet")
public class NetworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NetworkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		int user_id = 0;
		try {
			user_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		UserDAO udao = new UserDAOImpl();
		
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		List<User> out = udao.list();
		out.removeAll(out);
		List<Integer> ids = new ArrayList<Integer>();
		for(Friend fr: flist) {
			fdao.refresh(fr);
			if(fr.getUser_id() == user_id && fr.getStatus() == 0) {
				ids.add(fr.getFriend_id());
			}
		}
		
		String[] photos = new String[ids.size()];
		for(int i=0; i<ids.size(); i++) {
			User u = udao.find(ids.get(i));
			out.add(u);
			photos[i] = "C:/media/user" + ids.get(i) + "/" + u.getPhoto();
		}
		
		String json = new Gson().toJson(out);
		String json2 = new Gson().toJson(photos);
		request.setAttribute("flist", json);
		request.setAttribute("photos", json2);
		request.getRequestDispatcher("network_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
