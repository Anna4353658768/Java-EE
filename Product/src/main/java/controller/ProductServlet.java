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
import dao.ProductDbDAO;
import domain.Manufacturer;
import domain.Product;
import exception.DAOException;

/**
* Servlet implementation class RoleServlet_
*/
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
 

	public ProductServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Product> products;
		List<Manufacturer> manufacturers;
		ManufacturerDbDAO daoManufacturer = new ManufacturerDbDAO();
		ProductDbDAO dao = new ProductDbDAO();
		
		try {
			products = dao.findAll();
			manufacturers = daoManufacturer.findAll();
			for (Product product: products) {
				product.setManufacturer(daoManufacturer.FindById(product.getIdManufacturer(), manufacturers));
			}
			request.setAttribute("products", products);
			request.setAttribute("manufacturers", manufacturers);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		userPath = request.getServletPath();
		if("/products".equals(userPath)){
			request.getRequestDispatcher("/views/product.jsp").forward(request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDbDAO dao = new ProductDbDAO();
		ManufacturerDbDAO daoManufacturer = new ManufacturerDbDAO();
		
		String name = request.getParameter("name");
		String weight = request.getParameter("weight");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String length = request.getParameter("length");
		String manufacturer = request.getParameter("manufacturer");
		
		int index1 = manufacturer.indexOf('='); 
		int index2 = manufacturer.indexOf(","); 
		
		String m1 = manufacturer.substring(index1 + 1, index2);
		Long idManufacturer = Long.parseLong(m1.trim());

		try {
			Manufacturer mn = daoManufacturer.findById(idManufacturer);
			Product newProduct = new Product(name, Float.parseFloat(weight), 
					Float.parseFloat(width), Float.parseFloat(height), Float.parseFloat(length), idManufacturer, mn);
			
			Long index = dao.insert(newProduct);
			System.out.println("Adding result: " + index );
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
	
	
}
