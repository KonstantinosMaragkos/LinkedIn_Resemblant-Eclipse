package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import model.Aggelia;
import model.Aitisi;
import dao.AggeliaDAO;
import dao.AggeliaDAOImpl;
import dao.AitisiDAO;
import dao.AitisiDAOImpl;

/**
 * Servlet implementation class RevJobAppsServlet
 */
@WebServlet("/RevJobAppsServlet")
public class RevJobAppsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevJobAppsServlet() {
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
		int user_id = 0;
		try {
			user_id = (int) session.getAttribute("user_id");
		}
		catch(NullPointerException e){
			response.sendRedirect("welcome_page.jsp");
			return;
		}
		
		AitisiDAO adao = new AitisiDAOImpl();
		List<Aitisi> alist = adao.list();
		AggeliaDAO agg_dao = new AggeliaDAOImpl();
		List<Aggelia> aggelies = agg_dao.list(); 
		UserDAO udao = new UserDAOImpl();
		
		List<Aggelia> agg_user = agg_dao.list(); 
		agg_user.removeAll(agg_user);
		List<Aitisi> ait_user = adao.list();
		ait_user.removeAll(ait_user);
		ArrayList<Integer> app_count = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		
		
		//add jobs(aggelia), and for each one add its applicatons by various users, keeping a count on each occassion.
		int i;
		for(Aggelia ag: aggelies) {
			agg_dao.refresh(ag);
			if(ag.getUser_id() == user_id) {
				agg_user.add(ag);
				i=0;										//get the application count in each job
				for(Aitisi a: alist) {
					adao.refresh(a);
					if(a.getIdAggelia() == ag.getIdAggelia() && a.getStatus().equals("1")) {
						ait_user.add(a);
						i++;
						User u = udao.find(a.getUser_id());
						names.add(u.getFirst_Name() + " " + u.getLast_Name());
					}
				}
				app_count.add(i);
			}
		}
		
		String json = new Gson().toJson(agg_user);
		String json2 = new Gson().toJson(ait_user);
		String json3 = new Gson().toJson(app_count);
		String json4 = new Gson().toJson(names);
		request.setAttribute("aggelies", json);
		request.setAttribute("aitiseis", json2);
		request.setAttribute("app_count", json3);
		request.setAttribute("names", json4);
		request.setAttribute("conf_flag", 1);
		request.getRequestDispatcher("review_apps.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
