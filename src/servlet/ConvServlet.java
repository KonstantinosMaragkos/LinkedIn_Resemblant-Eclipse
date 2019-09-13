package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActiveDao;
import dao.ActiveDaoImpl;
import model.Active;

/**
 * Servlet implementation class ConvServlet
 */
@WebServlet("/ConvServlet")
public class ConvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");
		ActiveDao adao = new ActiveDaoImpl();
 		List<Active> active = adao.list(user_id);
 		if (active.size() > 0) {
 			adao.refresh(active.get(0));
 			session.setAttribute("active", active.get(0).getChat());
 		}else {
 			session.setAttribute("active", -1);
 		}
 		
 		request.getRequestDispatcher("conv_page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
