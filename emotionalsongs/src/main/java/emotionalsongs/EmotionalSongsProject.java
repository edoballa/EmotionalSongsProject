package emotionalsongs;

import controller.ActionController;
import objects.Action;
import objects.Session;

/**
* <p>This class contains the main</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class EmotionalSongsProject {
	public static void main(String[] args) throws Exception{
		int action = Action.START_PROGRAM;
		System.out.println("Starting program...");
		
		Session session = new Session();
		ActionController actionController = new ActionController();
		
		while(session.getCurrentAction() != Action.EXIT) {

			actionController.printActionList(session.getPreviousAction(), session.getIsLoggedIn());
			action = actionController.insertAction();
			
			if(action == Action.EXIT) {
				session.setCurrentAction(action);
				continue;
			} else if((action < Action.EXIT || action > Action.VIEW_PLAYLIST_DETAILS) && action != Action.BACK) {
				continue;
			} else if(!actionController.checkInsertAction(action, session.getPreviousAction(), session.getIsLoggedIn())) {
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
