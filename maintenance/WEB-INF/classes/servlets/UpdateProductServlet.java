package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import business.Product;
import data.ProductDB;
import util.MaintenancePool;

/*
	UpdateProductServlet.java
	Chris Glock
	4/24/2007
	This servlet will either add a record to the product.txt file or update a current record, based upon the product code
*/

public class UpdateProductServlet extends HttpServlet
{
	private String productFile;
	private MaintenancePool connectionPool;
	public void init() throws ServletException
		{
			ServletConfig config = getServletConfig();
			ServletContext context = config.getServletContext();
			productFile = context.getInitParameter("fileName");
		}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String code = request.getParameter("pcode");
		String artist = request.getParameter("artist");
		String title = request.getParameter("album");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		Connection connection = connectionPool.getConnection();
		try
		{
			boolean codeExists = ProductDB.isMatch(connection, code);
			Product product = new Product();
		//product = ProductIO.readRecord(code, productFile);
		product = ProductDB.getProduct(connection, code);
		if (codeExists)
		{
			product = new Product(code, title, artist, category, description, price);
			//ProductIO.addRecord(product, productFile);
			ProductDB.addRecord(connection, product);
		}
		else
		{
			product = new Product(code, title, artist, category, description, price);
			//ProductIO.updateRecord(product, productFile);
			ProductDB.updateRecord(connection, product);
		}


/*		product.setCode(code);
		product.setArtist(artist);
		product.setTitle(title);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(price);

*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/servlets.ControllerServlet?View=fromUpdate");
		dispatcher.forward(request, response);
		return;
		}

		catch(SQLException sqle)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/servlets.ControlerServlet?Index=Index");
			dispatcher.forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		return;
	}
}
