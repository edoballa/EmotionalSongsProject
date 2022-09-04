package controller;

import java.io.IOException;
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

public class ActionController {
	private Scanner cmdInput;

	private Authenticator authenticator;
	private SongService songService;
	private EmotionService emotionService;
	private PlaylistService playlistService;
	
	public ActionController() throws Exception {
		cmdInput = new Scanner(System.in);
		
		authenticator = new Authenticator();
		songService = new SongService();
		emotionService = new EmotionService();
		playlistService = new PlaylistService();
		
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

        return Integer.valueOf(input);
	}
	
	public void printActionList(int actionId, boolean isLoggedIn) {
		int[] possibleActions = Action.possibleActions(actionId, isLoggedIn);
		
		System.out.println("-----------------------------------------------------------------------------------------------");
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
				canDoNextAction = true;
				break;
			case Action.LOGIN:
				canDoNextAction = login(session);
				break;
			case Action.LOGOUT:
				session.setUser(authenticator.actionLogout());
				canDoNextAction = true;
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
				canDoNextAction = inserisciEmozioniBrano(session);
				break;
			case Action.ADD_PLAYLIST:
				canDoNextAction = registraPlaylist(session);
				break;
			case Action.VIEW_ALL_USER_PLAYLIST:
				session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
				playlistService.printUserPlaylist(session.getUser().getPlaylists());
				break;
			case Action.RENAME_PLAYLIST:
				canDoNextAction = updatePlaylistName(session);
				break;
			case Action.DELETE_PLAYLIST:
				canDoNextAction = deletePlaylist(session);
				break;
				
		}
		
		if(canDoNextAction) {
			session.setPreviousAction(session.getCurrentAction());
		}
		session.setCurrentAction(Action.NO_ACTION);
		
	}
	
	private boolean login(Session session) {
		try {
			User user = authenticator.actionLogin(cmdInput);
			if(user != null) {
				session.setUser(user);
				session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
				System.out.println("Login effettuata correttamente!");
				return true;
			} else System.out.println("La login non è stata effettuata correttamente, si prega di riprovare.");
		} catch (Exception e) {
			e = new Exception("Something went wrong during login");
			e.printStackTrace();
		}
		
		return false;
	}
	
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
	
	private boolean visualizzaEmozioneBrano() {
		Long songId = songService.selectSong(cmdInput);
		songService.printSongDetails(songId);
		return true;
	}
	
	private boolean cercaBranoMusicale() {
		String stringToSearch = songService.getStringToSearch(cmdInput);
		Map<Long, Song> result = songService.searchSong(stringToSearch);
		songService.showResult(result);		
		return true;
	}
	
	private boolean inserisciEmozioniBrano(Session session) {
		EmotionFelt ef = emotionService.insertEmotionData(cmdInput);
		ef.setSongId(songService.getLastSongSelected());
		ef.setUserId(session.getUser().getUserId());
		
		try {
			emotionService.insertNewEmotion(ef);
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer new emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean deletePlaylist(Session session) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, session.getUser().getPlaylists());
		try {
			playlistService.deletePlaylist(session.getUser().getPlaylists().get(playListSelected));
			session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean updatePlaylistName(Session session) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, session.getUser().getPlaylists());
		String newName = playlistService.insertPlaylistData(cmdInput);
		try {
			playlistService.updatePlaylistName(newName, session.getUser().getPlaylists().get(playListSelected));
			session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during update playlist name");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean registraPlaylist(Session session) {
		Playlist p = new Playlist();
		String playlistName = playlistService.insertPlaylistData(cmdInput);
		
		try {
			p = playlistService.addPlaylist(playlistName, session.getUser().getUserId());
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer the new playlist");
			e.printStackTrace();
		}

		boolean end = false;
		while(!end){
			System.out.println("Per uscire dalla ricerca inserire EXIT");
			String songToSearch = songService.getStringToSearch(cmdInput);
			
			if(songToSearch == "EXIT") {
				end = true;
				continue;
			}
			
			Map<Long, Song> res = songService.searchSong(songToSearch);
			songService.showResult(res);
			Long songSelected = songService.selectSong(cmdInput);
			Song s = songService.getSongById(songSelected);
			p.getSongs().add(s);
		}
		
		try {
			playlistService.addSongsToPlaylist(p);
			session.getUser().setPlaylists(playlistService.getUserPlaylist(session.getUser().getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer the songs playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
}
