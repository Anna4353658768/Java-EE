<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Manufacturer"%>
<%@ page import="domain.Product"%>
<%
	Manufacturer m1 = new Manufacturer(1l,"Юг стол","Россия","Иванов И.И.","+7(961)-289-44-39");
	Manufacturer m2 = new Manufacturer(2l, "Мебельград","Россия","Рогов А.А.","+7(961)-289-55-24");
	Manufacturer m3= new Manufacturer(3l, "Диван опт","Беларусь","Лыткин А.Р.","+7(961)-289-44-39");
	Manufacturer m4 = new Manufacturer(4l, "Икея","Россия","Зуева Т.Е.","+7(961)-289-33-57");
	Manufacturer[] manufacturers = new Manufacturer[]{m1, m2, m3, m4};
	pageContext.setAttribute("manufacturers", manufacturers);
	
	Product p1 = new Product(1l,"Стул", 4.5f, 0.5f, 0.5f, 1f, 1l, m1);
	Product p2 = new Product(2l,"Шкаф", 15f, 2.5f, 2f, 1f, 2l, m2);
	Product p3 = new Product(3l,"Стол", 7f, 1f, 1f, 1.5f, 3l, m3);
	Product p4 = new Product(4l,"Диван", 25f, 2f, 2f, 0.5f, 4l, m4);
	
	Product[] products = new Product[]{p1, p2, p3, p4};
	pageContext.setAttribute("products", products);
%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Товары</title>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Products</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- jQuery -->
	<script defer src="js/jquery.min.js"></script>
	<!-- Bootstrap JS + Popper JS -->
	<script defer src="js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>

<body>

	<body>
		<div class="container-fluid">
			<jsp:include page="/views/header.jsp" />
			<div class="container-fluid">
				<div class="row justify-content-start "> 
				<div class="col-8 border bg-light px-4">
					<h3>Список товаров</h3>
					<table class="table">
						<thead>
							<th scope="col">Код</th>
							<th scope="col">Название</th>
							<th scope="col">Производитель</th>
							<th scope="col">Вес</th>
							
							<th scope="col">Ширина</th>
							<th scope="col">Высота</th>
							<th scope="col">Длина</th>

							<th scope="col">Редактировать</th>
							<th scope="col">Удалить</th>
						</thead>
						<tbody>
							<c:forEach var="product" items="${products}">
								<tr>
									<td>${product.getId()}</td>
									<td>${product.getName()}</td>
									<td>${product.getManufacturer().getName()}</td>
									<td>${product.getWeight()}</td>
									
									<td>${product.getWidth()}</td>
									<td>${product.getHeight()}</td>
									<td>${product.getLength()}</td>

									<td width="20">
										<a href="#" role="button" class="btn btn-outline-primary">
											<img alt="Редактировать"src="images/icon-edit.png" width="30" height="30">
										</a> 
									</td>
									<td width="20">
										<a href="#" role="button" class="btn btn-outline-primary">
											<img alt="Удалить" src="images/icon-delete.png" width="30" height="30">
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-4 border px-4">
					<form method="POST" action="">
						<h3>Новый товар</h3> <br>
						
						<div class="mb-3 row"> <label for="name" class="col-sm-4 col-form-label">Название товара</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticName" name="name" /> </div>
						</div>
						
						<div class="mb-3 row"> <label for="manufacturer" class="col-sm-4 col-form-label">Производитель</label>
							<div class="col-sm-7"> 
								<select name="manufacturer" class="form-control">
									<option>Выберите производителя</option>
									<c:forEach var="manufacturer" items="${manufacturers}">
										<option value="${manufacturer}">
											<c:out value="${manufacturer.getName()}"/>
										</option>
									</c:forEach>
								</select> 
							</div>
						</div>
						
						<div class="mb-3 row"> <label for="weight" class="col-sm-4 col-form-label">Вес</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticWeight" name="weight" /> </div>
						</div>
						<div class="mb-3 row"> <label for="width" class="col-sm-4 col-form-label">Ширина</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticWidth" name="width" /> </div>
						</div>
						<div class="mb-3 row"> <label for="height" class="col-sm-4 col-form-label">Высота</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticHeight" name="height" /> </div>
						</div>
						<div class="mb-3 row"> <label for="length" class="col-sm-4 col-form-label">Длина</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticLength" name="length" /> </div>
						</div>
						
						<p> <br> <button type="submit" class="btn btn-primary">Добавить</button> </p>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/views/footer.jsp" /> </div>
	</body>

</html>