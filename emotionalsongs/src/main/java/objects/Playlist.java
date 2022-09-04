/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package objects;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	/**
	 * <code>playlistId</code>
	 * A Long to keep track of the playlist's id.
	 */
    private Long playlistId;
    /**
     * <code>userId</code>
	 * A Long to keep track of the user's id.
	 */
    private Long userId;
    /**
     * <code>name</code>
	 * A String to store the playlist's name.
	 */
    private String name;
    /**
     * <code>songs</code>
     * A List of Song to store the song of a playlist.
     */
    private List<Song> songs;
    /**
     * <code>isPublic</code>
     * A boolean to control the visibility of a playlist.
     */
    private boolean isPublic = false;

    /**
     * Playlist default constructor.
     */
    public Playlist() {
    	this.songs = new ArrayList<>();
    }

    /**
     * Playlist constructor with all fields as parameters.
     * 
     * @param <playlistId> The playlist's id.
     * @param <userId> The user's id.
     * @param <name> The playlist's name.
     * @param <songs> The playlist's songs.
     * @param <isPublic> The visibility of the playlist.
     */
    public Playlist(Long playlistId, Long userId, String name, List<Song> songs, boolean isPublic) {
        this.playlistId = playlistId;
        this.userId = userId;
        this.name = name;
        this.songs = songs;
        this.isPublic = isPublic;
    }

    /**
     * This method return the playlistId field.
     * @return Long the playlistId.
     */
    public Long getPlaylistId() {
        return playlistId;
    }

    /**
     * playlistId setter method.
     * @param <playlistId> The playlistId to set.
     */
    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * This method return the userId field.
     * @return Long the userId.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * userId setter method.
     * @param <userId> The userId to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method return the playlist's name field.
     * @return String the name.
     */
    public String getName() {
        return name;
    }

    /**
     * name setter method.
     * @param <name> The playlist's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method return a List of Song.
     * @return List<Song> the List of Song.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * songs setter method.
     * @param <songs> The playlist's songs to set.
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * This method return the value of the boolean field.
     * @return boolean the visibility of a playlist.
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * isPublic setter method.
     * @param <isPublic> The playlist's visibility to set.
     */
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
