package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PDataDAO;
import dao.PDataDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.PersonalData;
import model.User;
import dao.PDStatusDAO;
import dao.PDStatusDAOImpl;
import model.PdStatus;
import model.AdminUser;
import model.Friend;
import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.FriendsDAO;
import dao.FriendsDAOImpl;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
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
		//get the friend(user) id
		String strUId = request.getParameter("f_id");
		int f_id = Integer.valueOf(strUId);
		
		//find this user and set attributes
		UserDAO udao = new UserDAOImpl();
		User u = new User();
		u = udao.find(Integer.valueOf(strUId));
		request.setAttribute("f_name", u.getFirst_Name());
		request.setAttribute("l_name", u.getLast_Name());
		request.setAttribute("phone", u.getPhone());
		request.setAttribute("email", u.getE_Mail());
		
		//find matching pd entry, set atts
		PDataDAO pdao = new PDataDAOImpl();
		PersonalData pd = new PersonalData();
		pd = pdao.find(u.getPersonal_Data_id());
		request.setAttribute("uni", pd.getUniversity());
		request.setAttribute("dep", pd.getDepartment());
		request.setAttribute("frm", pd.getYearFrom());
		request.setAttribute("to", pd.getYearTo());
		request.setAttribute("edu", pd.getEducation());
		if(pd.getTitle() == null) {
			request.setAttribute("title", "None");
		}
		else {
			request.setAttribute("title", pd.getTitle());
		}
		if(pd.getCompany() == null) {
			request.setAttribute("comp", "None");
		}
		else {
			request.setAttribute("comp", pd.getCompany());
		}
		if(pd.getLocation() == null) {
			request.setAttribute("loc", "None");
		}
		else {
			request.setAttribute("loc", pd.getLocation());
		}
		
		PdStatus pds = new PdStatus();
		PDStatusDAO status_dao = new PDStatusDAOImpl();
		pds = status_dao.find(Integer.valueOf(strUId));
		status_dao.refresh(pds);
		request.setAttribute("pds", pds);
		
		//find status
		FriendsDAO fdao = new FriendsDAOImpl();
		List<Friend> flist = fdao.list();
		int flag=0;
		
		for(Friend fr: flist) {
			fdao.refresh(fr);
			if(fr.getUser_id() == user_id && fr.getFriend_id() == f_id) {
				request.setAttribute("status", fr.getStatus());
				flag = 1;
			}
		}
		if(flag == 0) {
			request.setAttribute("status", -1);
		}
		
		//check if admin is visiting this page - "make all public"
		AdminDAO adao = new AdminDAOImpl();
		AdminUser admin = new AdminUser();
		admin = adao.find(user_id);
		request.setAttribute("isAdmin", "0");
		if(admin != null) {
			request.setAttribute("isAdmin", "1");
		}
		
		
		request.getRequestDispatcher("friend_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
