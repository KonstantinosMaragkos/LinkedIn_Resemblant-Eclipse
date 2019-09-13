package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SxolioArthroDAO;
import dao.SxolioArthroDAOImpl;
import model.SxolioArthro;

/**
 * Servlet implementation class UploadCommentServlet
 */
@WebServlet("/UploadCommentServlet")
public class UploadCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCommentServlet() {
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
		
		String strArthro = request.getParameter("arthro");
		int arthro_id = Integer.parseInt(strArthro);
		String strText = request.getParameter("text");
		
		SxolioArthroDAO sadao = new SxolioArthroDAOImpl();
		SxolioArthro sa = new SxolioArthro();
		sa.setIdSxolio_Arthro(getId());
		sa.set_idArthro(arthro_id);
		sa.setUser_id(user_id);
		sa.setComment(strText);
		while(sadao.create(sa) == 1) { ; }
	}

	private int getId() {
		int x=1;
		SxolioArthroDAO sadao = new SxolioArthroDAOImpl();
		List<SxolioArthro> slist = sadao.list();
		while(true) {
			int flag=0;
			for(int i=0; i<slist.size(); i++) {
				if(x == slist.get(i).getIdSxolio_Arthro()) {
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
