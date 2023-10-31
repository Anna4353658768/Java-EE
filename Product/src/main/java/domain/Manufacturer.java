package domain;

public class Manufacturer {

	private Long id;
	private String name;
	private String country;
	private String person;
	private String phone;
	
	
	public Manufacturer() {
	}
	
	public Manufacturer(String name,String country,String person,String phone) {
		this.name = name;
		this.country = country;
		this.person = person;
		this.phone = phone;
	}
	
	public Manufacturer(Long id,String name,String country,String person,String phone) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.person = person;
		this.phone = phone;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@Override
	public String toString() {
		return "Manufacturer {" + "Id = " + id + ", name = " + name + 
				", country = " + country + ", person = " + person + ", phone = " + phone + "}";
	}
}
