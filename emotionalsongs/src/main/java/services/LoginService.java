package services;

import java.util.Scanner;

import objects.User;
import persistence.User_Factory;

/**
 * @author Diana Cantaluppi 744457,
 *  Sede di Como
 */


public class LoginService {
    private static LoginService istance = null;
    private User_Factory userFactory;
    
    private LoginService() throws Exception{
        this.userFactory = User_Factory.getIstance();
    }
    
    public static LoginService getIstance() throws Exception {
    	if(istance == null)
    		return new LoginService();
    	else return istance;
    }

    public User run(Scanner cmdInput) {
    	String inpUser;
    	do {
    		System.out.print("Username: ");
    		inpUser = cmdInput.nextLine();
    		
    	} while(inpUser.isBlank());
    	
    	String inpPass;
    	do {
    		System.out.print("Password: ");
    		inpPass = cmdInput.nextLine();
    		
    	} while(inpPass.isBlank());

        return new User(inpUser, inpPass);
    }

    public boolean loginAttempt(User user) {
    	if(user.getUsername().isEmpty() || user.getUsername() == null) {
    		System.out.println("Username invalido");
    		return false;
    	}
    	
    	if(user.getPassword().isEmpty() || user.getPassword() == null) {
    		System.out.println("Password invalida");
    		return false;
    	}
    	
        return userFactory.existUser(user);
    }
    
    public User loadUser(User paramUser) {
    	return userFactory.getByUsername(paramUser.getUsername());
    }

}