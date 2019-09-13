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

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import dao.PDataDAO;
import dao.PDataDAOImpl;
import model.PersonalData;
import dao.PDStatusDAO;
import dao.PDStatusDAOImpl;
import model.PdStatus;

/**
 * Servlet implementation class SignupServlet2
 */
@WebServlet("/SignupServlet2")
@MultipartConfig
public class SignupServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet2() {
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
		
		//get all parameters
		String strUser = request.getParameter("user");
		String strPass = request.getParameter("pass");
		String strPass2 = request.getParameter("pass2");
		String strName = request.getParameter("name");
		String strLname = request.getParameter("lname");
		String strPhone = request.getParameter("phone");
		//String strPhoto = request.getParameter("photo");
		
		Part PartPhoto = request.getPart("photo");
		String filename = getFileName(PartPhoto);
		//parse if not ready
		if(filename.contains("\\")) {
			int y = filename.lastIndexOf("\\");
			String parts[] = filename.split("");
			String last = "";
			for(int i=y+1; i<parts.length; i++) {
				last += parts[i];
			}
			filename = last;
		}
				
		HttpSession session = request.getSession();
		session.setAttribute("field", "ok");		//check if any field is empty
		session.setAttribute("pass_flag", "ok");	//check if passwords match
		session.setAttribute("opened_signup", "yes");
					
		if(strUser=="" || strPass=="" || strPass2=="" || strName=="" || strLname=="" || strPhone=="" || filename=="") {
			session.setAttribute("field", "no");
			response.sendRedirect("signup_page.jsp");
		}
		else if(!strPass.equals(strPass2)) {
			session.setAttribute("pass_flag", "no");
			response.sendRedirect("signup_page.jsp");
		}
		else {
			User user = new User();
			//set values - get hashed password first
			String strHash = getHash(strPass);
			if(strHash != null) {
				strPass = strHash;
			}
			user.setE_Mail(strUser);
			user.setPassword(strPass);
			user.setFirst_Name(strName);
			user.setLast_Name(strLname);
			user.setPhone(strPhone);
			
			UserDAO udao = new UserDAOImpl();
			//set id
			List<User> ulist = udao.list();
			User u = new User();
			for(int i=0; i<ulist.size(); i++) {
				u = ulist.get(i);
			}
			int x = u.getUser_id() + 1;
			user.setUser_id(x);
			
			//make user folder, check if general folder already exists - UPLOAD PHOTO
			
			File gen = new File("C:/media");
			gen.mkdir();
			
			File newDir = new File("C:/media/user" + x);
			
			newDir.mkdir();
			String strPhoto = newDir + "/" + filename;
			
			
			//make a logfile
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:/Users/Ερρίκοσ/workspace/TED/WebContent/info.log"), "utf-8"))) {
				writer.write("Path to media uploads of users created: C:/media");
			}
			
			OutputStream out = new FileOutputStream(strPhoto);
			InputStream cont = PartPhoto.getInputStream();
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
			
	        user.setPhoto(filename);
			//create new entry of personal data
			PersonalData pd = new PersonalData();
			PDataDAO pdao = new PDataDAOImpl();
			
			pd.setIdPersonal_Data(x);
			pd.setFinished_status(1);
			user.setPersonal_Data_id(x);
			//user.setPersonalData(pd);
			
			//create status input
			PdStatus pds = new PdStatus();
			PDStatusDAO status_dao = new PDStatusDAOImpl();
			pds.setIdPersonal_Data(x);
			pds.setAll(0);
			pd.setPdStatus(pds);
			
			pdao.create(pd);
			status_dao.create(pds);
			udao.create(user);
		
			session.setAttribute("username", user.getFirst_Name());
			session.setAttribute("user_id", user.getUser_id());
			session.setAttribute("pd_id", pd.getIdPersonal_Data());
			//response.sendRedirect("home_page.jsp");
			
			response.sendRedirect("personal_data.jsp");
		}
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
	
	private String getHash(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			return myHash;
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
