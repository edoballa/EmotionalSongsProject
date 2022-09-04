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
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\playlist_data.csv";
    private static Playlist_Factory istance = null;
    
    private Song_Factory songFactory;
    private Map<Long, Playlist> playlistMap;
    private List<String> lines;
    private Long nextKey = null;
    
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
    
    public static Playlist_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Playlist_Factory();
        } else return istance;
    }

    @Override
	public void create(Playlist playlist) throws Exception, IOException {
		playlistMap.putIfAbsent(playlist.getPlaylistId(), playlist);
    	save();
	}

	@Override
	public Playlist getById(Long id) throws Exception {
		return playlistMap.get(id);
	}

	@Override
	public void update(Playlist playlist) throws Exception, IOException {
		playlistMap.replace(playlist.getPlaylistId(), playlist);
    	save();
	}

	@Override
	public void delete(Playlist playlist) throws Exception {
		playlistMap.remove(playlist.getPlaylistId());
    	save();
	}
	
	@Override
	public Map<Long, Playlist> listAll() {
		return playlistMap;
	}
	
	public Long getNextKey() {
    	while(playlistMap.containsKey(nextKey)) {
    		nextKey++;
    	}
    	return nextKey;
	}
	
	public Playlist getByName(String playlistName, Long userId) {
		Map<Long, Playlist> userPlaylist = getUserPlaylist(userId);
		
		for(Playlist playlist : userPlaylist.values()) {
			if(playlist.getName().equals(playlistName)) {
				return playlist;
			}
		}
		
		return null;
	}
	
	public Map<Long, Playlist> getUserPlaylist(Long userId){
		Map<Long, Playlist> userPlaylist = new HashMap<>();
		
		for(Playlist playlist : playlistMap.values()) {
			if(playlist.getUserId() == userId) {
				userPlaylist.put(playlist.getPlaylistId(), playlist);
			}
		}
		
		return userPlaylist;
	}
	
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
    
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        String songs = "";
        
        for(Playlist playlist : playlistMap.values()) {
        	//(playlistId, userId, nome, List<canzone>, isPublic)
            line = playlist.getPlaylistId() + ";"
                    + playlist.getUserId() + ";" 
                    + playlist.getName() + ";"; 
            
            if(playlist.getSongs() != null && !playlist.getSongs().isEmpty()) {
	            for(Song song : playlist.getSongs()) {
	            	songs += song.getSongId() + ",";
	            }
	            //songs = songs.substring(0, songs.length() - 1);
            }
            
            line += songs + ";" + playlist.isPublic();
            lines.add(line);
        }        
    }
    
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
            	playlist.setSongs(new ArrayList<>());
            	
            	if(strs[3].length() > 1) {
	            	String[] songs = strs[3].split(",");
	        		for(int i = 0; i < songs.length; i++) {
	        			playlist.getSongs().add(songFactory.getById(Long.valueOf(songs[i]))); 
	            	}
            	}
                
                playlistMap.putIfAbsent(playlist.getPlaylistId(), playlist);
            }
        }    
    }
}
