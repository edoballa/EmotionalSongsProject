package objects;

public class Session {
	
	private User user;
	private int previusAction;
	private int currentAction;
	private int actionDetails;
	
	public Session() {
		this.currentAction = Action.START_PROGRAM;
	}
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getPreviusAction() {
		return previusAction;
	}
	
	public int getCurrentAction() {
		return currentAction;
	}
	
	public void setCurrentAction(int currentAction) {
		this.previusAction = this.currentAction;
		this.currentAction = currentAction;
	}


	public int getActionDetails() {
		return actionDetails;
	}


	public void setActionDetails(int actionDetails) {
		this.actionDetails = actionDetails;
	}
	
	

}
