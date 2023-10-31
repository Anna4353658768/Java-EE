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
		doGet(request, response);
	}
	
	
}
