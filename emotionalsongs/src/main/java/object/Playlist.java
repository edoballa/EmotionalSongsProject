package object;

import java.util.List;

public class Playlist {
	//(playlistId, userId, nome, List<canzone>, isPublic)
	private Long playlistId;
	private Long userId;
	private String name;
	private List<Song> songs;
	private boolean isPublic = false;
	
	public Playlist() {
		
	}

	public Playlist(Long playlistId, Long userId, String name, List<Song> songs, boolean isPublic) {
		this.playlistId = playlistId;
		this.userId = userId;
		this.name = name;
		this.songs = songs;
		this.isPublic = isPublic;
	}

	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
}
