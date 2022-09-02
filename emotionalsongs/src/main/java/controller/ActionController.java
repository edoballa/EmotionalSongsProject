package controller;

import java.util.Scanner;

import objects.Action;
import objects.InputScanner;
import objects.Session;
import objects.User;
import services.Authenticator;
import services.SignUpService;

public class ActionController {
	private Scanner cmdInput;

	private Authenticator authenticator;
	
	public ActionController() throws Exception {
		cmdInput = new Scanner(System.in);
		
		authenticator = new Authenticator();
	}
	
	public int insertAction() {
		String input = "";
        boolean validAction = false;
        
        while(!validAction) {
        	System.out.print("Azione scelta: ");
        	input = cmdInput.nextLine();
	        
        	char[] chars = new char[input.length()];
        	input.getChars(0, input.length(), chars, 0);
	        
	        for(int i = 0; i < chars.length; i++) {
	        	switch(chars[i]) {
	        	case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
	        		validAction = true;
	        		break;
	        	default:
	        		System.out.println("I caratteri inseriti non sono validi. Scegliere nuovamente.");
	        		validAction = false;
	        		break;
	        	}
	        }
        }

        return Integer.valueOf(input);
	}
	
	public void printActionList(int actionId, boolean isLoggedIn) {
		int[] possibleActions = Action.possibleActions(actionId, isLoggedIn);
		
		System.out.println("Per effettuare un azione inserire il numero ad essa corrispondente, le azioni eseguibili sono :");
		for(int i = 0; i < possibleActions.length; i++) {
			System.out.println("(" + possibleActions[i] + ") -> " + Action.getActionDescription(possibleActions[i]));
		}
	}
	
	public void doAction(int actionId, Session session) {
		switch(actionId) {
			case Action.EXIT:
				break;
			case Action.LOGIN:
				try {
					User user = authenticator.actionLogin(cmdInput);
					session.setUser(user);
					System.out.println("Login effettuata correttamente!");
				} catch (Exception e) {
					e = new Exception("Something went wrong during login");
					e.printStackTrace();
				}
				break;
			case Action.LOGOUT:
				session.setUser(authenticator.actionLogout());
				break;
			case Action.REGISTRATION:
				try {
					User user = authenticator.actionRegisterUser(cmdInput);
					session.setUser(user);
					System.out.println("Registrazione effettuata correttamente!" );
				} catch (Exception e) {
					e = new Exception("Something went wrong during registration");
					e.printStackTrace();
				}
				break;
		}
	}
	
}
