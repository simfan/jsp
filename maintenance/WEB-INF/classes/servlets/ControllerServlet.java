package servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import business.Product;
import data.ProductDB;
import util.MaintenancePool;

/*
	ControllerServlet.java
	Chris Glock
	5/07/2007
	This servlet will control which web page or servlet the session will go to.
*/

public class ControllerServlet extends HttpServlet
{
	private String productFile;
	private MaintenancePool connectionPool;
	public void init() throws ServletException
	{
		ServletConfig config = getServletConfig();
		ServletContext context = config.getServletContext();
		//productFile = context.getInitParameter("fileName");
		//Vector products = new Vector();
	   	//products = ProductIO.readRecords(productFile);
	   	//products = ProductDB.getProduct(connection, code);
	   	//session.setAttribute("products", products);
	   	//String encodedURL = response.encodeURL("/maintenance/servlet/servlets.ControllerServlet");
		connectionPool = MaintenancePool.getInstance();
	}

	public void destroy()
	{
		connectionPool.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
		Connection connection = connectionPool.getConnection();
	   	String code = request.getParameter("code");

	    Product product = new Product();
	    try{
	    if (code != null)
	    {
		    product = ProductDB.getProduct(connection, code);


	    }

	    HttpSession session = request.getSession();
		session.setAttribute("product", product);

		if (request.getParameter("Add") != null)
		{
		 //go to product.jsp?code=' '
		 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp?code=<%= product.getCode() %>");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("View") != null)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product_list.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("Index") != null)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("Update") != null)
		{

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/servlets.UpdateProductServlet?code=<%= product.getCode() %>");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("Delete") != null)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/servlets.DeleteServlet?code=<%=product.getCode() %>");
			dispatcher.forward(request, response);
		}

		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		}
		catch(SQLException sqle)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		connectionPool.freeConnection(connection);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);

	}
}