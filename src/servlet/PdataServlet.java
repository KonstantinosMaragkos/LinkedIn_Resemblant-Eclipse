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
import model.PersonalData;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class PdataServlet
 */
@WebServlet("/PdataServlet")
public class PdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdataServlet() {
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
		int user_id = 0;
		try {
			user_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		//session.setAttribute("opened_pdata", "yes");
		
		String strUni = request.getParameter("university");
		String strDep = request.getParameter("department");
		String strFrm = request.getParameter("from");
		String strTo = request.getParameter("to");
		String strEdu = request.getParameter("education");
		String strTitle = request.getParameter("title");
		String strComp = request.getParameter("company");
		String strLoc = request.getParameter("location");
		String strStat = "0";
		
		UserDAO udao = new UserDAOImpl();
		//find last row created - update there
		List<User> ulist = udao.list();
		User u = new User();
		for(int i=0; i<ulist.size(); i++) {
			u = ulist.get(i);
		}
		int x = u.getUser_id();
		String strId=String.valueOf(x);
		
		PDataDAO pdao = new PDataDAOImpl();
		pdao.updateNew(strId, strUni, strDep, strFrm, strTo, strEdu, strTitle, strComp, strLoc, strStat);
		
		response.sendRedirect("HomepageServlet");
	}

}
