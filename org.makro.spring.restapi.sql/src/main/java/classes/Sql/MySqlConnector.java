package classes.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * MySqlConnector makes it possible to use a simple Dataconnector to a MySql-DB.
 * You can use this class as an example for another DB-connector (e.g. Oracle, MSSQL..). 
 * If you want to 
 */
public class MySqlConnector implements IConnector {

	private java.sql.Connection _connection;
	private boolean _connected = false;
	//private final String conStng = "ec2-13-59-205-146.us-east-2.compute.amazonaws.com:443"; 
	private String _conString;

	public MySqlConnector(String aiConString){
		this._conString = aiConString;
		this._connected = false;
	}
	
	//Connects to the database
	public String Connect(String aiUser, String aiPassword,String aiDBString){
		
		if(aiDBString.equals(""))
			return "You did not specify a database.";
		
	if(aiUser.equals("") || aiPassword.equals(""))	
		return "You did not specify a username or password";
	
		String url = "jdbc:mysql://" + _conString + "/" + aiDBString;//+"?useSSL=false";
		
		try{
			_connection = DriverManager.getConnection(url,aiUser,aiPassword);
			_connected = true;
		    return "Database connected!";
		}
		catch(SQLException e){
			return "Could not connect to database " + aiDBString +": " + e.getMessage();
		}
	}
	
	//Connects to the server
		public String Connect(String aiUser, String aiPassword){		
		if(aiUser.equals("") || aiPassword.equals(""))	
			return "You did not specify a username or password";
		
			String url = "jdbc:mysql://" + _conString + "/?user=" + aiUser +"&password=" + aiPassword;
			
			try{
				_connection = DriverManager.getConnection(url,aiUser,aiPassword);
				_connected = true;
			    return "Server connected!";
			}
			catch(SQLException e){
				return "Could not connect to server " + _conString +": " + e.getMessage();
			}
		}
	
	public void Disconnect() throws SQLException{
		if(_connected != true) return;
		this._conString = "";
		this._connected = false;
		_connection.close();
		_connection = null;
	}
	
	public ResultSet performQuery(String aiQuery) throws SQLException{
		if(_connection == null)
			return null;
		
		Statement stm = _connection.createStatement();
		
		ResultSet rs = stm.executeQuery(aiQuery);
		
		return rs;
	}
	
	public long updateQuery(String aiQuery) throws SQLException{
		if(_connection == null)
			return -1;
		
	    Statement stmt = _connection.createStatement();
	    return stmt.executeUpdate(aiQuery);    
	}

	@Override
	public Boolean IsConnected() {
		return _connected;
	}

	@Override
	public String GetConnectionString() {
		return _conString;
	}

	@Override
	public void SetConnectionString(String aiConnectionString) {
		_conString = aiConnectionString;
	}
}
