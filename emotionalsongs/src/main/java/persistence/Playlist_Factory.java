/**
* This package contains the classes that create and manage the various factory.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import objects.Playlist;
import objects.Song;

import java.util.ArrayList;
import java.util.HashMap;

public class Playlist_Factory implements IGeneric_Factory<Playlist, Long>{
	/**
	 * <code>FILEPATH</code>
	 * A String to store the path of the file that contains the playlists create by the users.
	 */
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\playlist_data.csv";
	/**
	 * <code>instance</code>
	 * A Playlist_Factory object that allows to instantiate the class only once.
	 */
    private static Playlist_Factory istance = null;
    /**
	 * <code>songFactory</code>
	 * A Song_Factory object.
	 */
    private Song_Factory songFactory;
    /**
	 * <code>playlistMap</code>
	 * A Map of Playlist that contains all the playlist create by a user.
	 */
    private Map<Long, Playlist> playlistMap;
    /**
	 * <code>lines</code>
	 * A List of String that contains the lines of a file.
	 */
    private List<String> lines;
    /**
	 * <code>nextKey</code>
	 * A Long to keep track of the next key of the map.
	 */
    private Long nextKey = null;
    
    /**
     * Playlist_Factory default constructor.
     * 
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
    private Playlist_Factory() throws Exception {
    	this.playlistMap = new HashMap<>();
        this.lines = new ArrayList<>();
        this.songFactory = Song_Factory.getIstance();
        
        fillList();
        
        if(nextKey == null) {
        	nextKey = Long.valueOf(playlistMap.size());
        	while(playlistMap.containsKey(nextKey)) {
        		nextKey++;
        	}
        }
    }
    
    /**
     * This method return the istance of the Playlist_Factory object.
     * 
     * @return The object Playlist_Factory.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
    public static Playlist_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Playlist_Factory();
        } else return istance;
    }

    /**
     * This method create a playlist and add to the Map.
     * 
     * @param playlist The object that represents the playlist.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     * @throws IOException This exception is thrown if there is any input/output error.
     */
    @Override
    public void create(Playlist playlist) throws Exception, IOException {
		playlistMap.putIfAbsent(playlist.getPlaylistId(), playlist);
    	save();		
	}

    /**
     * This method return a Playlist based on its id.
     * 
     * @param id The playlist's id.
     * @return The object Playlist.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
	@Override
	public Playlist getById(Long id) throws Exception {
		return playlistMap.get(id);
	}

	/**
	 * This method replace in the map a Playlist object with the one passed as a parameter.
	 * 
	 * @param playlist The object that represents the playlist.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     * @throws IOException This exception is thrown if there is any input/output error.
	 */
	@Override
	public void update(Playlist playlist) throws Exception, IOException {
		playlistMap.replace(playlist.getPlaylistId(), playlist);
    	save();
	}

	/**
	 * This method remove a Playlist from the map.
	 * 
	 * @param playlist The object that represents the playlist.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	@Override
	public void delete(Playlist playlist) throws Exception {
		playlistMap.remove(playlist.getPlaylistId());
    	save();
	}
	
	/**
	 * This method return a Map with all the Playlist.
	 * 
	 * @return <playlistMap> A Map of Playlist.
	 */
	@Override
	public Map<Long, Playlist> listAll() {
		return playlistMap;
	}
	
	/**
	 * This method return the next key of the playlist map.
	 * 
	 * @return A Long that indicate the next key of the map.
	 */
	public Long getNextKey() {
		while(playlistMap.containsKey(nextKey)) {
    		nextKey++;
    	}
    	return nextKey;
	}
	
	/**
	 * This method returns a user's playlist based on the playlist name.
	 * 
	 * @param playlistName The name of the playlist.
	 * @param userId The user's id.
	 * @return A Playlist object.
	 */
	public Playlist getByName(String playlistName, Long userId) {
		Map<Long, Playlist> userPlaylist = getUserPlaylist(userId);
		
		for(Playlist playlist : userPlaylist.values()) {
			if(playlist.getName().equals(playlistName)) {
				return playlist;
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns all playlists created by a user.
	 * 
	 * @param userId The user's id.
	 * @return A map of playlists.
	 */
	public Map<Long, Playlist> getUserPlaylist(Long userId){
		Map<Long, Playlist> userPlaylist = new HashMap<>();
		
		for(Playlist playlist : playlistMap.values()) {
			if(playlist.getUserId() == userId) {
				userPlaylist.put(playlist.getPlaylistId(), playlist);
			}
		}
		
		return userPlaylist;
	}
	
	/**
	 * This method creates a new file in which it saves the playlists create by a user.
	 * If a file with that name already exists, it deletes it and creates a new one.
	 * 
	 * @return <strong>True</strong>, if the program successfully saves the file, <strong>False</strong>, if the program throws the exception.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	private Boolean save() throws Exception{
        prepareDataForWriting();
        
        try{
            Files.deleteIfExists(new File(FILEPATH).toPath());
            Path file = Files.createFile(new File(FILEPATH).toPath());
            Files.write(file, lines);            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        playlistMap.clear();
        fillList();
        
        return true;
    }
    
	/**
	 * This method adds the playlists creates by a user to a list of String.
	 * The data in the list are divided by a separator.
	 */
	private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        
        for(Playlist playlist : playlistMap.values()) {
        	//(playlistId, userId, nome, List<canzone>, isPublic)
            line = playlist.getPlaylistId() + ";"
                    + playlist.getUserId() + ";" 
                    + playlist.getName() + ";"; 
            
            String songs = "";
            if(playlist.getSongs() != null && !playlist.getSongs().isEmpty()) {
	            for(Song song : playlist.getSongs().values()) {
	            	songs += song.getSongId() + ",";
	            }
	            
	            if(!songs.isBlank()) {
	            	songs = songs.substring(0, songs.length() - 1);
	            }
            }
            
            line += songs + ";" + playlist.isPublic();
            lines.add(line);
        }        
    }
    
    /**
     * This method after the every update refresh the object Map.
     * 
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
	private void fillList() throws Exception{
        lines.clear();
        
        try {
            lines = Files.readAllLines(new File(FILEPATH).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for(String line : lines) {
        	Playlist playlist = new Playlist();
            
            String[] strs = line.split(";");
            if(strs.length > 0){
            	playlist.setPlaylistId(Long.valueOf(strs[0]));
            	playlist.setUserId(Long.valueOf(strs[1]));
            	playlist.setName(strs[2]);
            	playlist.setPublic(Boolean.valueOf(strs[4]));
            	playlist.setSongs(new HashMap<>());
            	
            	if(strs[3].length() > 1) {
	            	String[] songs = strs[3].split(",");
	        		for(int i = 0; i < songs.length; i++) {
	        			playlist.getSongs().put(songFactory.getById(Long.valueOf(songs[i])).getSongId(), songFactory.getById(Long.valueOf(songs[i]))); 
	            	}
            	}
                
                playlistMap.putIfAbsent(playlist.getPlaylistId(), playlist);
            }
        }    
    }
}
