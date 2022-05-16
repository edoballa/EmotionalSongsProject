package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import object.Song;

public class Song_Factory implements IGeneric_Factory<Song, Long>{
	private static final String FILEPATH = System.getProperty("song.dir") + "\\data\\song_data.csv";
    private static Song_Factory istance = null;
    
    private Map<Long, Song> SongList;
    private List<String> lines;
    
    private Song_Factory() throws Exception {
    	this.SongList = new HashMap<>();
        this.lines = new ArrayList<>();
        
        fillSongList();
    }
    
    public static Song_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Song_Factory();
        } else return istance;
    }

	@Override
	public void create(Song song) throws Exception, IOException {
		song.setSongId(Long.valueOf(SongList.size()+1));
		SongList.putIfAbsent(song.getSongId(), song);
		save();
	}

	@Override
	public Song getById(Long id) throws Exception {
		if(SongList.containsKey(id))
			return SongList.get(id);
		else return null;
	}

	@Override
	public void update(Song song) throws Exception, IOException {
		SongList.remove(song.getSongId());
		SongList.put(song.getSongId(), song);
		save();
	}

	@Override
	public void delete(Song song) throws Exception {
		if(SongList.containsKey(song.getSongId())) {
			SongList.remove(song.getSongId());
			save();
		}	
	}

	@Override
	public Map<Long, Song> listAll() {
		return SongList;
	}
	
	public List<Song> getSongByString(String string){
		List<Song> songs = new ArrayList<>();
		
		for(Song song : SongList.values()) {
			if(song.getTitle().contains(string) || song.getAuthor().contains(string)) {
				songs.add(song);
			}
		}
		
		return songs;
	}
	
	public List<Song> getByTitle(String title) {
		List<Song> songs = new ArrayList<>();
		
		for(Song song : SongList.values()) {
			if(song.getTitle().equals(title) || song.getTitle().contains(title)) {
				songs.add(song);
			}
		}
		
		return songs;
	}
	
	public List<Song> getByMusicalGenre(String musicalGenre) {
		List<Song> songs = new ArrayList<>();
		
		for(Song song : SongList.values()) {
			if(song.getMusicalGenre().equals(musicalGenre)) {
				songs.add(song);
			}
		}
		
		return songs;
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
        
        SongList.clear();
        fillSongList();
        
        return true;
    }
    
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        
        for(Song song : SongList.values()) {
            
            line = song.getSongId() + ";"
                    + song.getTitle() + ";" 
                    + song.getAuthor() + ";"
                    + song.getMusicalGenre() + ";"
                    + song.getYear();

            lines.add(line);
        }        
    }
    
    private void fillSongList() throws Exception{
        lines.clear();
        
        try {
            lines = Files.readAllLines(new File(FILEPATH).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Song song;

        for(String line : lines) {
            song = new Song(); //do this for cache problems
            
            String[] strs = line.split(";");
            if(strs.length > 0){
                song.setSongId(Long.valueOf(strs[0]));
                song.setTitle(strs[1]);
                song.setAuthor(strs[3]);
                song.setMusicalGenre(strs[4]);
                song.setYear(strs[5]);
                
                SongList.putIfAbsent(song.getSongId(), song);
            }
            song = null; //do this for cache problems
        }    
    }

}
