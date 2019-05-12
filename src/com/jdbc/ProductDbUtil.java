package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class ProductDbUtil {

	private DataSource dataSource;

	public ProductDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Product> getProducts() throws Exception {

		List<Product> products = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rSet = null;

		try {
			// get connnection 
			conn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from product";

			stmt = conn.createStatement();

			// execute query
			rSet = stmt.executeQuery(sql);

			// process result set
			while (rSet.next()) {

				// retrieve data from result set 
				int id = rSet.getInt("id");
				String name = rSet.getString("name");
				double price = rSet.getDouble("price");
				int quantity = rSet.getInt("quantity");

				// create new product object
				Product product = new Product(id, name, price, quantity);

				// add to the list of product
				products.add(product);
			}

			return products;
		} finally {
			close(conn, stmt, rSet);
		}
	}

	public void addProduct(Product product) throws Exception {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into product "
					+ "(name, price, quantity) "
					+ "values (?, ?, ?)";
			stmt = conn.prepareStatement(sql);

			// set the value for the product
			stmt.setString(1, product.getName());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getQuantity());

			stmt.execute();
		} finally {
			close(conn, stmt, null);
		}
	}

	public Product getProduct(String productId) throws Exception {
		// TODO Auto-generated method stub

		Product product = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;


		try {

			int prodId = Integer.parseInt(productId);

			// get connection
			conn = dataSource.getConnection();

			// creat sql
			String sql = "select * from product where id=?";

			// create prepared statement
			stmt = conn.prepareStatement(sql);

			// set params
			stmt.setInt(1, prodId);

			// execute statement
			rSet = stmt.executeQuery();

			if (rSet.next()) {
				// retrieve data from result set 
				String name = rSet.getString("name");
				double price = rSet.getDouble("price");
				int quantity = rSet.getInt("quantity");

				// create new product object
				product = new Product(prodId, name, price, quantity);

			} else {
				throw new Exception("The product " + prodId + " is not exist");
			}
			return product;

		} finally {
			close(conn, stmt, rSet);
		}

	}

	public void deleteProduct(String productId) throws Exception {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			int prodId = Integer.parseInt(productId);

			// get connection
			conn = dataSource.getConnection();

			// creat sql
			String sql = "DELETE from product where id=?";

			// create prepared statement
			stmt = conn.prepareStatement(sql);

			// set params
			stmt.setInt(1, prodId);

			stmt.execute();

		} finally {
			close(conn, stmt, null);
		}

	}

	public void updateProduct(Product newProd) throws Exception {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			
			// get connection
			conn = dataSource.getConnection();

			// creat sql
			String sql = "UPDATE product SET quantity=? where id=?";

			// create prepared statement
			stmt = conn.prepareStatement(sql);

			// set params
			stmt.setInt(1, newProd.getQuantity());
			stmt.setInt(2, newProd.getId());

			stmt.execute();

		} finally {
			close(conn, stmt, null);
		}


	}

	private void close(Connection conn, Statement stmt, ResultSet rSet) {

		try {
			if (rSet != null) {
				rSet.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}	
	}




}
