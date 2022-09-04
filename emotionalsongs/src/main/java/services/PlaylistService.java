package services;

import java.io.IOException;
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
		
		Long playlistId = playlistFactory.getNextId();
		playlist.setPlaylistId(playlistId);
		
		playlistFactory.create(playlist);
		return playlistFactory.getById(playlistId);		
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
	
	public void deletePlaylist(String playlistName, Long userId) throws Exception {
		Playlist playlist = playlistFactory.getByName(playlistName, userId);
		playlistFactory.delete(playlist);
	}
	
	public Playlist updatePlaylistName(String playlistName, String newName, Long userId) throws IOException, Exception {
		Playlist playlist = playlistFactory.getByName(playlistName, userId);
		playlist.setName(newName);
		playlistFactory.update(playlist);
		
		return playlist;
	}
	
	public String insertPlaylistData(Scanner cmdInput) {
		String playlistName = null;
		do {
			System.out.println("Inserisci il nome della playlist: ");
			playlistName = cmdInput.nextLine();
		} while(playlistName.isEmpty() || playlistName == null);
		
		return playlistName;
	}
	
}
