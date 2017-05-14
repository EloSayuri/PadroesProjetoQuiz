package hello;

public class Login {
	
	private String email;
	private String password;
	
	// Constructor method
	public Login(String email, String password){
		this.setEmail(email);
		this.setPassword(password);
	}

	// Get-Set for email
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Get-Set for password
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Check compatibility between two users

	public boolean isValid(Login login){
		return (this.email.equals(login.email) && this.password.equals(login.password)) ?  true : false;
	}
}
