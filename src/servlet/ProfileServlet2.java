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
 * Servlet implementation class ProfileServlet2
 */
@WebServlet("/ProfileServlet2")
public class ProfileServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet2() {
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
		int pd_id = 0;
		try {
			pd_id = (int) session.getAttribute("pd_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		String str_uni = request.getParameter("university");
		String str_dep = request.getParameter("department");
		String str_frm = request.getParameter("from");
		String str_to = request.getParameter("to");
		String str_edu = request.getParameter("education");
		String str_title = request.getParameter("title");
		String str_comp = request.getParameter("company");
		String str_loc = request.getParameter("location");
		
		PDataDAO pdao = new PDataDAOImpl();
		PersonalData pd = new PersonalData();
		pd = pdao.find(pd_id);
		
		//fix all fields
		if(str_uni == "" || str_uni == null) {
			str_uni = pd.getUniversity();
		}
		if(str_dep == "" || str_dep == null) {
			str_dep = pd.getDepartment();
		}
		if(str_frm == "" || str_frm == null) {
			str_frm = String.valueOf(pd.getYearFrom());
		}
		if(str_to == "" || str_to == null) {
			str_to = String.valueOf(pd.getYearTo());
		}
		if(str_edu == "" || str_edu == null) {
			str_edu = pd.getEducation();
		}
		if(str_title == "" || str_title == null) {
			str_title = pd.getTitle();
		}
		if(str_comp == "" || str_comp == null) {
			str_comp = pd.getCompany();
		}
		if(str_loc == "" || str_loc == null) {
			str_loc = pd.getLocation();
		}
		
		pdao.updateNew(String.valueOf(pd_id), str_uni, str_dep, str_frm, str_to, str_edu, str_title, str_comp, str_loc, "0");
		pdao.refresh(pd);
		response.sendRedirect("ProfileServlet");
	}

}
