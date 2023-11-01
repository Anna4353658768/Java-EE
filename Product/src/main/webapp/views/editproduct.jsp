<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Manufacturer"%>
<%@ page import="domain.Product"%>


<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Редактирование данных</title>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Product</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- jQuery -->
	<script defer src="js/jquery.min.js"></script>
	<!-- Bootstrap JS + Popper JS -->
	<script defer src="js/bootstrap.min.js"></script>
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
							</thead>
							<tbody>
								<c:forEach var="product" items="${products}">
									<tr>
									<td height="60">${product.getId()}</td>
									<td>${product.getName()}</td>
									<td>${product.getManufacturer().getName()}</td>
									<td>${product.getWeight()}</td>
									
									<td>${product.getWidth()}</td>
									<td>${product.getHeight()}</td>
									<td>${product.getLength()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="col-4 border px-4">
						<form method="POST" action="">
							<h3>Редактирование данных</h3> <br>
							
							<div class="mb-3 row"> 
								<label for="idproduct" class="col-sm-3 col-form-label">Код товара</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" readonly id="idproduct" value="${productEdit.getId()}" />
								</div>
							</div>
							
							<div class="mb-3 row"> 
								<label for="name" class="col-sm-3 col-form-label">Название товара</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" id="staticName" name="name" value="${productEdit.getName()}" />        
								</div>
							</div>
							
							
							<div class="mb-3 row"> <label for="manufacturer" class="col-sm-3 col-form-label">Производитель</label>
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
							
							<div class="mb-3 row"> 
								<label for="weight" class="col-sm-3 col-form-label">Вес</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" id="staticWeight" name="weight" value="${productEdit.getWeight()}" />        
								</div>
							</div>
							
							<div class="mb-3 row"> 
								<label for="width" class="col-sm-3 col-form-label">Ширина</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" id="staticWidth" name="width" value="${productEdit.getWidth()}" />        
								</div>
							</div>
							
							<div class="mb-3 row"> 
								<label for="height" class="col-sm-3 col-form-label">Высота</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" id="staticHeight" name="height" value="${productEdit.getHeight()}" />        
								</div>
							</div>
							
							<div class="mb-3 row"> 
								<label for="length" class="col-sm-3 col-form-label">Длина</label>
								<div class="col-sm-7"> 
									<input type="text" class="form-control" id="staticLength" name="length" value="${productEdit.getLength()}" />        
								</div>
							</div>
							
							
							<p> <br>  
							<button type="submit" class="btn btn-primary">Редактировать</button> 
							<a href='<c:url value="/products" />' role="button" class="btn btn-secondary">Отменить</a> 
							<br>
							</p>
							
						</form>
					</div>
				</div>
			</div>
			<jsp:include page="/views/footer.jsp" /> 
		</div>
	</body>

</html>