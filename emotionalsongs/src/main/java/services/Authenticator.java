/**
* This package contains the busisness logic.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package services;

import java.util.Scanner;

import objects.User;

public class Authenticator {    
	/**
	 * <code>loginService</code>
	 * A LoginService object use to invoke the methods of that class.
	 */
	private LoginService loginService;
	/**
	 * <code>SignUpService</code>
	 * A SignUpService object use to invoke the methods of that class.
	 */
	private SignUpService signUpService;
	
	/**
	 *  Authenticator default constructor.
	 *  
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public Authenticator() throws Exception {
		this.loginService = LoginService.getIstance();
		this.signUpService = SignUpService.getIstance();
	}
	
	/**
	 * This method return a User object if the credentials entered by the user are correct.
	 * Otherwise it return null.
	 * 
	 * @param <cmdInput> User's input from cmd.
	 * @return A User object.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public User actionLogin(Scanner cmdInput) throws Exception {
		User paramsUser = loginService.run(cmdInput);
		if(!loginService.loginAttempt(paramsUser)) {
			System.out.println("User not found");
			return null;
		}
		
		return loginService.loadUser(paramsUser);
	}
	
	/**
	 * This method return a User object if the registration is successful.
	 * 
	 * @param <cmdInput> User's input from cmd.
	 * @return A User object.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public User actionRegisterUser(Scanner cmdInput) throws Exception {
		User userToReg = signUpService.insertdata(cmdInput); //the check for the data validity is inside
		userToReg = signUpService.insertNewUser(userToReg);
		return userToReg; 
	}
	
	/**
	 * This method is for logging out.
	 * 
	 * @return A User object with a null value.
	 */
	public User actionLogout() {
		return null;
	}
	
}
