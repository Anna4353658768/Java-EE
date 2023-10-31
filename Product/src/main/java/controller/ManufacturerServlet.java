package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import dao.ConnectionProperty;
import dao.ManufacturerDbDAO;
import domain.Manufacturer;
import exception.DAOException;

/**
* Servlet implementation class RoleServlet_
*/
@WebServlet("/manufacturers")
public class ManufacturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	
	/**
	* @throws IOException
	* @throws FileNotFoundException
	* @see HttpServlet#HttpServlet()
	*/
	
	public ManufacturerServlet()  throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Manufacturer> manufacturers;
		ManufacturerDbDAO dao = new ManufacturerDbDAO();
		
		try {
			manufacturers = dao.findAll();
			request.setAttribute("manufacturers", manufacturers);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		userPath = request.getServletPath();
		if ("/manufacturers".equals(userPath)) {
			request.getRequestDispatcher("/views/manufacturer.jsp").forward(request, response);
		}
		
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/manufacturer.jsp");
//        requestDispatcher.forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManufacturerDbDAO dao = new ManufacturerDbDAO();
		String name = request.getParameter("manufacturer");
		String country = request.getParameter("country");
		String person = request.getParameter("person");
		String phone = request.getParameter("phone");
		
		Manufacturer newManufacturer = new Manufacturer(name, country, person, phone);
		try {
			Long index = dao.insert(newManufacturer);
			System.out.println("Adding result: " + index );
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
	
	
	
	
	
}
