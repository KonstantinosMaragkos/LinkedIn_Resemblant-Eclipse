package servlet;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDataDao;
import dao.ChatDataDaoImpl;
import model.ChatData;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreServlet() {
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

		String chat_id = request.getParameter("activeId");
		String user_id = request.getParameter("user_id");
		String text = request.getParameter("text");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");
		Date date = new Date();
		String cmtDate = sdf.format(date);
		String cmtTime = sdf2.format(date);
		
		ChatDataDao cdDao = new ChatDataDaoImpl();
		ChatData cd = new ChatData();
		ChatData newEnt = new ChatData();

		List<ChatData> cdList = cdDao.LoadMessages(Integer.parseInt(chat_id));
		for (int i=0; i<cdList.size(); i++) {
			cd = cdList.get(i);
		}
		int sr = cd.getSrNo();
		sr += 1;
		
		cdList.removeAll(cdList);
		cdList = cdDao.list();
		for (int i=0; i<cdList.size(); i++) {
			cd = cdList.get(i);
		}
		int id = cd.getIdChat_Data();
		id += 1;
		
		newEnt.setIdChat_Data(id);
		newEnt.setChat_id(Integer.parseInt(chat_id));
		newEnt.setText(text);
		newEnt.setUser_id(Integer.parseInt(user_id));
		newEnt.setDate(cmtDate);
		newEnt.setTime(cmtTime);
		newEnt.setSrNo(sr);

		cdDao.create(newEnt);

	}

}
