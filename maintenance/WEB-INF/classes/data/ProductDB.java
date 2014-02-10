package data;

import java.sql.*;
import business.Product;

public class ProductDB
{
	public static synchronized boolean isMatch(Connection connection, String code) throws SQLException
	{
		String query = "SELECT code FROM Product " +
					   "WHERE code = '"
					   		+ code + "'";
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		boolean codeExists = results.next();
		results.close();
		statement.close();
		return codeExists;
	}

	public static synchronized int addRecord(Connection connection, Product product) throws SQLException
	{
		String query = "INSERT INTO Product " +
						"(code, title, artist, category, description, price) " +
						"VALUES ('" + product.getCode() + "', " +
						"'" + product.getTitle() + "', " +
						"'" + product.getArtist() + "', " +
						"'" + product.getCategory() + "', " +
						"'" + product.getDescription() + "', " +
						"'" + product.getPriceNumber() + "', " + "')";
		Statement statement = connection.createStatement();
		int status = statement.executeUpdate(query);
		statement.close();
		return status;
	}

	public static synchronized int updateRecord(Connection connection, Product product) throws SQLException
	{
		String query = "UPDATE Product " + "SET code = '" + product.getCode() + "',"
						+ "'" + product.getTitle() + "', " +
						  "'" + product.getArtist() + "', " +
					 	  "'" + product.getCategory() + "', " +
						  "'" + product.getDescription() + "', " +
						  "'" + product.getPriceNumber() + "'" +
						  "WHERE code = '" + product.getCode() + "'";
		Statement statement = connection.createStatement();
 		int status = statement.executeUpdate(query);
		statement.close();
		return status;
	}

	public static synchronized int deleteRecord(Connection connection, String code) throws SQLException
	{
		String query = "DELETE FROM Product " +
						"WHERE code = '" + code + "'";
		Statement statement = connection.createStatement();
		int status = statement.executeUpdate(query);
		statement.close();
		return status;
	}

	public static synchronized Product getProduct(Connection connection, String code) throws SQLException
	{
		String query = "SELECT * FROM Product " +
						"WHERE code = '" + code + "'";
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		Product product = new Product();
		if (results.next() == true)
		{
			product.setCode(results.getString("code"));
			product.setTitle(results.getString("title"));
			product.setArtist(results.getString("artist"));
			product.setCategory(results.getString("category"));
			product.setDescription(results.getString("description"));
			product.setPrice(Double.parseDouble(results.getString("price")));
		}
		results.close();
		statement.close();
		return product;
	}
}


