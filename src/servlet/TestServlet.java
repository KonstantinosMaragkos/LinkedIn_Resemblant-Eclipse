package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
//import javax.servlet.*;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import model.Friend;
import dao.FriendsDAO;
import dao.FriendsDAOImpl;
/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
		
		String strId = request.getParameter("f_id");
		String strFile = request.getParameter("photo");
		
		Part eric = request.getPart("photo");
		String name = getFileName(eric);
		
		request.setAttribute("v1", strId);
		request.setAttribute("v2", name);
		request.setAttribute("v3", eric.getHeader("content-disposition"));
		
		request.getRequestDispatcher("test2.jsp").forward(request, response);
	}

	private String getFileName(final Part part) {
	    //final String partHeader = part.getHeader("content-disposition");
	    //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
