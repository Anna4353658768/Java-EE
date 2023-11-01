<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Manufacturer"%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Производители</title>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Manufacturers</title>
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
						</thead>
						<tbody>
							<c:forEach var="manufacturer" items="${manufacturers}">
								<tr>
									<td height="60">${manufacturer.getId()}</td>
									<td>${manufacturer.getName()}</td>
									<td>${manufacturer.getCountry()}</td>
									<td>${manufacturer.getPerson()}</td>
									<td>${manufacturer.getPhone()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="col-4 border px-40">
					<form method="POST" action="">
						<h3>Редактировать производителя</h3> <br> <br>
						
						<div class="mb-3 row"> 
							<label for="idmanufacturer" class="col-sm-4 col-form-label"> Код производителя</label>
							<div class="col-sm-7"> 
								<input type="text" class="form-control" readonly value="${manufacturerEdit.getId()}" id="idManufacturer"/> 
							</div>
						</div>
						
						<div class="mb-3 row"> <br> 
							<label for="name" class="col-sm-4 col-form-label">Должность</label>
							<div class="col-sm-7"> 
								<input type="text" name="name" class="form-control" value="${manufacturerEdit.getName()}" id="staticName" /> 
							</div>
						</div>
						
						<div class="mb-3 row"> <br> 
							<label for="country" class="col-sm-4 col-form-label">Страна</label>
							<div class="col-sm-7"> 
								<input type="text" name="country" class="form-control" value="${manufacturerEdit.getCountry()}" id="staticCountry" /> 
							</div>
						</div>
						
						<div class="mb-3 row"> <br> 
							<label for="person" class="col-sm-4 col-form-label">Контактное лицо</label>
							<div class="col-sm-7"> 
								<input type="text" name="person" class="form-control" value="${manufacturerEdit.getPerson()}" id="staticPerson" /> 
							</div>
						</div>
						
						
						<div class="mb-3 row"> <br> 
							<label for="phone" class="col-sm-4 col-form-label">Телефон</label>
							<div class="col-sm-7"> 
								<input type="text" name="phone" class="form-control" value="${manufacturerEdit.getPhone()}" id="staticPhonen" /> 
							</div>
						</div>
						
						<p> <br> <br> <br> 
							<button type="submit" class="btn btn-primary">Редактировать</button> 
							<a href='<c:url value="/manufacturers" />' role="button" class="btn btn-secondary">Отменить</a> 
							<br> 
						</p>
						
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/views/footer.jsp" /> </div>
</body>

</html>