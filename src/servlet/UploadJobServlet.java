package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AggeliaDAO;
import dao.AggeliaDAOImpl;
import model.Aggelia;

/**
 * Servlet implementation class UploadJobServlet
 */
@WebServlet("/UploadJobServlet")
public class UploadJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadJobServlet() {
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
		
		String strTitle = request.getParameter("title");
		String strDesc = request.getParameter("description");
		String strReq = request.getParameter("requirements");
		String strDes = request.getParameter("desired");
		String strLoc = request.getParameter("location");
		String strWork = request.getParameter("workload");

		AggeliaDAO adao = new AggeliaDAOImpl();
		List<Aggelia> alist = adao.list();
		Aggelia ag = new Aggelia();
		
		int id=1;
		for(Aggelia temp: alist) {
			id = temp.getIdAggelia();
		}
		ag.setIdAggelia(id+1);
		ag.setUser_id(user_id);
		ag.setTitle(strTitle);
		ag.setDescription(strDesc);
		ag.setRequirements(strReq);
		ag.setDesired(strDes);
		ag.setLocation(strLoc);
		ag.setWorkload(strWork);
		
		while(adao.create(ag) == 1) { ; }
		adao.refresh(ag);
		response.sendRedirect("JobsPageServlet");
	}

}
