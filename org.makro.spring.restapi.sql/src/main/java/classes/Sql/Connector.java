package classes.Sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connector {

	private java.sql.Connection connection;
	private boolean connected = false;
	//private final String conStng = "ec2-13-59-205-146.us-east-2.compute.amazonaws.com:443"; 
	private String conStng;

	public Connector(String aiConString){
		this.conStng = aiConString;
		this.connected = false;
	}
	
	//Connects to the database
	public String Connect(String aiUser, String aiPassword,String aiDBString){
		
		if(aiDBString.equals(""))
			return "You did not specify a database.";
		
	if(aiUser.equals("") || aiPassword.equals(""))	
		return "You did not specify a username or password";
	
		String url = "jdbc:mysql://" + conStng + "/" + aiDBString;
		
		try{
			connection = DriverManager.getConnection(url,aiUser,aiPassword);
			connected = true;
		    return "Server connected!";
		}
		catch(SQLException e){
			return "Could not connect to database " + aiDBString +": " + e.getMessage();
		}
	}
	
	//Connects to the server
		public String Connect(String aiUser, String aiPassword){
			
		if(aiUser.equals("") || aiPassword.equals(""))	
			return "You did not specify a username or password";
		
			String url = "jdbc:mysql://" + conStng + "/?user=" + aiUser +"&password=" + aiPassword;
			
			try{
				connection = DriverManager.getConnection(url,aiUser,aiPassword);
				connected = true;
			    return "Server connected!";
			}
			catch(SQLException e){
				return "Could not connect to server " + conStng +": " + e.getMessage();
			}
		}
	
	public void Disconnect() throws SQLException{
		if(connected != true) return;
		this.conStng = "";
		this.connected = false;
		connection.close();
		connection = null;
	}
	
	public ResultSet performQuery(String aiQuery) throws SQLException{
		if(connection == null)
			return null;
		
		Statement stm = connection.createStatement();
		
		ResultSet rs = stm.executeQuery(aiQuery);
		
		return rs;
	}
	
	public long updateQuery(String aiQuery) throws SQLException{
		if(connection == null)
			return -1;
		
	    Statement stmt = connection.createStatement();
	    return stmt.executeUpdate(aiQuery);    
	}
	
	
}
