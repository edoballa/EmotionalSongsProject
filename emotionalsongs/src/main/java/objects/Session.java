package objects;

public class Session {
	
	private User user;
	private boolean isLoggedIn = false;
	private int previusAction;
	private int currentAction;
	private int actionDetails;
	
	public Session() {
		this.previusAction = Action.START_PROGRAM;
		this.user = null;
	}
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
		if(user != null) {
			this.isLoggedIn = true;
		} else this.isLoggedIn = false;
	}
	
	public int getPreviusAction() {
		return previusAction;
	}
	
	public int getCurrentAction() {
		return currentAction;
	}
	
	public void setPreviusAction(int previusAction) {
		this.previusAction = previusAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public int getActionDetails() {
		return actionDetails;
	}

	public void setActionDetails(int actionDetails) {
		this.actionDetails = actionDetails;
	}
	
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}

}
