package services;

import java.io.IOException;
import java.util.List;

import object.Playlist;
import object.Song;
import persistence.Playlist_Factory;
import persistence.User_Factory;

public class PlaylistService {
	private static PlaylistService istance = null;
	private Playlist_Factory playlistFactory;
	
	private PlaylistService() throws Exception{
        this.playlistFactory = Playlist_Factory.getIstance();
    }
    
    public static PlaylistService getIstance() throws Exception {
    	if(istance == null)
    		return new PlaylistService();
    	else return istance;
    }
	
	public void addPlaylist(String playlistName, Long userId) throws IOException, Exception {
		Playlist playlist = new Playlist();
		playlist.setName(playlistName);
		playlist.setPublic(false);
		playlist.setUserId(userId);
		
		Long playlistId = playlistFactory.getNextId();
		playlist.setPlaylistId(playlistId);
		
		playlistFactory.create(playlist);
		
	}
	
	public Playlist addSongToPlaylist(String playlistName, Song song, Long userId) throws IOException, Exception {
		Playlist playlist = playlistFactory.getByName(playlistName, userId);
		playlist.getSongs().add(song);
		
		playlistFactory.update(playlist);
		playlist = playlistFactory.getById(playlist.getPlaylistId());
		
		return playlist;
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
	
	public void updatePlaylistName(String playlistName, String newName) {
		
	}
	
}
