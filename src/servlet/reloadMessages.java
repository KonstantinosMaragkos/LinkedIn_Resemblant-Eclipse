package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActiveDao;
import dao.ActiveDaoImpl;
import dao.ChatDao;
import dao.ChatDaoImpl;
import dao.ChatDataDao;
import dao.ChatDataDaoImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Active;
import model.Chat;
import model.ChatData;
import model.User;

/**
 * Servlet implementation class reloadMessages
 */
@WebServlet("/reloadMessages")
public class reloadMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reloadMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Get everything from DB
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");
		int active;
		
		ActiveDao aDao = new ActiveDaoImpl();
		List<Active> aList = aDao.list(user_id);
		
		//Find active
 		UserDAO uDao = new UserDAOImpl();
		ChatDataDao cdDao = new ChatDataDaoImpl();
		List<ChatData> cdList = new ArrayList<ChatData>();
		ChatDao cDao = new ChatDaoImpl();
		List<Chat> cList = new ArrayList<Chat>();
		int active_user_id = -1;
		List<User> uList = new ArrayList<User>();
		
		if (aList.size() > 0) {
			aDao.refresh(aList.get(0));
			active = aList.get(0).getChat();
		}else {
			active = -1;
		}
		
 		if(active > 0) {
 		
 			//Find active chat data
 			cdList = cdDao.LoadMessages(active);
 		
 			//Get chats
 			cList = cDao.getChats(user_id);
 		
 			//For each chat of user_id find the other user
 			int uId;
 			for(Chat c: cList) {
 				cDao.refresh(c);
 				if(c.getUser1() == user_id && c.getUser2() == user_id) {// Chating with himself
 					uId = user_id;
 				}else if(c.getUser1() != user_id){
 					uId = c.getUser1();
 				}else {
 					uId = c.getUser2();
 				}
 				uList.add(uDao.find(uId));
 				if(c.getIdChat() == active) {//Store the user of the active id
 					active_user_id = uId;
 				}
 			}
 			
 			for(ChatData cd: cdList) {
 				cdDao.refresh(cd);
				if(cd.getUser_id() == user_id) {
					out.print("<li class=\"msg-right\">");
					
				}else {
					out.print("<li class=\"msg-left\">");
				}
				out.print("<div class=\"msg-left-sub\">"+
						"<img src=\"" + uDao.find(cd.getUser_id()).getPhoto() +"\">"+
						"<div class=\"msg-desc\">" + cd.getText() + "</div>"+
						"<small>" + cd.getTime() + "</small></div></li>");
			}
 		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
