package services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import objects.Emotion;
import objects.EmotionFelt;
import objects.EmotionFeltDetails;
import objects.Playlist;
import objects.Song;
import persistence.EmotionFelt_Factory;
import persistence.Playlist_Factory;
import persistence.Song_Factory;

public class SongService {
	private Song_Factory songFactory;
	private Playlist_Factory playlistFactory;
	private EmotionFelt_Factory emotionFeltFactory;
	
	private Map<Long, Song> lastSearch;
	
	public SongService() throws Exception {
		songFactory = Song_Factory.getIstance();
		playlistFactory = Playlist_Factory.getIstance();
		emotionFeltFactory = EmotionFelt_Factory.getIstance();
		lastSearch = new HashMap<Long, Song>();
	}

	//* Ricerca con 3 lettere, per titolo, per autore e anno
	public Map<Long, Song> searchSong(String song) {
		List<Song> songByTitle = songFactory.getByTitle(song);
		List<Song> songByAuthor = songFactory.getSongByString(song);
		List<Song> songByMusicalGenre = songFactory.getByMusicalGenre(song);
		lastSearch.clear();
		
		for(Song s : songByTitle) {
			lastSearch.putIfAbsent(s.getSongId(), s);
		}
		for(Song s : songByAuthor) {
			lastSearch.putIfAbsent(s.getSongId(), s);
		}
		for(Song s : songByMusicalGenre) {
			lastSearch.putIfAbsent(s.getSongId(), s);
		}
		
		return lastSearch;
	}
	
	//* Creazione di una playlist
	public void createPlaylist(String name, Long userId) throws IOException, Exception {
		Playlist playlist = new Playlist();
		playlist.setName(name);
		playlist.setUserId(userId);
		playlistFactory.create(playlist);
	}
	
	//* Inserimento di un'emozione
	public void insertEmotion(Long userId, Long songId, Long emotionId) throws IOException, Exception {
		EmotionFelt emotionFelt = new EmotionFelt();
		emotionFelt.setSongId(songId);
		emotionFelt.setUserId(userId);
		emotionFelt.setEmotionId(emotionId);
		emotionFeltFactory.create(emotionFelt);
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
		        	case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
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
		
		return Long.valueOf(songId);
	}
	
	public void printSongDetails(Long songId) {
		Song song = new Song();
		try {
			song = songFactory.getById(songId);
		} catch (Exception e) {
			e= new Exception("Something went wrong when get song details");
			e.printStackTrace();
		}
		
		System.out.println(song.getTitle().toUpperCase() + "(" + song.getAuthor() + ")");
		System.out.println("Genere : " + song.getMusicalGenre());
		System.out.println("Anno : " + song.getYear());
		
		System.out.println("Emozioni provate dagli utenti (il numero indica il valore medio)");	
		for(EmotionFeltDetails emd : song.getEmotionList()) {
			System.out.println(emd.getEmotion().getName().toUpperCase() + "(" + emd.getEmotion().getDescription() + ")");
			System.out.println("Valore medio: " + emd.getAverageOfRatings() + " (la media fa riferimento a " + emd.getNumberOfRatings() + " recensioni)");
			System.out.println("Commenti: ");
			for(String s : emd.getComments()) {
				System.out.println(s);
			}
		} 
	}
}
