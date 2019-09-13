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
import dao.ChatDao;
import dao.ChatDaoImpl;
import model.Active;
import model.Chat;

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");
		int user_id2 = Integer.parseInt(request.getParameter("user"));
		
		ActiveDao adao = new ActiveDaoImpl();
		ChatDao cdao = new ChatDaoImpl();
 		List<Active> active = adao.list(user_id);
 		List<Chat> chat = cdao.getChats(user_id);
 		int chat_id = -1;

 		if(active.size() > 0) {
 			//Look if there is a chat between user1 user2
 			for(Chat c: chat) {
 				cdao.refresh(c);
 				if(c.getUser1() == user_id2 || c.getUser2() == user_id2) {
 					chat_id = c.getIdChat();
 					break;
 				}
 			}
 			if(chat_id != -1) {
 				//We have a chat
 				adao.refresh(active.get(0));
 				if(active.get(0).getChat() != chat_id) {
 					//update the active
 					adao.update(user_id, chat_id);
 				} 				
 			}else {
 				//No chat between current users
 				//create and change active
 				Chat c = new Chat();
 				chat.removeAll(chat);
 				chat = cdao.list();
 				c.setIdChat(chat.get(chat.size()-1).getIdChat()+1);
 				c.setStatus(1);
 				c.setUser1(user_id);
 				c.setUser2(user_id2);
 				cdao.create(c);
 				adao.update(user_id, c.getIdChat());
 			}
 		} else {
 			//Create active entry for user and chat
 			Chat c = new Chat();
			chat.removeAll(chat);
			chat = cdao.list();
			if(chat.size() > 0) {//has chat but no active
				cdao.refresh(chat.get(chat.size()-1));
				c.setIdChat(chat.get(chat.size()-1).getIdChat()+1);
			}else {//No active no chat
				c.setIdChat(1);
			}
			c.setStatus(1);
			c.setUser1(user_id);
			c.setUser2(user_id2);
			cdao.create(c);
			Active a = new Active();
			active.removeAll(active);
			active = adao.listAll();
			if(active.size() > 0) { // there are active entries
				a.setIdActive(active.get(active.size()-1).getIdActive()+1);
			}else {//Nothing in active
				a.setIdActive(1);
			}
			
			a.setUser_id(user_id);
			a.setChat(c.getIdChat());
			adao.create(a);
 		}
 		
 		request.getRequestDispatcher("ConvServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
