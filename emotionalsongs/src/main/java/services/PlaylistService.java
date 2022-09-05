/**
* This package contains the busisness logic. 
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package services;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import objects.Playlist;
import objects.Song;
import persistence.Playlist_Factory;

public class PlaylistService {
	/**
	 * <code>playlistFactory</code>
	 * A PlaylistFactory object use to invoke the methods of that class.
	 */
	private Playlist_Factory playlistFactory;
	
	/**
	 *  PlaylistService default constructor.
	 *  
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public PlaylistService() throws Exception {
		playlistFactory = Playlist_Factory.getIstance();
    }
    
	/**
	 * This method creates a new empty playlist naming it with the name passed as a parameter.
	 * 
	 * @param playlistName The name of the playlist.
	 * @param userId The user's id.
	 * @return A Playlist object.
	 * @throws IOException This exception is thrown if there is any input/output error.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public Playlist addPlaylist(String playlistName, Long userId) throws IOException, Exception {
		Playlist playlist = new Playlist();
		playlist.setName(playlistName);
		playlist.setPublic(false);
		playlist.setUserId(userId);
		
		playlist.setPlaylistId(playlistFactory.getNextKey());
		playlistFactory.create(playlist);
		return playlistFactory.getById(playlist.getPlaylistId());		
	}
	
	/**
	 * This method delete a Playlist.
	 * 
	 * @param playlist The Playlist to delete the song.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public void deletePlaylist(Playlist playlist) throws Exception {
		playlistFactory.delete(playlist);
	}
	
	/**
	 * This method replace the name of the playlist.
	 * 
	 * @param newName The new name of the playlist.
	 * @param playlist The playlist to rename.
	 * @return A Playlist object.
	 * @throws IOException This exception is thrown if there is any input/output error.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public Playlist updatePlaylistName(String newName, Playlist playlist) throws IOException, Exception {
		playlist.setName(newName);
		playlistFactory.update(playlist);
		
		return playlist;
	}
	
	/**
	 * This method update the playlist object.
	 * 
	 * @param playlist The playlist to update.
	 * @return A Playlist object.
	 * @throws IOException This exception is thrown if there is any input/output error.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public Playlist updatePlaylist(Playlist playlist) throws IOException, Exception {
		playlistFactory.update(playlist);
		
		return playlist;
	}
	
	/**
	 * This method allows the user to insert from cmd the playlist name.
	 * 
	 * @param cmdInput User's input from cmd.
	 * @return A String with the name of the playlist.
	 */
	public String insertPlaylistData(Scanner cmdInput) {
		String playlistName = null;
		do {
			System.out.print("Inserisci il nome della playlist: ");
			playlistName = cmdInput.nextLine();
		} while(playlistName.isBlank() || playlistName == null);
		
		return playlistName;
	}
	
	/**
	 * This method return a Map with all the playlists of the user.
	 * 
	 * @param userId The user's id.
	 * @return A Map of Playlist.
	 */
	public Map<Long, Playlist> getUserPlaylist(Long userId) {	
		return playlistFactory.getUserPlaylist(userId);
	}
	
	/**
	 * This method print on the console the playlist of the user.
	 * 
	 * @param pl A Map of Playlist.
	 */
	public void printUserPlaylist(Map<Long, Playlist> pl) {
		for(Playlist p : pl.values()) {
			System.out.println(p.getPlaylistId() + " - " + p.getName());
		}
	}
	
	/**
	 * 
	 * @param cmdInput User's input from cmd.
	 * @param playlistMap A Map of Playlist.
	 * @return
	 */
	public Long selectPlaylist(Scanner cmdInput, Map<Long, Playlist> playlistMap) {
		boolean validValue = false;
		boolean validId = false;
		String playlistId;
		
		do {
			do {
				System.out.print("Playlist da selezionare: ");
				playlistId = cmdInput.nextLine();
				
				if(playlistId.isBlank()) {
					System.out.println("Inserire un valore.");
					continue;
				}
				
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
	
	/**
	 * This method print on the console the name of the playlist with all the songs and the authors contained in it.
	 * 
	 * @param p A Playlist object.
	 */
	public void printPlaylistDetails(Playlist p) {
		System.out.println("------- " + p.getName().toUpperCase() + " ------------------------------------------");
		
		for(Song song : p.getSongs().values()) {
			System.out.println(song.getTitle().toUpperCase() + "(" + song.getAuthor() + ")");
		} 
	}
	
	/**
	 * This method
	 * 
	 * @param cmdInput User's input from cmd.
	 * @param songs A Map of Song.
	 * @return
	 */
	public Long selectSongIntoPlaylist(Scanner cmdInput, Map<Long, Song> songs) {
		boolean validValue = false;
		boolean validId = false;
		String songId;
		
		do {
			do {
				System.out.print("Canzone da eliminare: ");
				songId = cmdInput.nextLine();
				
				if(songId.isBlank()) {
					System.out.println("Inserire un valore.");
					continue;
				}
				
				if(songId.equals("EXIT")) {
					return null;
				} else {
					char[] chars = new char[songId.length()];
					songId.getChars(0, songId.length(), chars, 0);
		        	
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
				}
				
			}while(!validValue);
			
			if(songs.containsKey(Long.valueOf(songId))) {
				validId = true;
			}
			
		}while(!validId);
		
		return Long.valueOf(songId);
	}
}
