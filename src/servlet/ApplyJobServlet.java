package servlet;


import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Aitisi;
import dao.AitisiDAO;
import dao.AitisiDAOImpl;

/**
 * Servlet implementation class ApplyJobServlet
 */
@WebServlet("/ApplyJobServlet")
public class ApplyJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyJobServlet() {
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
		
		String strIdAgg = request.getParameter("id");
		int idAgg = Integer.parseInt(strIdAgg);
		String status = "1";
		
		AitisiDAO adao = new AitisiDAOImpl();
		Aitisi ait = new Aitisi();
		//check for duplicate
		List<Aitisi> alist = adao.list();
		int flag=0;
		for(Aitisi a: alist) {
			if(a.getIdAggelia() == idAgg && a.getUser_id() == user_id) {
				flag = 1;
			}
		}
		if(flag == 0) {
			ait.setIdAitisi(getId());
			ait.setIdAggelia(idAgg);
			ait.setUser_id(user_id);
			ait.setStatus(status);
			while(adao.create(ait) == 1) { ; }
		}
		else {
			response.sendError(500);
		}
	}

	private int getId() {
		int x=1;
		AitisiDAO adao = new AitisiDAOImpl();
		List<Aitisi> alist = adao.list();
		while(true) {
			int flag=0;
			for(int i=0; i<alist.size(); i++) {
				if(x == alist.get(i).getIdAitisi()) {
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
