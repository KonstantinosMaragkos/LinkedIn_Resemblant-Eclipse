package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.InterestDAO;
import dao.InterestDAOImpl;
import model.Interest;

/**
 * Servlet implementation class ShowInterestServlet
 */
@WebServlet("/ShowInterestServlet")
public class ShowInterestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInterestServlet() {
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
		String strArthro_id = request.getParameter("arthro");
		
		int arthro_id = Integer.parseInt(strArthro_id);
		
		InterestDAO indao = new InterestDAOImpl();
		Interest in = new Interest();
		List<Interest> inlist = indao.list();
		int flag=0, del_id=-1;
		//search for row
		for(Interest i: inlist) {
			indao.refresh(i);
			if(i.getUser_id() == user_id && i.get_idArthro() == arthro_id) {
				//found
				flag = 1;
				del_id = i.getIdInterest();
			}
		}
		
		if(flag == 0) {
			//if not found, insert interest
			in.setIdInterest(getId());
			in.setUser_id(user_id);
			in.set_idArthro(arthro_id);
			while(indao.create(in) == 1) { ; }
			
			indao.refresh(in);
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("Ok");
		}
		else {
			//found, delete interest
			in = indao.find(del_id);
			indao.remove(in);
			
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("Deleted");
		}
	}

	private int getId() {
		int x=1;
		InterestDAO indao = new InterestDAOImpl();
		List<Interest> inlist = indao.list();
		while(true) {
			int flag=0;
			for(int i=0; i<inlist.size(); i++) {
				if(x == inlist.get(i).getIdInterest()) {
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
