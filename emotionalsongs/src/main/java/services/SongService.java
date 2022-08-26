package services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import object.EmotionFelt;
import object.Playlist;
import object.Song;
import persistence.EmotionFelt_Factory;
import persistence.Playlist_Factory;
import persistence.Song_Factory;

public class SongService {
	private Song_Factory songFactory;
	private Playlist_Factory playlistFactory;
	private EmotionFelt_Factory emotionFeltFactory;
	
	public SongService() throws Exception {
		songFactory = Song_Factory.getIstance();
		playlistFactory = Playlist_Factory.getIstance();
		emotionFeltFactory = EmotionFelt_Factory.getIstance();
	}

	//* Ricerca con 3 lettere, per titolo, per autore e anno
	public Map<Long, Song> searchSong(String song) {
		List<Song> songByTitle = songFactory.getByTitle(song);
		List<Song> songByAuthor = songFactory.getSongByString(song);
		List<Song> songByMusicalGenre = songFactory.getByMusicalGenre(song);
		Map<Long, Song> songToReturn = new HashMap<>();
		
		for(Song s : songByTitle) {
			songToReturn.putIfAbsent(s.getSongId(), s);
		}
		for(Song s : songByAuthor) {
			songToReturn.putIfAbsent(s.getSongId(), s);
		}
		for(Song s : songByMusicalGenre) {
			songToReturn.putIfAbsent(s.getSongId(), s);
		}
		return songToReturn;
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
}
