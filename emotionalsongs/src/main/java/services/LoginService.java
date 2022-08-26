package services;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import objects.User;
import persistence.User_Factory;

/**
 * @author Diana Cantaluppi 744457,
 *  Sede di Como
 */


public class LoginService {
    private static final Logger logger = Logger.getLogger(String.valueOf(LoginService.class));
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

    public User run() {
        Scanner cmdInput = new Scanner(System.in);

        System.out.print("Username: ");
        String inpUser = cmdInput.nextLine();
        System.out.print("Password: ");
        String inpPass = cmdInput.nextLine();
        
        cmdInput.close();

        return new User(inpUser, inpPass);
    }

    public boolean loginAttempt(User user) {
    	if(user.getUsername().isEmpty() || user.getUsername() == null) {
    		logger.info("invalid username");
    		return false;
    	}
    	
    	if(user.getPassword().isEmpty() || user.getPassword() == null) {
    		logger.info("invalid password");
    		return false;
    	}
    	
    	logger.info("try login for user " + user.getUsername());
        return userFactory.existUser(user);
    }

}