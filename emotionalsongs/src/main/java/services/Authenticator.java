package services;

import java.util.logging.Logger;
import object.User;

public class Authenticator {
	private static final Logger logger = Logger.getLogger(String.valueOf(Authenticator.class));
    
	private LoginService loginService;
	
	public Authenticator() {
		this.loginService = LoginService.getIstance();
	}
	
	public User actionLogin() throws Exception {
		User paramsUser = loginService.run();
		if(loginService.loginAttempt(paramsUser)) {
			
		} else {
			throw new Exception("User not found");
		}
		
		
		
		return null;
	}
	
}
