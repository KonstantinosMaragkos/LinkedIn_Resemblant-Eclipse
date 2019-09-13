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
import dao.FriendsDAO;
import dao.FriendsDAOImpl;
import dao.InterestDAO;
import dao.InterestDAOImpl;
import model.Interest;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
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
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		//prepare friend requests
		UserDAO udao = new UserDAOImpl();
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		List<User> out = udao.list();
		List<Integer> ids = new ArrayList<Integer>();
		out.removeAll(out);
		for(Friend fr: flist) {
			fdao.refresh(fr);
			if(fr.getUser_id() == u_id && fr.getStatus() == 2) {
				ids.add(fr.getFriend_id());
			}
		}
		
		for(int i=0; i<ids.size(); i++) {
			User u = udao.find(ids.get(i));
			out.add(u);
		}
		String json = new Gson().toJson(out);
		request.setAttribute("flist", json);
		request.setAttribute("flist_count", out.size());
		
		//prepare articles of user
		ArthroDAO adao = new ArthroDAOImpl();
		List<Arthro> alist = adao.list();
		List<Arthro> arthra = adao.list(); arthra.removeAll(arthra);
		Collections.reverse(alist);
		
		for(Arthro ar: alist) {
			if(ar.getUser_id() == u_id) {
				arthra.add(ar);
			}
		}
		//find likes
		InterestDAO indao = new InterestDAOImpl();
		List<Interest> inlist = indao.list();
		List<Integer> likes = new ArrayList<Integer>();
		out.removeAll(out); 	//friends who liked
		
		int index=0;
		List<String> photos = new ArrayList<String>();
		for(Arthro ar: arthra) {
			likes.add(0);
			for(Interest in: inlist) {
				if(in.get_idArthro() == ar.getIdArthro()) {
					User temp = new User();
					temp = udao.find(in.getUser_id());
					out.add(temp);
					likes.set(index, likes.get(index)+1);
					photos.add("C:/media/user" + temp.getUser_id() + "/" + temp.getPhoto());
				}
			}
			index++;
		}
		String json2 = new Gson().toJson(likes);
		String json3 = new Gson().toJson(out);
		String json4 = new Gson().toJson(arthra);
		String json5 = new Gson().toJson(photos);
		request.setAttribute("like_counts", json2);
		request.setAttribute("friends_like", json3);
		request.setAttribute("arthra", json4);
		request.setAttribute("fr_photos", json5);
		
		User user = new User();
		user = udao.find(u_id);
		String photo = "C:/media/user" + u_id + "/" + user.getPhoto();
		String json6 = new Gson().toJson(user);
		request.setAttribute("user", json6);
		request.setAttribute("photo", photo);
		
		request.getRequestDispatcher("notif_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
