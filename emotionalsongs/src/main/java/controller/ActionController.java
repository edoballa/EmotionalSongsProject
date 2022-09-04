package controller;

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
	
	private boolean logout(Session session) {
		session.setUser(authenticator.actionLogout());
		return true;
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
	
	private boolean inserisciEmozioniBrano(User user) {
		EmotionFelt ef = emotionService.insertEmotionData(cmdInput, true);
		ef.setSongId(songService.getLastSongSelected());
		ef.setUserId(user.getUserId());
		
		try {
			emotionService.insertNewEmotion(ef);
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer new emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean deleteUserEmotion(User user) {
		String emotionFeltCode = emotionService.selectEmotionFelt(cmdInput, user.getEmotionsFelt());
		try {
			emotionService.deleteEmotionFelt(emotionFeltCode);
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean updateUserEmotion(User user) {
		String emotionFeltCode = emotionService.selectEmotionFelt(cmdInput, user.getEmotionsFelt());
		String[] ids = emotionFeltCode.split("_");
		EmotionFelt ef = emotionService.insertEmotionData(cmdInput, false);
		ef.setEmotionFeltId(emotionFeltCode);
		ef.setSongId(Long.valueOf(ids[1]));
		ef.setEmotionId(Long.valueOf(ids[0]));
		ef.setUserId(user.getUserId());
		
		try {
			emotionService.updateEmotionFelt(ef);
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean viewUserEmotion(User user) {
		try {
			user.setEmotionsFelt(emotionService.getUserEmotion(user.getUserId()));
			emotionService.printEmotionList(user.getEmotionsFelt());
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during show user emotion felt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean viewUserPlaylist(User user) {
		user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
		playlistService.printUserPlaylist(user.getPlaylists());
		return true;
	}
	
	private boolean viewPlaylistDetail(Map<Long, Playlist> playlistMap) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, playlistMap);
		playlistService.printPlaylistDetails(playlistMap.get(playListSelected));
		return true;
	}
	
	private boolean deletePlaylist(User user) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, user.getPlaylists());
		try {
			playlistService.deletePlaylist(user.getPlaylists().get(playListSelected));
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during delete playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean updatePlaylistName(User user) {
		Long playListSelected = playlistService.selectPlaylist(cmdInput, user.getPlaylists());
		String newName = playlistService.insertPlaylistData(cmdInput);
		try {
			playlistService.updatePlaylistName(newName, user.getPlaylists().get(playListSelected));
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during update playlist name");
			e.printStackTrace();
		}
		
		return false;
	}
	
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
			
			if(songToSearch == "EXIT") {
				end = true;
				continue;
			}
			
			Map<Long, Song> res = songService.searchSong(songToSearch);
			songService.showResult(res);
			Long songSelected = songService.selectSong(cmdInput);
			Song s = songService.getSongById(songSelected);
			p.getSongs().put(s.getSongId(), s);
		}
		
		try {
			playlistService.updatePlaylist(p);
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during update playlist name");
			e.printStackTrace();
		}
		
		return false;
	}
	
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
			
			if(songToSearch == "EXIT") {
				end = true;
				continue;
			}
			
			Map<Long, Song> res = songService.searchSong(songToSearch);
			songService.showResult(res);
			Long songSelected = songService.selectSong(cmdInput);
			Song s = songService.getSongById(songSelected);
			p.getSongs().put(s.getSongId(), s);
		}
		
		try {
			playlistService.addSongsToPlaylist(p);
			user.setPlaylists(playlistService.getUserPlaylist(user.getUserId()));
			return true;
		} catch (Exception e) {
			e = new Exception("Something went wrong during registrer the songs playlist");
			e.printStackTrace();
		}
		
		return false;
	}
	
}
