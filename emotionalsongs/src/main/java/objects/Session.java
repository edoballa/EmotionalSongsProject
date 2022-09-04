/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package objects;

public class Session {
	/**
	 * <code>user</code>
	 * An User object to store the user data.
	 */
	private User user;
	/**
	 * <code>isLoggedIn</code>
	 * A boolean to control if a user is logged in or not.
	 */
	private boolean isLoggedIn = false;
	/**
	 * <code>previousAction</code>
	 * An integer to keep track of the value corresponding to the previous action.
	 */
	private int previousAction;
	/**
	 * <code>currentAction</code>
	 * An integer to keep track of the value corresponding to the current action.
	 */
	private int currentAction;
	
	/**
     * Session default constructor.
     */
	public Session() {
		this.previousAction = Action.START_PROGRAM;
		this.user = null;
	}
	
	/**
     * This method return the user object.
     * @return User the object user.
     */
	public User getUser() {
		return user;
	}
	
	/**
     * user setter method.
     * @param <user> The user to set.
     */
	public void setUser(User user) {
		this.user = user;
		if(user != null) {
			this.isLoggedIn = true;
		} else this.isLoggedIn = false;
	}
	
	/**
     * This method return the previousAction field.
     * @return integer the user's previous action.
     */
	public int getPreviousAction() {
		return previousAction;
	}
	
	/**
     * This method return the currentAction field.
     * @return integer the user's current action.
     */
	public int getCurrentAction() {
		return currentAction;
	}
	
	/**
     * previousAction setter method.
     * @param <previousAction> The previousAction to set.
     */
	public void setPreviousAction(int previousAction) {
		this.previousAction = previousAction;
	}

	/**
     * currentAction setter method.
     * @param <currentAction> The currentAction to set.
     */
	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}
	
	/**
     * This method return the isLoggedIn field.
     * @return boolean <strong>true</strong> if the user is logged in, <strong>false</strong> if the user isn't logged in.
     */
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
}
