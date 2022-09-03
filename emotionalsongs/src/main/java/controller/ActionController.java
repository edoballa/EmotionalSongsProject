package controller;

import java.util.Map;
import java.util.Scanner;

import objects.Action;
import objects.Session;
import objects.Song;
import objects.User;
import services.Authenticator;
import services.SongService;

public class ActionController {
	private Scanner cmdInput;

	private Authenticator authenticator;
	private SongService songService;
	
	public ActionController() throws Exception {
		cmdInput = new Scanner(System.in);
		
		authenticator = new Authenticator();
		songService = new SongService();
		
	}
	
	public int insertAction() {
		String input = "";
        boolean validAction = false;
        
        while(!validAction) {
        	System.out.print("Azione scelta: ");
        	input = cmdInput.nextLine();
        	
        	if(input.length() > 2) {
        		continue;
        	}
	        
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
	
	public boolean checkInsertAction(int actionToCheck, int previusAction, boolean isLoggedIn) {
		int[] possibleActions = Action.possibleActions(previusAction, isLoggedIn);
		
		for(int i = 0; i < possibleActions.length; i++) {
			if(possibleActions[i] == actionToCheck) {
				return true;
			}
		}
		
		return false;
	}
	
	public void doAction(int actionId, Session session) {
		boolean canDoNextAction = false;
		
		switch(actionId) {
			case Action.EXIT:
				break;
			case Action.LOGIN:
				try {
					User user = authenticator.actionLogin(cmdInput);
					if(user != null) {
						session.setUser(user);
						canDoNextAction = true;
						System.out.println("Login effettuata correttamente!");
					} else System.out.println("La login non è stata effettuata correttamente, si prega di riprovare.");
				} catch (Exception e) {
					e = new Exception("Something went wrong during login");
					e.printStackTrace();
				}
				break;
			case Action.LOGOUT:
				session.setUser(authenticator.actionLogout());
				canDoNextAction = true;
				break;
			case Action.REGISTRATION:
				try {
					User user = authenticator.actionRegisterUser(cmdInput);
					
					if(user != null) {
						session.setUser(user);
						canDoNextAction = true;
						System.out.println("Registrazione effettuata correttamente!" );
					}
					
				} catch (Exception e) {
					e = new Exception("Something went wrong during registration");
					e.printStackTrace();
				}
				break;
			case Action.SEARCH:
				String stringToSearch = songService.getStringToSearch(cmdInput);
				Map<Long, Song> result = songService.searchSong(stringToSearch);
				songService.showResult(result);		
				canDoNextAction = true;
				break;
			case Action.SELECT_SONG:
				Long songId = songService.selectSong(cmdInput);
				songService.printSongDetails(songId);
				canDoNextAction = true;
				break;
			
		}
		
		if(canDoNextAction) {
			session.setPreviusAction(session.getCurrentAction());
		}
		session.setCurrentAction(Action.NO_ACTION);
		
	}
	
}
