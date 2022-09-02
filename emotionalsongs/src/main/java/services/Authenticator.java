package services;

import java.util.Scanner;

import objects.User;

public class Authenticator {    
	private LoginService loginService;
	private SignUpService signUpService;
	
	public Authenticator() throws Exception {
		this.loginService = LoginService.getIstance();
		this.signUpService = SignUpService.getIstance();
	}
	
	public User actionLogin(Scanner cmdInput) throws Exception {
		User paramsUser = loginService.run(cmdInput);
		if(!loginService.loginAttempt(paramsUser)) {
			throw new Exception("User not found");	
		}
		
		return loginService.loadUser(paramsUser);
	}
	
	public User actionRegisterUser(Scanner cmdInput) throws Exception {
		User userToReg = signUpService.insertdata(cmdInput); //the check for the data validity is inside
		userToReg = signUpService.insertNewUser(userToReg);
		return userToReg; 
	}
	
	public User actionLogout() {
		return null;
	}
	
}
