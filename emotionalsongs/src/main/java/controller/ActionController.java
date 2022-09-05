/**
* <p>This package manage the actions calling the correct method in the correct service.</p>
*/
package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import objects.Action;
import objects.EmotionFelt;
import objects.Playlist;
import objects.Session;
import objects.Song;
import objects.User;
import services.Authenticator;
import services.EmotionService;
import services.PlaylistService;
import services.SongService;

/**
* <p>This class manage the actions calling the correct method in the correct service.</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class ActionController {
	/**
	 * <code>cmdInput</code>
	 * A Scanner object that store the inputs written by the prompt.
	 */
	private Scanner cmdInput;
	/**
	 * <code>authenticator</code>
	 * An authenticator object.
	 */
	private Authenticator authenticator;
	/**
	 * <code>songService</code>
	 * A songService object.
	 */
	private SongService songService;
	/**
	 * <code>emotionService</code>
	 * A emotionService object.
	 */
	private EmotionService emotionService;
	/**
	 * <code>playlistService</code>
	 * A playlistService object.
	 */
	private PlaylistService playlistService;
	
	/**
	 * ActionController default constructor.
	 * 
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public ActionController() throws Exception {
		cmdInput = new Scanner(System.in);
		
		authenticator = new Authenticator();
		songService = new SongService();
		emotionService = new EmotionService();
		playlistService = new PlaylistService();
		
	}
	
	/**
	 * This method allows the user to insert an action via the command line.
	 * 
	 * @return an Integer object holding the value of the input String.
	 */
	public int insertAction() {
		String input = "";
        boolean validAction = false;
        
        while(!validAction) {
        	System.out.print("Azione scelta: ");
        	input = cmdInput.nextLine();
        	
        	if(input.length() > 2 || input.isBlank()) {
        		continue;
        	}
	        
        	char[] chars = new char[input.length()];
        	input.getChars(0, input.length(), chars, 0);
        	
	        for(int i = 0; i < chars.length; i++) {
	        	switch(chars[i]) {
	        	case '-':
					validAction = true;
					break;
	        	case '0':
					validAction = true;
					break;
				case '1':
					validAction = true;
					break;
				case '2':
					validAction = true;
					break;
				case '3':
					validAction = true;
					break;
				case '4':
					validAction = true;
					break;
				case '5':
					validAction = true;
					break;
				case '6':
					validAction = true;
					break;
				case '7':
					validAction = true;
					break;
				case '8':
					validAction = true;
					break;
				case '9':
					validAction = true;
					break;
				default:
	        		System.out.println("I caratteri inseriti non sono validi. Scegliere nuovamente.");
	        		validAction = false;
	        		break;
	        	}
	        }
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        return Integer.valueOf(input);
	}
	
	/**
	 * This method prints an array of all possible actions a user can choose from where it is.
	 * 
	 * @param actionId The id of the action.
	 * @param isLoggedIn A boolean representing if the user is logged in or not.
	 */
	public void printActionList(int actionId, boolean isLoggedIn) {
		int[] possibleActions = Action.possibleActions(actionId, isLoggedIn);
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Per effettuare un azione inserire il numero ad essa corrispondente, le azioni eseguibili sono :");
		for(int i = 0; i < possibleActions.length; i++) {
			System.out.println("(" + possibleActions[i] + ") -> " + Action.getActionDescription(possibleActions[i]));
		}
	}
	
	/**
	 * This method checks that the action choose by the user is part of the actions that the user could choose from.
	 * 
	 * @param actionToCheck The action choose by the user.
	 * @param previousAction The action previously chosen by the user.
	 * @param isLoggedIn A boolean representing if the user is logged in or not.
	 * @return True value, if the actionToCheck is in the array of possible action.
	 */
	public boolean checkInsertAction(int actionToCheck, int previousAction, boolean isLoggedIn) {
		int[] possibleActions = Action.possibleActions(previousAction, isLoggedIn);
		
		for(int i = 0; i < possibleActions.length; i++) {
			if(possibleActions[i] == actionToCheck) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method performs the action choose by the user by making eventual checks.
	 * 
	 * @param actionId The id of the action.
	 * @param session The Session object.
	 */
	public void doAction(int actionId, Session session) {
		boolean canDoNextAction = false;
		
		switch(actionId) {
			case Action.EXIT:
				canDoNextAction = true;
				break;
			case Action.BACK:
				canDoNextAction = true;
				break;
			case Action.LOGIN:
				canDoNextAction = login(session);
				break;
			case Action.LOGOUT:
				canDoNextAction = logout(session);
				break;
			case Action.REGISTRATION:
				canDoNextAction = registrazione(session);
				break;
			case Action.SEARCH:
				canDoNextAction = cercaBranoMusicale();
				break;
			case Action.SELECT_SONG:
				canDoNextAction = visualizzaEmozioneBrano();
				break;
			case Action.ADD_EMOTION:
				canDoNextAction = inserisciEmozioniBrano(session.getUser());
				break;
			case Action.ADD_PLAYLIST:
				canDoNextAction = registraPlaylist(session.getUser());
				break;
			case Action.VIEW_ALL_USER_PLAYLIST:
				canDoNextAction = viewUserPlaylist(session.getUser());
				break;
			case Action.RENAME_PLAYLIST:
				canDoNextAction = updatePlaylistName(session.getUser());
				break;
			case Action.DELETE_PLAYLIST:
				canDoNextAction = deletePlaylist(session.getUser());
				break;
			case Action.VIEW_PLAYLIST_DETAILS:
				canDoNextAction = viewPlaylistDetail(session.getUser().getPlaylists());
				break;
			case Action.VIEW_ALL_USER_EMOTION:
				canDoNextAction = viewUserEmotion(session.getUser());
				break;
			case Action.REMOVE_EMOTION:
				canDoNextAction = deleteUserEmotion(session.getUser());
				break;
			case Action.UPDATE_USER_EMOTION:
				canDoNextAction = updateUserEmotion(session.getUser());
				break;
			case Action.UPDATE_PLAYLIST_SONG:
				canDoNextAction = updatePlaylistSongs(session.getUser());
				break;
		}
		
		if(canDoNextAction) {
			if(session.getIsLoggedIn() && session.getUser().getPlaylists().isEmpty() && session.getCurrentAction() == Action.DELETE_PLAYLIST) {
				session.setPreviousAction(Action.BACK);
			} else if(session.getIsLoggedIn() && session.getUser().getEmotionsFelt().isEmpty() && session.getCurrentAction() == Action.REMOVE_EMOTION) {
				session.setPreviousAction(Action.BACK);
			} else session.setPreviousAction(session.getCurrentAction());
		}
		session.setCurrentAction(Action.NO_ACTION);
		
	}
	
	/**
	 * Method for logging in a user.
	 * 
	 * @param session The Session object.
	 * @return True, if the user is correctly logged in.
	 */
	private boolean login(Session session) {
		try {
			User user = authenticator.actionLogin(cmdInput);
			if(user != null) {
				session.setUser(user);
				session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
				session.getUser().setEmotionsFelt(emotionService.getUserEmotion(session.getUser().getUserId()));
				System.out.println("Login effettuata correttamente!");
				return true;
			} else System.out.println("La login non è stata effettuata correttamente, si prega di riprovare.");
		} catch (Exception e) {
			e = new Exception("Something went wrong during login");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method for logging out a user.
	 * 
	 * @param session The Session object.
	 * @return True, if the user is correctly logged out.
	 */
	private boolean logout(Session session) {
		session.setUser(authenticator.actionLogout());
		System.out.println("Logout eseguito con successo!");
		return true;
	}
	
	/**
	 * Method for registering a user.
	 * 
	 * @param session The Session object.
	 * @return True, if the registration happens with no error.
	 */
	private boolean registrazione(Session session) {
		try {
			User user = authenticator.actionRegisterUser(cmdInput);
			
			if(user != null) {
				session.setUser(user);
				System.out.println("Registrazione effettuata correttamente!" );
				return true;
			}
			
		} catch (Exception e) {
			e = new Exception("Something went wrong during registration");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to visualize all the emotions felt of a song.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean visualizzaEmozioneBrano() {
		Long songId = songService.selectSong(cmdInput);
		songService.printSongDetails(songId);
		return true;
	}
	
	/**
	 * Method to search a song.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean cercaBranoMusicale() {
		System.out.println("------- RISULTATI RICERCA ---------------------------------------------------------------------");
		String stringToSearch = songService.getStringToSearch(cmdInput);
		Map<Long, Song> result = songService.searchSong(stringToSearch);
		if(!result.isEmpty()) {
			songService.showResult(result);		
		} else {
			System.out.println("La ricerca non ha dato risultati");
			return false;
		}
		return true;
	}
	
	/**
	 * Method to insert an emotion felt while listening to a song.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean inserisciEmozioniBrano(User user) {
		Long songId = songService.getLastSongSelected();
		Map<String, EmotionFelt> emotionByUserAndSong = emotionService.getSongUserEmotion(user.getUserId(), songId);
		EmotionFelt ef = emotionService.insertEmotionData(cmdInput, true, songId, emotionByUserAndSong);
		ef.setSongId(songId);
		ef.setUserId(user.getUserId());
		
		try {
			emotionService.insertNewEmotion(ef);
			System.out.println("Emozione inserita correttamente!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer new emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to delete an emotion felt by the user.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean deleteUserEmotion(User user) {
		String emotionFeltCode = emotionService.selectEmotionFelt(cmdInput, user.getEmotionsFelt());
		try {
			emotionService.deleteEmotionFelt(emotionFeltCode);
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			System.out.println("Emozione cancellata con successo!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to update an emotion felt by the user.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean updateUserEmotion(User user) {
		String emotionFeltCode = emotionService.selectEmotionFelt(cmdInput, user.getEmotionsFelt());
		String[] ids = emotionFeltCode.split("_");
		EmotionFelt ef = emotionService.insertEmotionData(cmdInput, false, null, new HashMap<String, EmotionFelt>());
		ef.setEmotionFeltId(emotionFeltCode);
		ef.setSongId(Long.valueOf(ids[1]));
		ef.setEmotionId(Long.valueOf(ids[0]));
		ef.setUserId(user.getUserId());
		
		try {
			emotionService.updateEmotionFelt(ef);
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			System.out.println("Emozione aggiornata correttamente!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to show all the emotion insert by a user.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean viewUserEmotion(User user) {
		try {
			System.out.println("------- ELENCO EMOZIONI INSERITE --------------------------------------------------------------");
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			if(!user.getEmotionsFelt().isEmpty()) {
				emotionService.printEmotionList(user.getEmotionsFelt());
			} else {
				System.out.println("L'utente non ha inserito nessuna emozione");
				return false;
			}
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during show user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to show all the playlist create by a user.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean viewUserPlaylist(User user) {
		System.out.println("------- ELENCO PLAYLIST -----------------------------------------------------------------------");
		user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
		if(!user.getPlaylists().isEmpty()) {
			playlistService.printUserPlaylist(user.getPlaylists());
		} else {
			System.out.println("L'utente non ha nessuna playlist");
			return false;
		}
		return true;
	}
	
	/**
	 * Method to show all the details about a playlist.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean viewPlaylistDetail(Map<Long, Playlist> playlistMap) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, playlistMap);
		playlistService.printPlaylistDetails(playlistMap.get(playListSelected));
		return true;
	}
	
	/**
	 * Method to delete a playlist.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean deletePlaylist(User user) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, user.getPlaylists());
		try {
			playlistService.deletePlaylist(user.getPlaylists().get(playListSelected));
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			System.out.println("Playlist cancellata con successo!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to update a name of a playlist.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean updatePlaylistName(User user) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, user.getPlaylists());
		String newName = playlistService.insertPlaylistData(cmdInput);
		try {
			playlistService.updatePlaylistName(newName, user.getPlaylists().get(playListSelected));
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			System.out.println("Playlist aggiornata con successo!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during update playlist name");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to update the songs that belongs to the playlist.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean updatePlaylistSongs(User user) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, user.getPlaylists());
		Playlist p = user.getPlaylists().get(playListSelected);
		
		boolean end = false;
		while(!end){
			System.out.println("Inserire il codice della canzone da rimuore dalla playlist, per terminare la cancellazione inserire EXIT");
			Long songToDelete = playlistService.selectSongIntoPlaylist(cmdInput, p.getSongs());
			
			if(songToDelete == null) {
				end = true;
				continue;
			}
			
			p.getSongs().remove(songToDelete);			
		}
		
		end = false;
		while(!end){
			System.out.println("Ricerca di canzoni da aggiungere alla playlist. Per uscire dalla ricerca inserire EXIT");
			String songToSearch = songService.getStringToSearch(cmdInput);
			
			if(songToSearch.equals("EXIT")) {
				end = true;
				continue;
			}
			
			Map<Long, Song> res = songService.searchSong(songToSearch);
			if(!res.isEmpty()) {
				songService.showResult(res);		
			} else {
				System.out.println("La ricerca non ha dato risultati");
				continue;
			}
			
			Long songSelected = songService.selectSong(cmdInput);
			Song s = songService.getSongById(songSelected);
			p.getSongs().put(s.getSongId(), s);
		}
		
		try {
			playlistService.updatePlaylist(p);
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			System.out.println("Playlist aggiornata con successo!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during update playlist name");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to create a new playlist.
	 * 
	 * @return True, if the operations have been carried out correctly. False, if something went wrong.
	 */
	private boolean registraPlaylist(User user) {
		Playlist p = new Playlist();
		String playlistName = playlistService.insertPlaylistData(cmdInput);
		
		try {
			p = playlistService.addPlaylist(playlistName, user.getUserId());
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer the new playlist");
			e.printStackTrace();
		}

		boolean end = false;
		while(!end){
			System.out.println("Per uscire dalla ricerca inserire EXIT");
			String songToSearch = songService.getStringToSearch(cmdInput);
			
			if(songToSearch.equals("EXIT")) {
				end = true;
				continue;
			}
			
			Map<Long, Song> res = songService.searchSong(songToSearch);
			if(!res.isEmpty()) {
				songService.showResult(res);		
			} else {
				System.out.println("La ricerca non ha dato risultati");
				continue;
			}
			
			Long songSelected = songService.selectSong(cmdInput);
			Song s = songService.getSongById(songSelected);
			p.getSongs().put(s.getSongId(), s);
		}
		
		try {
			playlistService.updatePlaylist(p);
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			System.out.println("Playlist aggiunta con successo!");
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer the songs playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
}
