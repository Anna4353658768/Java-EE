package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import domain.Manufacturer;
import exception.DAOException;
import dao.ManufacturerDbDAO;
import dao.ConnectionProperty;

@WebServlet("/editmanufacturer")
public class EditManufacturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	 
	public EditManufacturerServlet() throws FileNotFoundException, IOException {
		 super();
		 prop = new ConnectionProperty();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Manufacturer> manufacturers;
		Manufacturer editmanufacturer = null;
		ManufacturerDbDAO dao = new ManufacturerDbDAO();
		try {
			manufacturers = dao.findAll();
			request.setAttribute("manufacturers", manufacturers);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		String strId = request.getParameter("id");
		Long id = null;
		if(strId != null) {
			id = Long.parseLong(strId);
		}
		try {
			editmanufacturer = dao.findById(id);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("manufacturerEdit", editmanufacturer);
		userPath = request.getServletPath();
		if ("/editmanufacturer".equals(userPath)) {
			request.getRequestDispatcher("/views/editmanufacturer.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManufacturerDbDAO dao = new ManufacturerDbDAO();
		String strId = request.getParameter("id");
		Long id = null;
		if(strId != null) {
			id = Long.parseLong(strId);
		}
		
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		String person = request.getParameter("person");
		String phone = request.getParameter("phone");
		
		
		Manufacturer editmanufacturer = new Manufacturer(id, name, country, person, phone);
		try {
			dao.update(editmanufacturer);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/Product/manufacturers");
	}
}




