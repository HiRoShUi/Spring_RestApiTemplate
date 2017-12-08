package classes.authorization;

public class AccountCredentials {

	private String username;
	private String password;
	
	public AccountCredentials(){
		username = "";
		password = "";
	}
	
	public AccountCredentials(String aiUsername, String aiPassword){
		username = aiUsername;
		password = aiPassword;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String aiUsername){
		username = aiUsername;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String aiPassword){
		password = aiPassword;
	}
	
}
