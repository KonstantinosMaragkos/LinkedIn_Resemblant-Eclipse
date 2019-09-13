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

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsServlet() {
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
		int x = 0;
		try {
			x = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		String strNewEmail = request.getParameter("new_email");
		String strNewPass = request.getParameter("new_pass");
		String strNewPass2 = request.getParameter("new_pass2");
		
		String strUserId = String.valueOf(x);
		session.setAttribute("input", "yes");
		session.setAttribute("valid", "yes");
		session.setAttribute("updated", "no");
		//session.setAttribute("exist", "yes");
		
		if(strNewEmail=="" || strNewPass=="" || strNewPass2=="") {
			//blank input
			session.setAttribute("input", "no");
			
			response.sendRedirect("settings_page.jsp");
		}
		else if(!strNewPass.equals(strNewPass2)) {
			//not valid
			session.setAttribute("valid", "no");
			
			response.sendRedirect("settings_page.jsp");
		}
		else {
			UserDAO udao = new UserDAOImpl();
			
			/*List<User> ulist = udao.findEmail(strNewEmail);
			if(ulist.size() != 0) {
				session.setAttribute("exist", "no");
				
				session.setAttribute("input", "yes");
				session.setAttribute("valid", "yes");
				response.sendRedirect("settings_page.jsp");
			}
			else {*/
			String strHash = getHash(strNewPass);
			if(strHash != null) {
				strNewPass = strHash;
			}
			udao.update(strUserId, strNewEmail, strNewPass);
				
			session.setAttribute("updated", "yes");
			response.sendRedirect("settings_page.jsp");
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
