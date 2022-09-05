/**
* This package contains the classes that implements tha actions calling the factory.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package services;

import java.util.Scanner;

import objects.User;
import persistence.User_Factory;


public class LoginService {
	/**
	 * <code>istance</code>
	 * A LoginService object that allows to instantiate the class only once.
	 */
    private static LoginService istance = null;
    /**
	 * <code>userFactory</code>
	 * A UserFactory object use to invoke the methods of that class.
	 */
    private User_Factory userFactory;
    
    /**
	 *  LoginService default constructor.
	 *  
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
    private LoginService() throws Exception{
        this.userFactory = User_Factory.getIstance();
    }
    
    /**
     * This method return the istance of the LoginService object.
     * 
     * @return the object LoginService.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    public static LoginService getIstance() throws Exception {
    	if(istance == null)
    		return new LoginService();
    	else return istance;
    }

    /**
     * This method allows the user to type the username and password and create a user object with them.
     * If the user input is empty the program will continue to ask the user for credentials.
     * 
     * @param <cmdInput> User's input from cmd.
     * @return A User object create with the credentials typed by the user.
     */
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

    /**
     * This method return true if the user's credentials are not empty and the user is registered.
     * If both of the above conditions are not verified, it returns false.
     * 
     * @param <user> User object with which to attempt to login.
     * @return A boolean value.
     */
    public boolean loginAttempt(User user) {
    	if(user.getUsername().isEmpty() || user.getUsername() == null) {
    		System.out.println("Username invalido");
    		return false;
    	}
    	
    	if(user.getPassword().isEmpty() || user.getPassword() == null) {
    		System.out.println("Password invalida");
    		return false;
    	}
    	
        try {
			return userFactory.existUser(user);
		} catch (Exception e) {
			e = new Exception("Something went wrong during login");
			e.printStackTrace();
		}
        return false;
    }
    
    /**
     * 
     * @param <paramUser> The User object.
     * @return A User object.
     */
    public User loadUser(User paramUser) {
    	return userFactory.getByUsername(paramUser.getUsername());
    }

}
