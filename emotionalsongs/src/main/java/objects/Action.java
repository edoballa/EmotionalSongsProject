package objects;

public class Action {
	public static final int EXIT = -1;
	public static final int HOME = -2;
	public static final int START_PROGRAM = 0;

	public static final int LOGIN = 1;
	public static final int REGISTRATION = 2;
	public static final int LOGOUT = 3;
	
	public static final int SEARCH = 4;
	public static final int SELECT_SONG = 5;
	public static final int ADD_EMOTION = 6;
	public static final int REMOVE_EMOTION = 13;
	public static final int VIEW_ALL_USER_EMOTION = 14;
	public static final int SELECT_USER_EMOTION = 15;
	
	public static final int ADD_SONG_INTO_PLAYLIST = 7;
	public static final int ADD_PLAYLIST = 8;
	public static final int RENAME_PLAYLIST = 9;
	public static final int DELETE_PLAYLIST = 10;
	public static final int SELECT_PLAYLIST = 11;
	public static final int VIEW_ALL_USER_PLAYLIST = 12;
	
	//public static final int NUMBER_OF_ACTION = 17;
	
	public static String getActionDescription(int actionId) {
		switch(actionId) {
			case EXIT:
				return "Termina il programma";
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
			case ADD_SONG_INTO_PLAYLIST:
				return "Aggiungi canzone all'interno della playlist";
			case ADD_PLAYLIST:
				return "Crea una nuova playlist";
			case RENAME_PLAYLIST:
				return "Rinomina la playlist";
			case DELETE_PLAYLIST:
				return "Cancella la playlist";
			case SELECT_PLAYLIST:
				return "Crea una nuova playlist";
			case VIEW_ALL_USER_PLAYLIST:
				return "Visualizza tutte le tue playlist";
			case REMOVE_EMOTION:
				return "Visualizza tutte le tue playlist";
			case VIEW_ALL_USER_EMOTION:
				return "Visualizza tutte le tue playlist";
			case SELECT_USER_EMOTION:
				return "Visualizza tutte le tue playlist";
			default:
				return "Scelta non valida";
		}
	}
	
	public static int[] possibleActions(int previusAction, boolean login) {
		/*return new int[] {EXIT, START_PROGRAM, LOGIN, REGISTRATION, LOGOUT, SEARCH, SELECT_SONG, ADD_EMOTION,
				ADD_SONG_INTO_PLAYLIST, ADD_PLAYLIST, RENAME_PLAYLIST, DELETE_PLAYLIST, SELECT_PLAYLIST, VIEW_ALL_USER_PLAYLIST,
				REMOVE_EMOTION, VIEW_ALL_USER_EMOTION, SELECT_USER_EMOTION};*/
		
		switch(previusAction) {
			case START_PROGRAM:
				return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case LOGIN:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case REGISTRATION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case LOGOUT:
				return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case SEARCH:
				if(login) {
					return new int[] {EXIT, LOGOUT, SEARCH, SELECT_SONG, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
				} else return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH, SELECT_SONG};
			case SELECT_SONG:
				if(login) {
					return new int[] {EXIT, LOGOUT, SEARCH, ADD_EMOTION, ADD_SONG_INTO_PLAYLIST, ADD_PLAYLIST, 
							VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
				} else return new int[] {EXIT, LOGIN, REGISTRATION, SEARCH};
			case ADD_EMOTION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case ADD_SONG_INTO_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case ADD_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case RENAME_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case DELETE_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case SELECT_PLAYLIST:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, RENAME_PLAYLIST, DELETE_PLAYLIST, VIEW_ALL_USER_PLAYLIST,
						VIEW_ALL_USER_EMOTION};
			case REMOVE_EMOTION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION};
			case VIEW_ALL_USER_EMOTION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, VIEW_ALL_USER_EMOTION, SELECT_USER_EMOTION};
			case SELECT_USER_EMOTION:
				return new int[] {EXIT, LOGOUT, SEARCH, ADD_PLAYLIST, VIEW_ALL_USER_PLAYLIST, REMOVE_EMOTION, VIEW_ALL_USER_EMOTION};
			default:
				return null;
		}
	}
}
