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
import dao.ProductDbDAO;
import dao.ManufacturerDbDAO;
import domain.Product;
import domain.Manufacturer;
import exception.DAOException;

@WebServlet("/editproduct")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	 
	public EditProductServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Manufacturer> manufacturers;
		List<Product> products = null;
		Product editproduct = null;
		ManufacturerDbDAO daoManufacturer = new ManufacturerDbDAO();
		ProductDbDAO dao = new ProductDbDAO();
		
		try {
			manufacturers = daoManufacturer.findAll();
			request.setAttribute("manufacturers", manufacturers);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		try {
			products = dao.findAll();
			manufacturers = daoManufacturer.findAll();
			for (Product product: products) {
				product.setManufacturer(daoManufacturer.FindById(product.getIdManufacturer(), manufacturers));
			}
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
			editproduct = dao.findById(id);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("productEdit", editproduct);
		request.setAttribute("products", products);
		userPath = request.getServletPath();
		if ("/editproduct".equals(userPath)) {
			request.getRequestDispatcher("/views/editproduct.jsp").forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDbDAO dao = new ProductDbDAO();
		ManufacturerDbDAO daoManufacturer = new ManufacturerDbDAO();
		
		String strId = request.getParameter("id");
		Long id = null;
		if(strId != null) {
			id = Long.parseLong(strId);
		}
		
		String name = request.getParameter("name");
		String weight = request.getParameter("weight");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String length = request.getParameter("length");
		String manufacturer = request.getParameter("manufacturer");
		
		int index1 = manufacturer.indexOf('='); 
		int index2 = manufacturer.indexOf(","); 
		
		String r1 = manufacturer.substring(index1+1, index2);
		Long idManufacturer = Long.parseLong(r1.trim());
		
		try {
			Manufacturer mn = daoManufacturer.findById(idManufacturer);
			Product editProduct = new Product(id, name, Float.parseFloat(weight), 
					Float.parseFloat(width), Float.parseFloat(height), Float.parseFloat(length), idManufacturer, mn);
			dao.update(editProduct);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/Product/products");
	}
	
}