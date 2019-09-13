package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import dao.PDataDAO;
import dao.PDataDAOImpl;
import model.PersonalData;
import dao.AdminDAO;
import dao.AdminDAOImpl;
import model.AdminUser;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String strPass = request.getParameter("pass");
		HttpSession session = request.getSession();
		session.setAttribute("input", "ok");
		session.setAttribute("valid", "ok");
		
		if (strUser == "" || strPass == "") {
			//activate session variable for no input given
			session.setAttribute("input", "no");
			response.sendRedirect("welcome_page.jsp");
		}else {
			String strHash = getHash(strPass);
			if(strHash != null) {
				strPass = strHash;
			}
			List<User> user = dao.logIn(strUser, strPass);
			if (user.size() != 0) {
				
				//user exists, check if finished personal data page
				User u = user.get(0);
				int pd_id = u.getPersonal_Data_id();
				PersonalData pd = new PersonalData();
				PDataDAO pdao = new PDataDAOImpl();
				pd = pdao.find(pd_id);
				
				//set all attributes
				session.setAttribute("username", u.getFirst_Name());				
				session.setAttribute("email", strUser);
				session.setAttribute("pd_id", u.getPersonal_Data_id());
				session.setAttribute("user_id", u.getUser_id());
				
				//check if admin
				AdminDAO adao = new AdminDAOImpl();
				AdminUser admin = new AdminUser();
				admin = adao.find(u.getUser_id());
				if(admin != null) {
					response.sendRedirect("AdminServlet");
					return;
				}
				//continue
				
				if(pd.getFinished_status() == 0) {
					// all good
					response.sendRedirect("HomepageServlet");
				}
				else {
					//go finish data input
					response.sendRedirect("personal_data.jsp");
				}
			}else {
				//activate session variable for no valid user
				session.setAttribute("valid", "no");
				response.sendRedirect("welcome_page.jsp");
			}
		}
	}
	
	private String getHash(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			return myHash;
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
