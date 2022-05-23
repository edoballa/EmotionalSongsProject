package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import object.Playlist;
import object.Song;

public class Playlist_Factory implements IGeneric_Factory<Playlist, Long>{
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\playlist_data.csv";
    private static Playlist_Factory istance = null;
    
    private Song_Factory songFactory;
    private Map<Long, Playlist> playlistList;
    private List<String> lines;
    
    private Playlist_Factory() throws Exception {
    	this.playlistList = new HashMap<>();
        this.lines = new ArrayList<>();
        this.songFactory = Song_Factory.getIstance();
        
        fillList();
    }
    
    public static Playlist_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Playlist_Factory();
        } else return istance;
    }

    @Override
	public void create(Playlist playlist) throws Exception, IOException {
		playlistList.putIfAbsent(playlist.getPlaylistId(), playlist);
    	save();		
	}

	@Override
	public Playlist getById(Long id) throws Exception {
		return playlistList.get(id);
	}

	@Override
	public void update(Playlist playlist) throws Exception, IOException {
		playlistList.replace(playlist.getPlaylistId(), playlist);
    	save();
	}

	@Override
	public void delete(Playlist playlist) throws Exception {
		playlistList.remove(playlist.getPlaylistId());
    	save();
	}
	
	@Override
	public Map<Long, Playlist> listAll() {
		return playlistList;
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
        
        playlistList.clear();
        fillList();
        
        return true;
    }
    
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        String songs = "";
        
        for(Playlist playlist : playlistList.values()) {
        	//(playlistId, userId, nome, List<canzone>, isPublic)
            line = playlist.getPlaylistId() + ";"
                    + playlist.getUserId() + ";" 
                    + playlist.getName() + ";"; 
            
            for(Song song : playlist.getSongs()) {
            	songs += song.getSongId() + ",";
            }
            
            songs = songs.substring(songs.length() - 1, songs.length());
            
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
        
        Playlist playlist;

        for(String line : lines) {
        	playlist = new Playlist(); //do this for cache problems
            
            String[] strs = line.split(";");
            if(strs.length > 0){
            	//(playlistId, userId, nome, List<canzone>, isPublic)
            	playlist.setPlaylistId(Long.valueOf(strs[0]));
            	playlist.setUserId(Long.valueOf(strs[1]));
            	playlist.setName(strs[2]);
            	playlist.setPublic(Boolean.valueOf(strs[4]));
            	playlist.setSongs(new ArrayList<>());
            	
            	String[] songs = strs[3].split(",");
            	if(songs.length > 0) {
            		for(int i = 0; i < songs.length; i++) {
            			playlist.getSongs().add(songFactory.getById(Long.valueOf(songs[i])));
            		}
            	}
                
                playlistList.putIfAbsent(playlist.getPlaylistId(), playlist);
            }
            playlist = null; //do this for cache problems
        }    
    }
}