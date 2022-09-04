package services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import objects.EmotionFeltDetails;
import objects.Song;
import persistence.Song_Factory;

public class SongService {
	private Song_Factory songFactory;
	
	private Map<Long, Song> lastSearch;
	private Long lastSelectedSong;
	
	public SongService() throws Exception {
		songFactory = Song_Factory.getIstance();
		lastSearch = new HashMap<Long, Song>();
	}

	//* Ricerca con 3 lettere, per titolo, per autore e anno
	public Map<Long, Song> searchSong(String song) {
		List<Song> songByAuthor = songFactory.getSongByString(song);
		
		lastSearch.clear();
		
		for(Song s : songByAuthor) {
			lastSearch.putIfAbsent(s.getSongId(), s);
		}
		
		return lastSearch;
	}
	
	public String getStringToSearch(Scanner cmdInput) {
		String stringToSearch;
		do {
			System.out.print("Inserisci la canzone da cercare (inserisci almeno 3 lettere): ");
			stringToSearch = cmdInput.nextLine();
		}while(stringToSearch.length() < 3);
		
		return stringToSearch;
	}
	
	public void showResult(Map<Long, Song> resultMap) {
		for(Song s : resultMap.values()) {
			System.out.println(s.getSongId() + " - " + s.getTitle() + "(" + s.getAuthor() + ")" );
		}
	}
	
	public Long selectSong(Scanner cmdInput) {
		boolean validValue = false;
		boolean validId = false;
		String songId;
		
		do {
			do {
				System.out.print("Canzone da selezionare: ");
				songId = cmdInput.nextLine();
				
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
	
	public Song getSongById(Long id) {
		try {
			return songFactory.getById(id);
		} catch (Exception e) {
			e = new Exception("Something went wrong during search song");
			e.printStackTrace();
		}
		
		return new Song();
	}
	
	public void printSongDetails(Long songId) {
		Song song = new Song();
		try {
			song = songFactory.getById(songId);
		} catch (Exception e) {
			e= new Exception("Something went wrong when get song details");
			e.printStackTrace();
		}
		
		System.out.println("------- DETTAGLI CANZONE -------");
		System.out.println(song.getTitle().toUpperCase() + "(" + song.getAuthor() + ")");
		System.out.println("Genere : " + song.getMusicalGenre());
		System.out.println("Anno : " + song.getYear());
		
		if(!song.getEmotionList().isEmpty()) {
			System.out.println("Emozioni provate dagli utenti (il numero indica il valore medio)");	
			for(EmotionFeltDetails emd : song.getEmotionList()) {
				System.out.println(emd.getEmotion().getName() + "(" + emd.getEmotion().getDescription() + ")");
				System.out.println("Valore medio: " + emd.getAverageOfRatings() + " (la media fa riferimento a " + emd.getNumberOfRatings() + " recensioni)");
				System.out.println("Commenti: ");
				for(String s : emd.getComments()) {
					System.out.println(s);
				}
			} 
		}
	}
	
	public Long getLastSongSelected() {
		return this.lastSelectedSong;
	}
}
