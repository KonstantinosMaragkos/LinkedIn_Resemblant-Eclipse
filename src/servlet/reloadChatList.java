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
 * Servlet implementation class reloadChatList
 */
@WebServlet("/reloadChatList")
public class reloadChatList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reloadChatList() {
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
 		UserDAO uDao = new UserDAOImpl();
		ChatDataDao cdDao = new ChatDataDaoImpl();
		ChatDao cDao = new ChatDaoImpl();
		List<Chat> cList = new ArrayList<Chat>();
		List<User> uList = new ArrayList<User>();

		if (aList.size() > 0) {
			aDao.refresh(aList.get(0));
			active = aList.get(0).getChat();
		}else {
			active = -1;
		}
		
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
 		}
 			
 		List<ChatData> cdList2 = new ArrayList<ChatData>();
		for(int i=0; i<cList.size(); i++) {
			cdList2.removeAll(cdList2);
			cdList2 = cdDao.LoadMessages(cList.get(i).getIdChat());
			if(active == cList.get(i).getIdChat()) {
				out.println("<li class=\"active\" onclick=change(" + user_id + "," + cList.get(i).getIdChat() + ")>");
			}else {
				out.println("<li onclick=change(" + user_id + "," + cList.get(i).getIdChat() + ")>");
			}
			out.print("<div class=\"chat-block\"><div class=\"img\"><img src=\"" + uList.get(i).getPhoto() +"\"></div>");
			if(cdList2.size() > 0) {
				out.print("<div class=\"desc\"><small>" + cdList2.get(cdList2.size()-1).getTime() + 
						"</small><h5>" + uList.get(i).getFirst_Name() + uList.get(i).getLast_Name() + 
						"</h5><small>" + cdList2.get(cdList2.size()-1).getText() + "</small></div></div></li>");
			}else {
				out.print("<div class=\"desc\"><h5>" + uList.get(i).getFirst_Name() + uList.get(i).getLast_Name() + 
						"</h5>");
			}
		}
		if(cList.size() <= 0) {
			out.print("<small>No chats yet...</small>");
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
