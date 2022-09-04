package services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import objects.Playlist;
import objects.Song;
import persistence.Playlist_Factory;

public class PlaylistService {
	private Playlist_Factory playlistFactory;
	
	public PlaylistService() throws Exception {
		playlistFactory = Playlist_Factory.getIstance();
    }
    
   public Playlist addPlaylist(String playlistName, Long userId) throws IOException, Exception {
		Playlist playlist = new Playlist();
		playlist.setName(playlistName);
		playlist.setPublic(false);
		playlist.setUserId(userId);
		
		playlist.setPlaylistId(playlistFactory.getNextKey());
		playlistFactory.create(playlist);
		return playlistFactory.getById(playlist.getPlaylistId());		
	}
	
	public Playlist addSongToPlaylist(String playlistName, Song song, Long userId) throws IOException, Exception {
		Playlist playlist = playlistFactory.getByName(playlistName, userId);
		playlist.getSongs().add(song);
		
		playlistFactory.update(playlist);
		playlist = playlistFactory.getById(playlist.getPlaylistId());
		
		return playlist;
	}
	
	public void addSongsToPlaylist(Playlist playlist) throws IOException, Exception {
		playlistFactory.update(playlist);

	}
	
	public Playlist removeSongFromPlaylist(String playlistName, Song song, Long userId) throws IOException, Exception {
		Playlist playlist = playlistFactory.getByName(playlistName, userId);
		playlist.getSongs().remove(song);
		
		playlistFactory.update(playlist);
		playlist = playlistFactory.getById(playlist.getPlaylistId());
		
		return playlist;
	}
	
	public void deletePlaylist(Playlist playlist) throws Exception {
		playlistFactory.delete(playlist);
	}
	
	public Playlist updatePlaylistName(String newName, Playlist playlist) throws IOException, Exception {
		playlist.setName(newName);
		playlistFactory.update(playlist);
		
		return playlist;
	}
	
	public String insertPlaylistData(Scanner cmdInput) {
		String playlistName = null;
		do {
			System.out.print("Inserisci il nome della playlist: ");
			playlistName = cmdInput.nextLine();
		} while(playlistName.isEmpty() || playlistName == null);
		
		return playlistName;
	}
	
	public Map<Long, Playlist> getUserPlaylist(Long userId) {	
		return playlistFactory.getUserPlaylist(userId);
	}
	
	public void printUserPlaylist(Map<Long, Playlist> pl) {
		for(Playlist p : pl.values()) {
			System.out.println(p.getPlaylistId() + " - " + p.getName());
		}
	}
	
	public Long selectPlaylist(Scanner cmdInput, Map<Long, Playlist> playlistMap) {
		boolean validValue = false;
		boolean validId = false;
		String playlistId;
		
		do {
			do {
				System.out.print("Canzone da selezionare: ");
				playlistId = cmdInput.nextLine();
				
				char[] chars = new char[playlistId.length()];
				playlistId.getChars(0, playlistId.length(), chars, 0);
	        	
		        for(int i = 0; i < chars.length; i++) {
		        	switch(chars[i]) {
		        	case '0':
						validValue = true;
						break;
					case '1':
						validValue = true;
						break;
					case '2':
						validValue = true;
						break;
					case '3':
						validValue = true;
						break;
					case '4':
						validValue = true;
						break;
					case '5':
						validValue = true;
						break;
					case '6':
						validValue = true;
						break;
					case '7':
						validValue = true;
						break;
					case '8':
						validValue = true;
						break;
					case '9':
						validValue = true;
						break;
					default:
		        		System.out.println("Il valore inserito non è valido, scegliere nuovamente.");
		        		validValue = false;
		        		break;
		        	}
		        }
				
			}while(!validValue);
			
			if(playlistMap.containsKey(Long.valueOf(playlistId))) {
				validId = true;
			}
			
		}while(!validId);
		
		return Long.valueOf(playlistId);
	} 
	
}
