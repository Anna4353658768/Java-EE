package domain;

public class Product {
	
	private Long id;
	private String name;
	private Float weight;
	
	private Float width;
	private Float height;
	private Float length;
	
	private Long idManufacturer;
	private Manufacturer manufacturer;
	
	
	public Product() {
	}
	
	public Product(String name,Float weight,Float width,Float height,Float length, Manufacturer manufacturer) {
		this.name = name;
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.length = length;
		this.manufacturer = manufacturer;
	}
	
	public Product(String name,Float weight,Float width,Float height,Float length, Long idManufacturer, Manufacturer manufacturer) {
		this.name = name;
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.length = length;
		this.manufacturer = manufacturer;
		this.idManufacturer = idManufacturer;
	}
	
	
	public Product(Long id, String name,Float weight,Float width,Float height,Float length, Long idManufacturer, Manufacturer manufacturer) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.length = length;
		this.manufacturer = manufacturer;
		this.idManufacturer = idManufacturer;
	}
	
	
	public Float getLength() {
		return length;
	}
	
	public void setLength(Float length) {
		this.length = length;
	}
	
	public Float getHeight() {
		return height;
	}
	
	public void setHeight(Float height) {
		this.height = height;
	}
	
	public Float getWidth() {
		return width;
	} 
	
	public void setWidth(Float width) {
		this.width = width;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Float getWeight() {
		return weight;
	}
	
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public Long getIdManufacturer() {
		return idManufacturer;
	}
	
	public void setIdManufacturer(Long idManufacturer) {
		this.idManufacturer = idManufacturer;
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
 	@Override
 	public String toString() {
 		return "Role {" + "Id = " + id + ", name = " + name + ", weight = " + weight + 
 				", size width = " + width + ", size height = " + height +", size length = " + length
 				+", name Manufacturer = " + manufacturer.getName() + "}";
 	}
 	
}
