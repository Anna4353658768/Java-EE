package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Product;
import exception.DAOException;


/**
* Класс реализации функций взаимодействия с базой данных для таблицы
Product
* (Товары)
*/
public class ProductDbDAO implements RepositoryDAO<Product> {
	
	// SQL-запросы к таблице persons базы данных
	private static final String select_all_product = "SELECT * FROM products ORDER BY name";
	private static final String select_product_ById = "SELECT * FROM products WHERE id = ?";
	private static final String insert_product = "INSERT INTO products(manufacturerid, name, weight, width, height, length) VALUES(?,?,?,?,?,?)";
	private static final String edit_product = "UPDATE products SET manufacturerid = ?, name = ?, weight = ?, width = ?, height = ?, length = ? WHERE id = ?";
	private static final String delete_product = "DELETE FROM products WHERE id = ?";
	
	// Создание соединения с базой данных
	private ConnectionBuilder builder = new DbConnectionBuilder();
	
	private Connection getConnection() throws SQLException {
		return builder.getConnection();
	}
	
	ManufacturerDbDAO dao = new ManufacturerDbDAO();
	public ProductDbDAO() {
	}
	
	
	@Override
	public Long insert (Product product) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_product, new String[] { "id" })) {
			Long Id = -1L;
			pst.setLong(1, product.getIdManufacturer());
			pst.setString(2, product.getName());
			pst.setFloat(3, product.getWeight());
			pst.setFloat(4, product.getWidth());
			pst.setFloat(5, product.getHeight());
			pst.setFloat(6, product.getLength());
			pst.executeUpdate();
			ResultSet gk = pst.getGeneratedKeys();
			if (gk.next()) {
				Id = gk.getLong("id");
			}
			gk.close();
			return Id;
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	@Override
	public void update(Product product) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_product)) {
			pst.setString(1, product.getIdManufacturer().toString());
			pst.setString(2, product.getName().toString());
			pst.setString(3, product.getWeight().toString());
			pst.setString(4, product.getWidth().toString());
			pst.setString(5, product.getHeight().toString());
			pst.setString(6, product.getLength().toString());
			pst.executeUpdate();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	@Override
	public void delete(Long Id) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_product)) {
			pst.setLong(1, Id);
			pst.executeUpdate();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	@Override
	public Product findById(Long Id) throws DAOException {
		Product product = null;
		try (Connection con = getConnection()) {
			PreparedStatement pst = con.prepareStatement(select_product_ById);
			pst.setLong(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				product = fillPerson(rs);
			}
			rs.close();
			pst.close();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
		return product;
	}
	
	
	@Override
	public List<Product> findAll() throws DAOException {
		List<Product> list = new LinkedList<>();
		
		try (Connection con = getConnection();PreparedStatement pst = con.prepareStatement(select_all_product); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(fillPerson(rs));
			}
			rs.close();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	private Product fillPerson(ResultSet rs) throws SQLException, DAOException {
		Long manufacturerid = rs.getLong("manufacturerid");
		Product product = new Product();
		
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setWeight(rs.getFloat("weight"));
		product.setWidth(rs.getFloat("width"));
		product.setHeight(rs.getFloat("height"));
		product.setLength(rs.getFloat("length"));
		product.setIdManufacturer(manufacturerid);
		
		return product;
	}
}




