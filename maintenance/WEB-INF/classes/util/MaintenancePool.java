package util;

import com.javaexchange.dbConnectionBroker.*;
import java.io.*;

public class MaintenancePool extends DbConnectionBroker
{
	private static MaintenancePool pool = null;

	private MaintenancePool(String driverName, String dbUrl, String username, String password, int minConns, int maxConns,
							String logFilename, double maxConnTimeInDays) throws IOException
	{
		super(driverName, dbUrl, username, password, minConns, maxConns, logFilename, maxConnTimeInDays);
	}

	public synchronized static MaintenancePool getInstance()
	{
		try
		{
			if (pool == null)
			{
				pool = new MaintenancePool("org.gjt.mm.mysql.Driver", "jdbc:mysql://localhost/maintenance", "root", "",
											10, 50, "../webapps/maintenance/WEB-INF/etc/connectPool.log", 1.0);
			}
		}
		catch(IOException ioe)
		{
			System.err.println("Maintenance IOException: " + ioe.getMessage());
		}
		return pool;
	}
}