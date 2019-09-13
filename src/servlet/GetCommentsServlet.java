package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import model.Arthro;
import model.Friend;
import dao.ArthroDAO;
import dao.ArthroDAOImpl;
import dao.SxolioArthroDAO;
import dao.SxolioArthroDAOImpl;
import model.SxolioArthro;

/**
 * Servlet implementation class GetCommentsServlet
 */
@WebServlet("/GetCommentsServlet")
public class GetCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCommentsServlet() {
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
		int user_id = 0;
		try {
			user_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		String strArtro_id = request.getParameter("arthro_id");
		int arthro_id = Integer.parseInt(strArtro_id);
		
		UserDAO udao = new UserDAOImpl();
		ArthroDAO adao = new ArthroDAOImpl();
		SxolioArthroDAO  sadao = new SxolioArthroDAOImpl();
		
		List<SxolioArthro> salist = sadao.list();
		List<SxolioArthro> sxolia = sadao.list(); sxolia.removeAll(sxolia);
		List<User> users = udao.list();
		users.removeAll(users);
		
		List<String> photos = new ArrayList<String>();
		
		//find sxolia of (arthro_id)
		for(SxolioArthro sa: salist) {
			if(sa.get_idArthro() == arthro_id) {
				sxolia.add(sa);
				User u = udao.find(sa.getUser_id());
				users.add(u);
				photos.add("C:/media/user" + u.getUser_id() + "/" + u.getPhoto());
			}
		}
		
		List<String> list  = new ArrayList<>();
		
		String json = new Gson().toJson(sxolia);
		String json2 = new Gson().toJson(users);
		String json3 = new Gson().toJson(photos);
		
		list .add(json);
		list .add(json2);
		list .add(json3);
		String out = new Gson().toJson(list);
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    //response.getWriter().write(json + ".." + json2 + ".." + json3);
	    //response.getWriter().write(out);
	    response.getWriter().write(json + " " + json2 + " " + json3);
	}

}
