/**
 * 
 */
package classes;

/**
 * @author luisgzz
 *
 */
public class Users {

	private String Username;
	private String Password;
	


public Users(){
	
	
}

public Users(String Username,String Password) {
	
	this.Username = Username;
	this.Password = Password;
}

/**
 * @return the username
 */
public String getUsername() {
	return Username;
}

/**
 * @param username the username to set
 */
public void setUsername(String username) {
	Username = username;
}

/**
 * @return the password
 */
public String getPassword() {
	return Password;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
	Password = password;
}

}

