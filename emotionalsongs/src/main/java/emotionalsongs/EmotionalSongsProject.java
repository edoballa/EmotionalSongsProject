package emotionalsongs;

import controller.ActionController;
import objects.Action;
import objects.Session;

public class EmotionalSongsProject {
	public static void main(String[] args) throws Exception{
		int action = Action.START_PROGRAM;
		System.out.println("Starting program...");
		
		Session session = new Session();
		ActionController actionController = new ActionController();
		
		while(session.getCurrentAction() != Action.EXIT) {

			actionController.printActionList(session.getPreviusAction(), session.getIsLoggedIn());
			action = actionController.insertAction();
			
			if(action < Action.EXIT || action > Action.UPDATE_EMOTION_COMMENT) {
				continue;
			} else if(!actionController.checkInsertAction(action, session.getPreviusAction(), session.getIsLoggedIn())) {
				continue;
			}
			
			session.setCurrentAction(action);
			actionController.doAction(action, session);
			
			//first i destroy the current session and i make a new one
			if(action == Action.LOGOUT) {
				session = null;
				session = new Session();
			}
		}

		System.out.println("Close program...");
	}
}