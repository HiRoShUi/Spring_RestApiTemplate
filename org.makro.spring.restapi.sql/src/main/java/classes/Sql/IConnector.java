package classes.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * IConnector interface: This makes it possible to write DB-Connector implementations for every DB.
 */
public interface IConnector {
	
	//Methods
	ResultSet performQuery(String query) throws SQLException;
	long updateQuery(String query) throws SQLException;
	String Connect(String user, String password);
	String Connect(String aiUser, String aiPassword,String aiDBString);
	void Disconnect() throws SQLException;
	
	//Properties
	Boolean IsConnected();
	String GetConnectionString();
	void SetConnectionString(String aiConnectionString);

}
