/**
* This package contains the busisness logic. 
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import objects.EmotionFeltDetails;
import objects.Song;
import persistence.Song_Factory;
/**
* <p>This class contains the method to get the data insert by the user, 
* the method to manipulate the data, and the method to call the factory.</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class SongService {
	/**
	 * <code>songFactory</code>
	 * A SongFactory object use to invoke the methods of that class.
	 */
	private Song_Factory songFactory;
	/**
	 * <code>lastSearch</code>
	 * A Map that contains the last search of the user.
	 */
	private Map<Long, Song> lastSearch;
	/**
	 * <code>lastSelectedSong</code>
	 * A Long to keep track of the id of the last selected song.
	 */
	private Long lastSelectedSong;
	
	/**
	 *  SongService default constructor.
	 *  
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	public SongService() throws Exception {
		songFactory = Song_Factory.getIstance();
		lastSearch = new HashMap<Long, Song>();
	}

	/**
	 * This method return a map with the searched song inside.
	 * 
	 * @param song A String that represents the song to search.
	 * @return A Map that contains the last song search by the user.
	 */
	public Map<Long, Song> searchSong(String song) {
		List<Song> songByAuthor = songFactory.getSongByString(song);
		
		lastSearch.clear();
		
		for(Song s : songByAuthor) {
			lastSearch.putIfAbsent(s.getSongId(), s);
		}
		
		return lastSearch;
	}
	
	/**
	 * This method return the String that contains the song to search.
	 * 
	 * @param cmdInput User's input from cmd.
	 * @return A String.
	 */
	public String getStringToSearch(Scanner cmdInput) {
		String stringToSearch;
		do {
			System.out.print("Inserisci la canzone da cercare (inserisci almeno 3 lettere): ");
			stringToSearch = cmdInput.nextLine();
		}while(stringToSearch.length() < 3 || stringToSearch.isBlank());
		
		return stringToSearch;
	}
	
	/**
	 * This method print on the console a Map of Song.
	 * 
	 * @param resultMap A Map of Song.
	 */
	public void showResult(Map<Long, Song> resultMap) {
		for(Song s : resultMap.values()) {
			System.out.println(s.getSongId() + " - " + s.getTitle() + "(" + s.getAuthor() + ")" );
		}
	}
	
	/**
	 * This method get and check the data insert by user and give the id of the song to select.
	 * 
	 * @param cmdInput User's input from cmd.
	 * @return The id of the song to select
	 */
	public Long selectSong(Scanner cmdInput) {
		boolean validValue = false;
		boolean validId = false;
		String songId;
		
		do {
			do {
				System.out.print("Canzone da selezionare: ");
				songId = cmdInput.nextLine();
				
				if(songId.isBlank()) {
					System.out.println("Inserire un valore.");
					continue;
				}
				
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
				
			}while(!validValue);
			
			if(lastSearch.containsKey(Long.valueOf(songId))) {
				validId = true;
			}
			
		}while(!validId);
		
		this.lastSelectedSong = Long.valueOf(songId);
		return Long.valueOf(songId);
	}
	
	/**
	 * This method return a song based on its id.
	 * 
	 * @param id The song's id.
	 * @return A Song object.
	 */
	public Song getSongById(Long id) {
		try {
			return songFactory.getById(id);
		} catch (Exception e) {
			e = new Exception("Something went wrong during search song");
			e.printStackTrace();
		}
		
		return new Song();
	}
	
	/**
	 * This method print on the console all the data and details of a song, with also the emotions felt by the users and the associated details.
	 * 
	 * @param songId The song's id.
	 */
	public void printSongDetails(Long songId) {
		Song song = new Song();
		try {
			song = songFactory.getById(songId);
		} catch (Exception e) {
			e= new Exception("Something went wrong when get song details");
			e.printStackTrace();
		}
		
		System.out.println("------- DETTAGLI CANZONE ----------------------------------------------------------------------");
		System.out.println(song.getTitle().toUpperCase() + "(" + song.getAuthor() + ")");
		System.out.println("Genere : " + song.getMusicalGenre());
		System.out.println("Anno : " + song.getYear());
		
		if(!song.getEmotionList().isEmpty()) {
			System.out.println("\nEmozioni provate dagli utenti (il numero indica il valore medio): \n");
			
			for(EmotionFeltDetails emd : song.getEmotionList()) {
				System.out.println(emd.getEmotion().getName() + "(" + emd.getEmotion().getDescription() + ")");
				System.out.println("Valore medio: " + emd.getAverageOfRatings() + " (la media fa riferimento a " + emd.getNumberOfRatings() + " recensioni)");
				
				if(!emd.getComments().isEmpty()) {
					System.out.println("Commenti: ");
					for(String s : emd.getComments()) {
						System.out.println(s);
					}
				}
				System.out.println("--------------");
			} 
		}
	}
	
	/**
	 * This method return the id of the last song selected.
	 * 
	 * @return A Long.
	 */
	public Long getLastSongSelected() {
		return this.lastSelectedSong;
	}
}
