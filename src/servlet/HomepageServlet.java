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
import dao.ArthroDAO;
import dao.ArthroDAOImpl;
import model.Arthro;
import dao.InterestDAO;
import dao.InterestDAOImpl;
import model.Interest;
import com.google.gson.*;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageServlet() {
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
		User u = new User();
		u = udao.find(user_id);
		
		request.setAttribute("user", u);
		String path = "C:/media/user" + user_id + "/" + u.getPhoto();
		
		request.setAttribute("photo", path);
		
		//select 3-max random friends with photos for profile overview
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
			if(i == 3) {
				break;
			}
			User us = udao.find(ids.get(i));
			out.add(us);
			photos[i] = "C:/media/user" + ids.get(i) + "/" + us.getPhoto();
		}
		
		String json = new Gson().toJson(out);
		String json2 = new Gson().toJson(photos);
		request.setAttribute("flist", json);
		request.setAttribute("photos", json2);
		
		//get all users articles - with likes
		ArthroDAO adao = new ArthroDAOImpl();
		List<Arthro> alist = adao.list();
		List<Arthro> arthra = adao.list(); arthra.removeAll(arthra);
		out.removeAll(out);
		
		InterestDAO indao = new InterestDAOImpl();
		List<Interest> inlist = indao.list();
		List<Interest> liked = indao.list(); liked.removeAll(liked);
		
		int i=0;
		String[] photos2 = new String[alist.size()];
		Collections.reverse(alist);
		for(Arthro ar: alist) {
			if(ar.getUser_id() == user_id || searchArray(ar.getUser_id(), ids)) {
				User us = udao.find(ar.getUser_id());
				out.add(us);
				arthra.add(ar);
				photos2[i] = "C:/media/user" + ar.getUser_id() + "/" + us.getPhoto();
				
				//add to liked list if liked
				for(Interest in: inlist) {
					indao.refresh(in);
					if(in.get_idArthro()==ar.getIdArthro() && in.getUser_id()==user_id) {
						liked.add(in);
					}
				}
				i++;
			}
		}
		String json3 = new Gson().toJson(arthra);
		String json4 = new Gson().toJson(out);
		String json5 = new Gson().toJson(photos2);
		String json6 = new Gson().toJson(liked);
		
		request.setAttribute("article", json3);
		request.setAttribute("article_friend", json4);
		request.setAttribute("article_photos", json5);
		request.setAttribute("art_count", arthra.size());
		request.setAttribute("liked",json6);
		
		request.getRequestDispatcher("home_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean searchArray(int id, List<Integer> ids) {
		for(int i=0; i<ids.size(); i++) {
			if(id == ids.get(i)) {
				return true;
			}
		}
		return false;
	}
}
