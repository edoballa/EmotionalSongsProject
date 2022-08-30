package services;

import objects.User;

public class Authenticator {    
	private LoginService loginService;
	private SignUpService signUpService;
	
	public Authenticator() throws Exception {
		this.loginService = LoginService.getIstance();
		this.signUpService = SignUpService.getIstance();
	}
	
	public User actionLogin() throws Exception {
		User paramsUser = loginService.run();
		if(loginService.loginAttempt(paramsUser)) {
			
		} else {
			throw new Exception("User not found");
		}
		
		return null;
	}
	
	public User actionRegisterUser() throws Exception {
		User userToReg = signUpService.insertdata();
		if(!signUpService.checkUserDataInsert(userToReg)) {
			throw new Exception("Incorrect data");
		}
		if(signUpService.checkUniqueData(userToReg) != null) {
			//TODO manage all cases to reinsert data
		}
		userToReg = signUpService.insertNewUser(userToReg);
		return userToReg; 
	}
	
	public User actionLogout() {
		return null;
	}
	
}
