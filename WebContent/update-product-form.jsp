<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add to Cart</title>

<!-- 	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-product-style.css"> -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>	
</head>

<body>
	
	<div class ="container">
		<h3>Update Product</h3>
		
		<form action="ProductControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="productId" value="${THE_PRODUCT.id}" />
			
			
			
			<table class="table table-striped table-bordered table-hover">
				<tbody>
					<tr>
					<!-- choose product from prodcut list -->
						<td><label>Product name:</label></td>
						<td><input type="text" name="name" 
						value="${THE_PRODUCT.name}" /></td>
					</tr>
					
					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" 
						value="${THE_PRODUCT.price}" /></td>
					</tr>

					<tr>
						<td><label>Quantity:</label></td>
						<td><input type="text" name="quantity" 
						value="${THE_PRODUCT.quantity}" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ProductControllerServlet">Back to your shopping cart</a>
		</p>
	</div>
</body>

</html>
