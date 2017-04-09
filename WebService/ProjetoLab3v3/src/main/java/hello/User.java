package hello;

public class User {
	
	private String name;
	private boolean adm;
	private Login login;

	// Constructor method
	public User(String name, boolean adm, Login login){
		this.setName(name);
		this.setAdm(adm);
		this.setLogin(login);
	}
	
	// Get-Set for Name
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// Get-Set for ADM
	public boolean isAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
	// Get-Set for login
	public Login getLogin(){
		return this.login;
	}
	public void setLogin(Login login){
		this.login = login;
	}
	
	public boolean isValid(User user){
		return this.getName().equals(user.getName()) && this.getLogin().isValid(user.getLogin()) ? true : false;
	}
}
