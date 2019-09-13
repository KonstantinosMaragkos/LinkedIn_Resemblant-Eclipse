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

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import dao.ArthroDAO;
import dao.ArthroDAOImpl;
import model.Arthro;

/**
 * Servlet implementation class UploadArticleServlet
 */
@WebServlet("/UploadArticleServlet")
@MultipartConfig
public class UploadArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadArticleServlet() {
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
		
		Arthro ar = new Arthro();
		ArthroDAO adao = new ArthroDAOImpl();
		String strText = request.getParameter("text_in");
		Part PartPhoto = request.getPart("photo");
		Part PartVideo = request.getPart("video");
		String strPhoto = getFileName(PartPhoto);
		String strVideo = getFileName(PartVideo);
		
		//upload media - text
		if(!strPhoto.equals("")) {
			strPhoto = uploadMedia(PartPhoto, strPhoto, user_id);
			ar.setPhoto(strPhoto);
		}
		if(!strVideo.equals("")) {
			strVideo = uploadMedia(PartVideo, strVideo, user_id);
			ar.setVideo(strVideo);
		}
		if(!strText.equals("")) {
			ar.setText(strText);
		}
		
		//get id
		List<Arthro> alist = adao.list();
		int id=1;
		for(Arthro temp: alist) {
			id = temp.getIdArthro();
		}
		ar.setIdArthro(id+1);
		ar.setUser_id(user_id);
		
		while(adao.create(ar) == 1) { ; }
		adao.refresh(ar);
		response.sendRedirect("HomepageServlet");
	}

	private String uploadMedia(final Part part, String filename, int uid) throws FileNotFoundException, IOException {
			//parse names if not ready
			if(filename.contains("\\")) {
				int y = filename.lastIndexOf("\\");
				String parts[] = filename.split("");
				String last = "";
				for(int i=y+1; i<parts.length; i++) {
					last += parts[i];
				}
				filename = last;
			}
			String temp = "C:/media/user" + uid + "/" + filename;
			
			OutputStream out = new FileOutputStream(temp);
			InputStream cont = part.getInputStream();
			int read = 0;
	        byte[] bytes = new byte[1024];
	        while ((read = cont.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        if (out != null) {
	            out.close();
	        }
	        if (cont != null) {
	        	cont.close();
	        }
	        return filename;
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
