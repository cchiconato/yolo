package br.com.yolo.security;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
* @author  Chrisitan Chiconato
* @since   2018-02-18
*/

public class LoginDTO {

	@NotBlank
	@Email
	private String login;
	@NotBlank
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return password;
	}

	public void setSenha(String senha) {
		this.password = senha;
	}

	@Override
	public String toString() {
		return "LoginDTO [login=" + login + ", password=" + password + "]";
	}
	
	

}
