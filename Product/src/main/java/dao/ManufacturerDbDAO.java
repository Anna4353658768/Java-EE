package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Manufacturer;
import exception.DAOException;

/**
* Класс реализации функций взаимодействия с базой данных для таблицы
manufacturers
* (Производители)
*/
public class ManufacturerDbDAO implements RepositoryDAO<Manufacturer> {
	
	public ManufacturerDbDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// SQL-запросы к таблице manufacturers базы данных 
	private static final String select_all_manufacturer = "SELECT * FROM manufacturers ORDER BY name";
	private static final String select_manufacturer_ById = "SELECT * FROM manufacturers WHERE id = ?";
	private static final String insert_manufacturer = "INSERT INTO manufacturers(name, country, person, phone) VALUES(?,?,?,?)";
	private static final String edit_manufacturer = "UPDATE manufacturers SET name = ?, country = ?, person = ?, phone = ? WHERE id = ?";
	private static final String delete_manufacturer = "DELETE FROM manufacturers WHERE id = ?";
	
	// Создание соединения с базой данных
	private ConnectionBuilder builder = new DbConnectionBuilder();
	
	private Connection getConnection() throws SQLException {
		return builder.getConnection();
	}
	
	
	// Добавление нового производителя
	@Override
	public Long insert (Manufacturer manufacturer) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(insert_manufacturer, new String[] { "id" })) {
			Long Id = -1L;
			pst.setString(1, manufacturer.getName());
			pst.setString(2, manufacturer.getCountry());
			pst.setString(3, manufacturer.getPerson());
			pst.setString(4, manufacturer.getPhone());
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
	
	
	// Редактирование производителя
	@Override
	public void update(Manufacturer manufacturer) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst= con.prepareStatement(edit_manufacturer)) {
			pst.setString(1, manufacturer.getName());
			pst.setString(2, manufacturer.getCountry());
			pst.setString(3, manufacturer.getPerson());
			pst.setString(4, manufacturer.getPhone());
			pst.setLong(5, manufacturer.getId());
			pst.executeUpdate();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	// Удаление производителя
	@Override
	public void delete(Long Id) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_manufacturer)) {
			pst.setLong(1, Id);
			pst.executeUpdate();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	// Поиск производителя по Id
	@Override
	public Manufacturer findById(Long Id) throws DAOException {
		Manufacturer manufacturer = null;
		
		try (Connection con = getConnection()) {
			PreparedStatement pst = con.prepareStatement(select_manufacturer_ById);
			pst.setLong(1, Id);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				manufacturer = fillRole(rs);
			}
			
			rs.close();
			pst.close();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
		return manufacturer;
	}
	
	
	// Формирование списка всех производителей
	@Override
	public List<Manufacturer> findAll() throws DAOException {
		List<Manufacturer> list = new LinkedList<>();
		
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(select_all_manufacturer); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(fillRole(rs));
			}
			rs.close();
		} 
		catch (Exception e) {
			throw new DAOException(e);
		}
		return list;
	}
	
	
	// Формирование класса Производителя по результатам запроса к БД
	private Manufacturer fillRole(ResultSet rs) throws SQLException {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(rs.getLong("id"));
		manufacturer.setName(rs.getString("name"));
		manufacturer.setCountry(rs.getString("country"));
		manufacturer.setPerson(rs.getString("person"));
		manufacturer.setPhone(rs.getString("phone"));
		return manufacturer;
	}
	
	
	// Поиск производителя по id из списка производителей
	public Manufacturer FindById(Long id, List<Manufacturer> manufacturers) {
		if (manufacturers != null) {
			for (Manufacturer r : manufacturers) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} 
		else {
			return null;
		}
		return null;
	}
}