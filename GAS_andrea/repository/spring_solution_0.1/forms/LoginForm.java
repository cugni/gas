package it.polito.ai.gas.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class LoginForm {

	private String command;
	@NotEmpty @Size(min = 1, max = 20)
	private String username;
	@NotEmpty @Size(min = 1, max = 32)
	private String password;
	
	
	// --- Getters & Setters ---
	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String name) {
		this.username = name;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}

}
