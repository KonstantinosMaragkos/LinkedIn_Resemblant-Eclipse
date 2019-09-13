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
 * Servlet implementation class changeActive
 */
@WebServlet("/changeActive")
public class changeActive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeActive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int chat_id = Integer.parseInt(request.getParameter("ChatId"));
		int user = Integer.parseInt(request.getParameter("UserId"));
		
		ActiveDao act = new ActiveDaoImpl();
		List<Active> alist = act.list(user);
		if(alist.size() > 0) {
			act.update(user, chat_id);
		}else {
			Active active = new Active();
			active.setChat(chat_id);
			active.setUser_id(user);
			alist.removeAll(alist);
			alist = act.listAll();
			active.setIdActive(alist.get(alist.size()-1).getIdActive()+1);
			act.create(active);
		}
		session.setAttribute("active", chat_id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
