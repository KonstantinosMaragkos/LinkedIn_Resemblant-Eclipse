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

/**
 * Servlet implementation class errikosServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		int pd_id = (int) session.getAttribute("pd_id");
		
		PDataDAO pdao = new PDataDAOImpl();
		PersonalData pd = new PersonalData();
		pd = pdao.find(pd_id);
		pdao.refresh(pd);
		
		//education
		request.setAttribute("uni", pd.getUniversity());
		request.setAttribute("dep", pd.getDepartment());
		request.setAttribute("frm", pd.getYearFrom());
		request.setAttribute("to", pd.getYearTo());
		request.setAttribute("edu", pd.getEducation());
		
		//profession
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
		
		UserDAO udao = new UserDAOImpl();
		User u = new User();
		u = udao.find(u_id);
		request.setAttribute("f_name", u.getFirst_Name());
		request.setAttribute("l_name", u.getLast_Name());
		request.setAttribute("phone", u.getPhone());
		
		//get photo path
		String path = "C:/media/user" + u_id + "/" + u.getPhoto();
		request.setAttribute("photo", path);
		
		request.getRequestDispatcher("profile_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
