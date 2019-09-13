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
import model.Friend;
import dao.FriendsDAO;
import dao.FriendsDAOImpl;

/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String strFId = request.getParameter("f_id");
		int f_id = Integer.parseInt(strFId);
		HttpSession session = request.getSession();
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		for(Friend curr: flist) {
			if(curr.getUser_id() == u_id && curr.getFriend_id() == f_id) {
				while(fdao.remove(curr) == 1) { ; }
			}
			else if(curr.getUser_id() == f_id && curr.getFriend_id() == u_id) {
				while(fdao.remove(curr) == 1) { ; }
			}
		}
		//response.sendRedirect("FriendServlet?f_id=" + f_id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String f_id = request.getParameter("f_id");
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		Friend f = new Friend();
		User u1 = new User();
		User u2 = new User();
		UserDAO udao = new UserDAOImpl();
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		int x = getId();
		
		u1 = udao.find(u_id);
		u2 = udao.find(Integer.valueOf(f_id));
		f.setUser_id(u1.getUser_id());		f.setFriend_id(u2.getUser_id());
		f.setUser1(u1);
		f.setUser2(u2);
		f.setRelationship_id(x);
		f.setStatus(1);
		while(fdao.create(f) == 1) {
			;
		}
		
		Friend f2 = new Friend();
		f2.setUser_id(u2.getUser_id());		f2.setFriend_id(u1.getUser_id());
		f2.setUser1(u2);
		f2.setUser2(u1);
		//f2.setRelationship_id(x+2);
		x = getId();
		f2.setRelationship_id(x);
		f2.setStatus(2);
		while(fdao.create(f2) == 1) {
			;
		}
		
		//?
		fdao.refresh(f);
		fdao.refresh(f2);
		
		//response.sendRedirect("FriendServlet?f_id=" + f2.getUser_id());
	}

	private int getId() {
		int x=1;
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		while(true) {
			int flag=0;
			for(int i=0; i<flist.size(); i++) {
				if(x == flist.get(i).getRelationship_id()) {
					flag=1;
					break;
				}
			}
			if(flag == 1) {
				x++;
			}
			else {
				return x;
			}
		}
	}
}
