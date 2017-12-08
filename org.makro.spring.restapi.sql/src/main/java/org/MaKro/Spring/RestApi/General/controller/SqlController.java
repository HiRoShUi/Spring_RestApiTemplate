package org.MaKro.Spring.RestApi.General.controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import classes.sql.*;

@RestController
public class SqlController {

    @RequestMapping("/rest/sql")
    public String showSqlTest() {
        return "Sql service is available!";
    }
    
    /*
     * Only activate this for debugging purpose. Should not be used productively! 
     * Everyone with the user + password could steal another DB-session..
     */
    /*@RequestMapping("/rest/sql/tokens")
    public List<UUID> getTokens() {
        if(TokenMap.isInitialized() == false)
        	TokenMap.init();
        return TokenMap.getAllTokens();
    }
    */
         
    
    @RequestMapping("/rest/sql/token")
    public HttpStatus isValid(
    		@RequestParam(value="id") UUID id){
    	
        if(TokenMap.isInitialized() == false)
        	TokenMap.init();
        
    	if(TokenMap.isIDKnown(id))
    		return HttpStatus.ACCEPTED;
    	return HttpStatus.BAD_REQUEST;
    }
	
        @RequestMapping("/rest/sql/connect")
	    public String connectWithUsernamePasswordDB(
	    		@RequestParam(value="conString", defaultValue="") String conString, 
	    		@RequestParam(value="user",defaultValue="") String user,
	    		@RequestParam(value ="database") String db,
        		@RequestParam(value="password",defaultValue="") String password){
	        
        	IConnector connector = new MySqlConnector(conString); 	
        	
        	String response = connector.Connect(user,password,db);     	
        	if(response != "Database connected!")
        		return null;
        	
        	if(TokenMap.isInitialized() == false)
            	TokenMap.init();
        	
        	UUID id = UUID.randomUUID();
        	TokenMap.add(id, connector);	
        	return id.toString();
        }
        
        @RequestMapping("/rest/sql/connect/instance")
        public String connectToInstance(
        		@RequestParam(value="conString") String conString,
        		@RequestParam(value="user",defaultValue="") String user,
        		@RequestParam(value="password",defaultValue="") String password)
        {
        	//ToDo: IoC
        	IConnector connector = new MySqlConnector(conString);
        	
          	String response = connector.Connect(user,password);     	
        	if(response != "Server connected!")
        		return null;
        	
        	if(TokenMap.isInitialized() == false)
            	TokenMap.init();
        	
        	UUID id = UUID.randomUUID();
        	TokenMap.add(id, connector);	
        	return id.toString();
        }
        
        @RequestMapping("/rest/sql/update-query")
	    public @ResponseBody String updateQuery(
	    		@RequestParam(value="query", defaultValue="") String query,
	    		@RequestHeader(value = "SqlToken") UUID sqlToken) throws SQLException{
	                	
        	if(TokenMap.isInitialized() == false)
            	TokenMap.init();
        	
        	IConnector connector = TokenMap.findSqlConnector(sqlToken);
        	
        	if(connector == null)
        		return "You are currently not connected. Please connect to a server and a database first!";
        	
        	if(query.equals(""))
        		return "Please specify a valid update-query!";       	
        	
        	return "You successfully updated " + connector.updateQuery(query) + " datasets.";
        }
        
        @RequestMapping("/rest/sql/select-query")
	    public String performQuery(
	    		@RequestParam(value="query", defaultValue="") String query,
	    		@RequestHeader(value = "SqlToken") UUID sqlToken) throws SQLException{
	                	
        	if(TokenMap.isInitialized() == false)
            	TokenMap.init();  	
        	
        	IConnector connector = TokenMap.findSqlConnector(sqlToken);
        	
        	if(connector == null)
        		return "You are currently not connected. Please connect to a server and a database first!";
        	
        	if(query.equals(""))
        		return "Please specify a select-query!";       	
        	
        	String aoErg = "Columns: ";
        	
        	ResultSet rs = connector.performQuery(query);
        	
        	ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();    
            for (int i = 1; i <= columnCount; i++) {
                aoErg += (metadata.getColumnName(i) + "; ");      
            }
            aoErg+= "\r\nDatasets:";            
            while (rs.next()) {
                String row = "";
                for (int i = 1; i <= columnCount; i++) {
                    row += rs.getString(i) + ", ";          
                }
                System.out.println();
                aoErg += row;
            }	
        	
        	return aoErg;
        }
}