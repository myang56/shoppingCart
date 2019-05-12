<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>

<!--  <link type="text/css" rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container">
		<div id="wrapper">
			<div id="header">
				<h2>Your Shopping Cart</h2>
			</div>
		</div>

		<div id="content">

			<!-- put new button: Add Product-->
			<br /> <input type="button" value="Add Products"
				onclick="window.location.href='add-product-form.jsp'; return false;"
				class="add-product-button" /> <br />
			<br />

			<table class="table table-striped table-bordered table-hover">

				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Edit</th>
					<th>Delete</th>

				</tr>

				<c:forEach var="tempProduct" items="${PRODUCT_LIST}">

					<!-- set up a link for each product -->
					<c:url var="tempLink" value="ProductControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="productId" value="${tempProduct.id}" />
					</c:url>

					<!--  set up a link to delete a product-->
					<c:url var="deleteLink" value="ProductControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="productId" value="${tempProduct.id}" />
					</c:url>

					<tr class="success">
						<td>${tempProduct.name}</td>
						<td>${tempProduct.price}</td>
						<td>${tempProduct.quantity}</td>
						<td><a href="${tempLink}"><img src="images/edit.png">
						</a></td>
						<td><a href="${deleteLink}"><img src="images/delete.png">
						</a></td>

					</tr>

				</c:forEach>

			</table>
			
			<br /> <input type="button" value="Check out"
				onclick="window.location.href='checkout.html'; return false;"
				class="add-product-button" /> <br />
			<br />


		</div>

	</div>
</body>


</html>