package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AitisiDAO;
import dao.AitisiDAOImpl;
import model.Aitisi;

/**
 * Servlet implementation class ManageAppServlet
 */
@WebServlet("/ManageAppServlet")
public class ManageAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAppServlet() {
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
		int u_id = 0;
		try {
			u_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		String strAitisiId = request.getParameter("id");
		int AitisiId = Integer.valueOf(strAitisiId);
		String strStatus = request.getParameter("status");
		
		AitisiDAO adao = new AitisiDAOImpl();
		Aitisi ait = new Aitisi();
		ait = adao.find(AitisiId);
		
		if(strStatus.equals("0")) {
			//accept
			adao.updateStatus(ait, "0");
		}
		else {
			//decline;
			while(adao.remove(ait) == 1) { ; }
		}
		response.sendRedirect("RevJobAppsServlet");
	}

}
