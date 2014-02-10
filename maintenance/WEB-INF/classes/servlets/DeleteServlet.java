package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import business.Product;
import data.ProductDB;
import util.MaintenancePool;


/*	DeleteServlet.java
	Chris Glock
	4/24/2007
	This servlet will delete the specified record whose code was passed to this servlet.

*/
public class DeleteServlet extends HttpServlet
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
		String code = request.getParameter("code");
		Connection connection = connectionPool.getConnection();
		try
		{
			ProductDB.deleteRecord(connection, code);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/servlets.ControllerServlet?View=fromDelete");
			dispatcher.forward(request, response);
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
