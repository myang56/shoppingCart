package com.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDbUtil productDbUtil;
	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/tryout")
	private DataSource dataSource;




	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		// create product db util and pass in the datasource
		productDbUtil = new ProductDbUtil(dataSource);

	}

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listProducts(request, response);
				break;

			case "ADD":
				addProduct(request, response);
				break;

			case "LOAD":
				loadProduct(request, response);
				break;

			case "UPDATE":
				updateProduct(request, response);
				break;

			case "DELETE":
				deleteProduct(request, response);
				break;

			default:
				listProducts(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read product id from the data
		String productId = request.getParameter("productId");

		// delete product form database
		productDbUtil.deleteProduct(productId);

		// send back to the list of products page
		listProducts(request, response);

	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {


		// read product info
		int id = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		// create new product
		Product newProd = new Product(id, name, price, quantity);
		
		// update on the database
		productDbUtil.updateProduct(newProd);

		// seend back to main page
		listProducts(request, response);
	
	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read product id from form data
		String productId = request.getParameter("productId");

		// get product from database (db util)
		Product product = productDbUtil.getProduct(productId);

		// place product in the request attribute
		request.setAttribute("THE_PRODUCT", product);

		// send to jsp page: update-product-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-product-form.jsp");
		dispatcher.forward(request, response);	

	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.valueOf(request.getParameter("price"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));

		Product newProd = new Product(name, price, quantity);
		productDbUtil.addProduct(newProd);

		// seend back to main page
		listProducts(request, response);

	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get the products form the db util
		List<Product> products = productDbUtil.getProducts();

		//add student to the request 
		request.setAttribute("PRODUCT_LIST", products);

		// send to jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
		dispatcher.forward(request, response);
	}

}
