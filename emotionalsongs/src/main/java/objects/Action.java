/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package objects;

public class Action {
	/**
	 * <code>EXIT</code>
	 * An integer with a value of -1, that representing the user's action </strong>exit</strong>.
	 */
	public static final int EXIT = -1;
	/**
	 * <code>NO_ACTION</code>
	 * An integer with a value of -2, that representing the user's action </strong>no action</strong>.
	 */
	public static final int NO_ACTION = -2;
	/**
	 * <code>BACK</code>
	 * An integer with a value of -3, that representing the user's action </strong>no action</strong>.
	 */
	public static final int BACK = -3;
	/**
	 * <code>START_PROGRAM</code>
	 * An integer with a value of 0, that representing the user's action </strong>start program</strong>.
	 */
	public static final int START_PROGRAM = 0;
	/**
	 * <code>LOGIN</code>
	 * An integer with a value of 1, that representing the user's action </strong>login</strong>.
	 */
	public static final int LOGIN = 1;
	/**
	 * <code>REGISTRATION</code>
	 * An integer with a value of 2, that representing the user's action </strong>registration</strong>.
	 */
	public static final int REGISTRATION = 2;
	/**
	 * <code>LOGOUT</code>
	 * An integer with a value of 3, that representing the user's action </strong>logout</strong>.
	 */
	public static final int LOGOUT = 3;	
	/**
	 * <code>SEARCH</code>
	 * An integer with a value of 4, that representing the user's action </strong>search</strong>.
	 */
	public static final int SEARCH = 4;
	/**
	 * <code>SELECT_SONG</code>
	 * An integer with a value of 5, that representing the user's action </strong>select song</strong>.
	 */
	public static final int SELECT_SONG = 5;
	/**
	 * <code>ADD_EMOTION</code>
	 * An integer with a value of 6, that representing the user's action </strong>add emotion</strong>.
	 */
	public static final int ADD_EMOTION = 6;
	/**
	 * <code>REMOVE_EMOTION</code>
	 * An integer with a value of 7, that representing the user's action </strong>remove emotion</strong>.
	 */
	public static final int REMOVE_EMOTION = 7;
	/**
	 * <code>VIEW_ALL_USER_EMOTION</code>
	 * An integer with a value of 14, that representing the user's action </strong>view all user emotion</strong>.
	 */
	public static final int VIEW_ALL_USER_EMOTION = 14;
	/**
	 * <code>UPDATE_USER_EMOTION</code>
	 * An integer with a value of 15, that representing the user's action </strong>update user emotion</strong>.
	 */
	public static final int UPDATE_USER_EMOTION = 15;
	/**
	 * <code>ADD_PLAYLIST</code>
	 * An integer with a value of 8, that representing the user's action </strong>add playlist</strong>.
	 */
	public static final int ADD_PLAYLIST = 8;
	/**
	 * <code>RENAME_PLAYLIST</code>
	 * An integer with a value of 9, that representing the user's action </strong>rename playlist</strong>.
	 */
	public static final int RENAME_PLAYLIST = 9;
	/**
	 * <code>DELETE_PLAYLIST</code>
	 * An integer with a value of 10, that representing the user's action </strong>delete playlist</strong>.
	 */
	public static final int DELETE_PLAYLIST = 10;
	/**
	 * <code>UPDATE_PLAYLIST_SONG</code>
	 * An integer with a value of 11, that representing the user's action </strong>update playlist song</strong>.
	 */
	public static final int UPDATE_PLAYLIST_SONG = 11;
	/**
	 * <code>VIEW_ALL_USER_PLAYLIST</code>
	 * An integer with a value of 12, that representing the user's action </strong>view all user playlist</strong>.
	 */
	public static final int VIEW_ALL_USER_PLAYLIST = 12;
	/**
	 * <code>VIEW_PLAYLIST_DETAILS</code>
	 * An integer with a value of 16, that representing the user's action </strong>update emotion comment</strong>.
	 */
	public static final int VIEW_PLAYLIST_DETAILS = 16;
	
	//public static final int NUMBER_OF_ACTION = 17;
	
	/**
	 * This method return the description of an action based on its id.
	 * 
	 * @param <actionId> The action's id.
	 * @return String the description of the action.
	 */
	public static String getActionDescription(int actionId) {
		switch(actionId) {
			case EXIT:
				return "Termina il programma";
			case BACK:
				return "Torna indietro";
			case LOGIN:
				return "Login";
			case REGISTRATION:
				return "Registrati";
			case LOGOUT:
				return "Logout";
			case SEARCH:
				return "Cerca una canzone";
			case SELECT_SONG:
				return "Visualizza dettagli della canzone";
			case ADD_EMOTION:
				return "Aggiungi un emozione";
			case ADD_PLAYLIST:
				return "Crea una nuova playlist";
			case RENAME_PLAYLIST:
				return "Rinomina la playlist";
			case DELETE_PLAYLIST:
				return "Cancella la playlist";
			case UPDATE_PLAYLIST_SONG:
				return "Crea una nuova playlist";
			case VIEW_ALL_USER_PLAYLIST:
				return "Visualizza tutte le tue playlist";
			case REMOVE_EMOTION:
				return "Cancella un emozione provata";
			case VIEW_ALL_USER_EMOTION:
				return "Visualizza tutte le emozioni inserite";
			case UPDATE_USER_EMOTION:
				return "Aggiorna un emozione inserita";
			case VIEW_PLAYLIST_DETAILS:
				return "Vedi dettagli playlist";
			default:
				return "";
		}
	}
	
	/**
	 * This method returns all possible actions that a user can choose in the state in which it is.
	 * 
	 * @param <previousAction> An integer indicating the user's last action.
	 * @param <login> A boolean that tracks the user is logged in or not.
	 * @return An array of integers that contains all the values ​​associate with the actions that the user can choose.
	 */
	public static int[] possibleActions(int previousAction, boolean login) {
		switch(previousAction) {
			case START_PROGRAM:
				return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case LOGIN:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case REGISTRATION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case LOGOUT:
				return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case BACK:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case SEARCH:
				if(login) {
					return new int[] {EXIT, LOGOUT, SEARCH, SELECT_SONG, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
				} else return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH, SELECT_SONG};
			case SELECT_SONG:
				if(login) {
					return new int[] {EXIT, LOGOUT, SEARCH, ADD_EMOTION, ADD_PLAYLIST, 
							VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
				} else return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case ADD_EMOTION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case ADD_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case VIEW_ALL_USER_PLAYLIST:
				return new int[] {ADD_PLAYLIST, VIEW_PLAYLIST_DETAILS, RENAME_PLAYLIST, DELETE_PLAYLIST, UPDATE_PLAYLIST_SONG, BACK};
			case RENAME_PLAYLIST:
				return new int[] {ADD_PLAYLIST, VIEW_PLAYLIST_DETAILS, RENAME_PLAYLIST, DELETE_PLAYLIST, UPDATE_PLAYLIST_SONG, BACK};
			case DELETE_PLAYLIST:
				return new int[] {ADD_PLAYLIST, VIEW_PLAYLIST_DETAILS, RENAME_PLAYLIST, DELETE_PLAYLIST, UPDATE_PLAYLIST_SONG, BACK};
			case UPDATE_PLAYLIST_SONG:
				return new int[] {ADD_PLAYLIST, VIEW_PLAYLIST_DETAILS, RENAME_PLAYLIST, DELETE_PLAYLIST, UPDATE_PLAYLIST_SONG, BACK};
			case VIEW_PLAYLIST_DETAILS:
				return new int[] {RENAME_PLAYLIST, DELETE_PLAYLIST, UPDATE_PLAYLIST_SONG, BACK};
			case VIEW_ALL_USER_EMOTION:
				return new int[] {UPDATE_USER_EMOTION, REMOVE_EMOTION, BACK};
			case UPDATE_USER_EMOTION:
				return new int[] {UPDATE_USER_EMOTION, REMOVE_EMOTION, BACK};
			case REMOVE_EMOTION:
				return new int[] {UPDATE_USER_EMOTION, REMOVE_EMOTION, BACK};
			default:
					return null;
		}
	}
}
