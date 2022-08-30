package controller;

import java.util.Scanner;

import objects.Action;
import objects.Session;
import services.Authenticator;

public class ActionController {
	private Authenticator authenticator;
	
	public ActionController() throws Exception {
		authenticator = new Authenticator();
	}
	
	public int insertAction() {
		Scanner cmdInput = new Scanner(System.in);
        System.out.print("Azione scelta: ");
        int input = cmdInput.nextInt();

        return input;
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
					authenticator.actionLogin();
				} catch (Exception e) {
					e = new Exception("Something went wrong during login");
					e.printStackTrace();
				}
				break;
			case Action.REGISTRATION:
				break;
			case Action.LOGOUT:
				break;
			case Action.SEARCH:
				break;
			case Action.SELECT_SONG:
				break;
			case Action.ADD_EMOTION:
				break;
			case Action.ADD_SONG_INTO_PLAYLIST:
				break;
			case Action.ADD_PLAYLIST:
				break;
			case Action.RENAME_PLAYLIST:
				break;
			case Action.DELETE_PLAYLIST:
				break;
			case Action.SELECT_PLAYLIST:
				break;
			case Action.VIEW_ALL_USER_PLAYLIST:
				break;
			case Action.REMOVE_EMOTION:
				break;
			case Action.VIEW_ALL_USER_EMOTION:
				break;
			case Action.SELECT_USER_EMOTION:
				break;
		}
	}
	
}
