package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.PDStatusDAO;
import dao.PDStatusDAOImpl;
import model.PdStatus;

/**
 * Servlet implementation class ChangeStatusServlet
 */
@WebServlet("/ChangeStatusServlet")
public class ChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatusServlet() {
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
		int id = 0;
		try {
			id = (int) session.getAttribute("pd_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		PdStatus pds = new PdStatus();
		PDStatusDAO status_dao = new PDStatusDAOImpl();
		pds = status_dao.find(id);
		status_dao.refresh(pds);
		
		String json = new Gson().toJson(pds);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String strStatus = request.getParameter("status");
		String strField = request.getParameter("field");
		
		//find current obj
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("pd_id");
		PdStatus pds = new PdStatus();
		PDStatusDAO status_dao = new PDStatusDAOImpl();
		pds = status_dao.find(id);
		
		//parse field and update
		String[] tokens = strField.split("_");
		status_dao.update(tokens[1], strStatus, id);
	}

}
