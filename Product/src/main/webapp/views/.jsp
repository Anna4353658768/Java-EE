<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Manufacturer"%>
<%
	Manufacturer r1 = new Manufacturer(1l,"Простоквашено","Россия","Иванов И.И.","+7(961)-289-44-39");
	Manufacturer r2 = new Manufacturer(2l, "Юг Руси","Россия","Рогов А.А.","+7(961)-289-55-24");
	Manufacturer r3= new Manufacturer(3l, "Кленовичи","Беларусь","Лыткин А.Р.","+7(961)-289-44-39");
	Manufacturer r4 = new Manufacturer(4l, "Мираторг","Россия","Зуева Т.Е.","+7(961)-289-33-57");
	Manufacturer[] manufacturers = new Manufacturer[]{r1, r2, r3, r4};
	int length = manufacturers.length;
	pageContext.setAttribute("manufacturers", manufacturers);
%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Производители</title>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Manufacturer</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- jQuery -->
	<script defer src="js/jquery-3.6.4.js"></script>
	<!-- Bootstrap JS + Popper JS -->
	<script defer src="js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="/views/header.jsp" />
		<div class="container-fluid">
			<div class="row justify-content-start ">
				<div class="col-8 border bg-light px-4">
					<h3>Список производителей</h3>
					<table class="table">
						<thead>
							<th scope="col">Код</th>
							<th scope="col">Производитель</th>
							<th scope="col">Страна</th>
							<th scope="col">Контактное лицо</th>
							<th scope="col">Телефон</th>
							<th scope="col"> Редактировать</th>
							<th scope="col">Удалить</th>
						</thead>
						<tbody>
							<c:forEach var="manufacturer" items="${manufacturers}">
								<tr>
									<td>${manufacturer.getId()}</td>
									<td>${manufacturer.getName()}</td>
									<td>${manufacturer.getCountry()}</td>
									<td>${manufacturer.getPerson()}</td>
									<td>${manufacturer.getPhone()}</td>
									<td width="20">
										<a href="#" role="button" class="btn btn-outline-primary">
											<img src="images/icon-edit.png" alt="Редактировать" width="30" height="30" >
										</a>
									</td>
									<td width="20">
										<a href="#" role="button" class="btn btn-outline-primary">
											<img src="images/icon-delete.png" alt="Удалить" width="30" height="30">
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-4 border px-40">
					<form method="POST" action="">
						<h3>Новый производитель</h3>

						<div class="mb-3 row"> <label for="manufacturer" class="col-sm-4 col-form-label">Производитель</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticManufacturer" name="manufacturer" /> </div>
						</div>
						
						<div class="mb-3 row"> <label for="country" class="col-sm-4 col-form-label">Страна</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticCountry" name="country" /> </div>
						</div>
						
						<div class="mb-3 row"> <label for="person" class="col-sm-4 col-form-label">Контактное лицо</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticPerson" name="person" /> </div>
						</div>
						
						<div class="mb-3 row"> <label for="phone" class="col-sm-4 col-form-label">Телефон</label>
							<div class="col-sm-7"> <input type="text" class="form-control" id="staticPhone" name="phone" /> </div>
						</div>
						
						
						<p> <br> <br> <br> <button type="submit" class="btn btn-primary">Добавить</button> <br> </p>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/views/footer.jsp" /> </div>
</body>

</html>