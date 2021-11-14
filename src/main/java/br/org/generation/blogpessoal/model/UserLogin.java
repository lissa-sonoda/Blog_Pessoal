package br.org.generation.blogpessoal.model;

public class UserLogin {
	
	private long idlog;
	
	private String namelog;
	
	private String usernamelog;
	
	private String passwordlog;
	
	private String token;

	public long getIdlog() {
		return idlog;
	}

	public void setIdlog(long idlog) {
		this.idlog = idlog;
	}

	public String getNamelog() {
		return namelog;
	}

	public void setNamelog(String namelog) {
		this.namelog = namelog;
	}

	public String getUsernamelog() {
		return usernamelog;
	}

	public void setUsernamelog(String usernamelog) {
		this.usernamelog = usernamelog;
	}

	public String getPasswordlog() {
		return passwordlog;
	}

	public void setPasswordlog(String passwordlog) {
		this.passwordlog = passwordlog;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
