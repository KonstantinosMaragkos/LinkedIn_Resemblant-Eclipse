package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import dao.AggeliaDAO;
import dao.AggeliaDAOImpl;
import dao.ArthroDAO;
import dao.ArthroDAOImpl;
import dao.PDataDAO;
import dao.PDataDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Aggelia;
import model.Arthro;
import model.PersonalData;
import model.User;

/**
 * Servlet implementation class DownloadXMLSerlvet
 */
@WebServlet("/DownloadXMLSerlvet")
public class DownloadXMLSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadXMLSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String strId = (String) request.getParameter("id");
		int user_id = Integer.parseInt(strId);
		
		//get all info in form of lists
		//user
		UserDAO udao = new UserDAOImpl();
		User user = udao.find(user_id);
		
		//personal data
		PDataDAO pdata_dao = new PDataDAOImpl();
		PersonalData pd = pdata_dao.find(user_id);
		
		//arthro
		ArthroDAO ar_dao = new ArthroDAOImpl();
		List<Arthro> ar_list = ar_dao.list();
		List<Arthro> out_arthro = new ArrayList<Arthro>();
		for(Arthro ar: ar_list) {
			if(ar.getUser_id() == user_id) {
				out_arthro.add(ar);
			}
		}
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("user");
			doc.appendChild(rootElement);
			
			//Pdata
			// staff elements
			Element staff = doc.createElement("PersonalData");
			rootElement.appendChild(staff);
			//staff.setAttribute("id", Integer.toString(pd.getIdPersonal_Data()));
			
			//other elements
			if(pd.getCompany() != null) {
				Element temp = doc.createElement("company");
				temp.appendChild(doc.createTextNode(pd.getCompany()));
				staff.appendChild(temp);
			}
			
			if(pd.getDepartment() != null) {
				Element temp1 = doc.createElement("department");
				temp1.appendChild(doc.createTextNode(pd.getDepartment()));
				staff.appendChild(temp1);
			}
			
			if(pd.getEducation() != null) {
				Element temp2 = doc.createElement("education");
				temp2.appendChild(doc.createTextNode(pd.getEducation()));
				staff.appendChild(temp2);
			}
			
			if(pd.getLocation() != null) {
				Element temp4 = doc.createElement("location");
				temp4.appendChild(doc.createTextNode(pd.getLocation()));
				staff.appendChild(temp4);
			}
			
			if(pd.getTitle() != null) {
				Element temp5 = doc.createElement("title");
				temp5.appendChild(doc.createTextNode(pd.getTitle()));
				staff.appendChild(temp5);
			}
			
			if(pd.getUniversity() != null) {
				Element temp6 = doc.createElement("university");
				temp6.appendChild(doc.createTextNode(pd.getUniversity()));
				staff.appendChild(temp6);
			}
			
			if(Integer.toString(pd.getYearFrom()) != null) {
				Element temp7 = doc.createElement("yearFrom");
				temp7.appendChild(doc.createTextNode(Integer.toString(pd.getYearFrom())));
				staff.appendChild(temp7);
			}
			
			if(Integer.toString(pd.getYearTo()) != null) {
				Element temp8 = doc.createElement("yearTo");
				temp8.appendChild(doc.createTextNode(Integer.toString(pd.getYearTo())));
				staff.appendChild(temp8);
			}
			
			//User
			Element staff2 = doc.createElement("UserInfo");
			rootElement.appendChild(staff2);
			//staff2.setAttribute("id", Integer.toString(user_id));
			
			if(user.getE_Mail() != null) {
				Element temp10 = doc.createElement("e_Mail");
				temp10.appendChild(doc.createTextNode(user.getE_Mail()));
				staff2.appendChild(temp10);
			}
			
			if(user.getFirst_Name() != null) {
				Element temp11 = doc.createElement("first_Name");
				temp11.appendChild(doc.createTextNode(user.getFirst_Name()));
				staff2.appendChild(temp11);
			}
			
			if(user.getLast_Name() != null) {
				Element temp12 = doc.createElement("last_Name");
				temp12.appendChild(doc.createTextNode(user.getLast_Name()));
				staff2.appendChild(temp12);
			}
			
			if(user.getPhone() != null) {
				Element temp13 = doc.createElement("phone");
				temp13.appendChild(doc.createTextNode(user.getPhone()));
				staff2.appendChild(temp13);
			}
			
			if(user.getPhoto() != null) {
				Element temp14 = doc.createElement("photo");
				temp14.appendChild(doc.createTextNode(user.getPhoto()));
				staff2.appendChild(temp14);
			}
			
			//arthro
			staff2 = doc.createElement("Arthra");
			rootElement.appendChild(staff2);
			for(Arthro ar: out_arthro) {
				Element staff3 = doc.createElement("Arthro");
				staff2.appendChild(staff3);
				staff3.setAttribute("id", Integer.toString(ar.getIdArthro()));
				
				if(ar.getPhoto() != null) {
					Element temp10 = doc.createElement("Photo");
					temp10.appendChild(doc.createTextNode(ar.getPhoto()));
					staff3.appendChild(temp10);
				}
				
				if(ar.getText() != null) {
					Element temp11 = doc.createElement("Text");
					temp11.appendChild(doc.createTextNode(ar.getText()));
					staff3.appendChild(temp11);
				}
				
				if(ar.getVideo() != null) {
					Element temp12 = doc.createElement("Video");
					temp12.appendChild(doc.createTextNode(ar.getVideo()));
					staff3.appendChild(temp12);
				}
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:/media/user" + user_id + ".xml"));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			response.sendError(500);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			response.sendError(501);
		  }
	}
}
