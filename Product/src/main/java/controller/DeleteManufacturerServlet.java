package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import dao.ConnectionProperty;
import dao.ManufacturerDbDAO;
import exception.DAOException;

@WebServlet("/deletemanufacturer")
public class DeleteManufacturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	 
	public DeleteManufacturerServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManufacturerDbDAO dao = new ManufacturerDbDAO();
		String strId = request.getParameter("id");
		Long deleteid = null;
		if(strId != null) {
			deleteid = Long.parseLong(strId);
		}
		try 
		{
			dao.delete(deleteid);
		} 
		catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/Product/manufacturers");
	}
}
