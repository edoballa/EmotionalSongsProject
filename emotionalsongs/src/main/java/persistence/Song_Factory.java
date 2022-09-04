package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import objects.EmotionFeltDetails;
import objects.Song;

import java.util.ArrayList;
import java.util.HashMap;

public class Song_Factory implements IGeneric_Factory<Song, Long>{
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\song_data.csv";
    private static Song_Factory istance = null;
    private EmotionFelt_Factory emotionFeltFactory;
    
    private Map<Long, Song> songMap;
    private List<String> lines;
    private Long nextKey = null;
    
    private Song_Factory() throws Exception {
    	this.songMap = new HashMap<>();
        this.lines = new ArrayList<>();
        this.emotionFeltFactory = EmotionFelt_Factory.getIstance();
        
        fillList();
        
        if(nextKey == null) {
        	nextKey = Long.valueOf(songMap.size());
        	while(songMap.containsKey(nextKey)) {
        		nextKey++;
        	}
        }
    }
    
    public static Song_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Song_Factory();
        } else return istance;
    }

	@Override
	public void create(Song song) throws Exception, IOException {
		song.setSongId(getNextKey());
		songMap.putIfAbsent(song.getSongId(), song);
		save();
	}

	@Override
	public Song getById(Long id) throws Exception {
		if(songMap.containsKey(id))
			return songMap.get(id);
		else return null;
	}

	@Override
	public void update(Song song) throws Exception, IOException {
		songMap.replace(song.getSongId(), song);
		save();
	}

	@Override
	public void delete(Song song) throws Exception {
		if(songMap.containsKey(song.getSongId())) {
			songMap.remove(song.getSongId());
			save();
		}	
	}

	@Override
	public Map<Long, Song> listAll() {
		return songMap;
	}
	
	public void updateSongInList(Song song) {
		if(songMap.containsKey(song.getSongId())) {
			songMap.replace(song.getSongId(), song);
		}
	}
	
	public List<Song> getSongByString(String string){
		List<Song> songs = new ArrayList<>();
		
		for(Song song : songMap.values()) {
			if(song.getTitle().toLowerCase().contains(string.toLowerCase()) || song.getAuthor().toLowerCase().contains(string.toLowerCase())
					|| song.getYear().contains(string)) {
				songs.add(song);
			}
		}
		
		return songs;
	}
	
	public List<Song> getByMusicalGenre(String musicalGenre) {
		List<Song> songs = new ArrayList<>();
		
		for(Song song : songMap.values()) {
			if(song.getMusicalGenre().equals(musicalGenre)) {
				songs.add(song);
			}
		}
		
		return songs;
	}	
	
	public Long getNextKey() {
    	while(songMap.containsKey(nextKey)) {
    		nextKey++;
    	}
    	return nextKey;
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
        
        songMap.clear();
        fillList();
        
        return true;
    }
    
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        
        for(Song song : songMap.values()) {
            
            line = song.getSongId() + ";"
                    + song.getTitle() + ";" 
                    + song.getAuthor() + ";"
                    + song.getMusicalGenre() + ";"
                    + song.getYear();

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
        	Song song = new Song(); 
            
            String[] strs = line.split(";");
            if(strs.length > 0){
                song.setSongId(Long.valueOf(strs[0]));
                song.setTitle(strs[1]);
                song.setAuthor(strs[2]);
                song.setMusicalGenre(strs[3]);
                song.setYear(strs[4]);
                
                List<EmotionFeltDetails> emotionsFelt = emotionFeltFactory.getEmotionAndRelativeScoreBySongId(song.getSongId());
                song.setEmotionList(emotionsFelt);
                
                
                songMap.putIfAbsent(song.getSongId(), song);
            }
        }    
    }

}
