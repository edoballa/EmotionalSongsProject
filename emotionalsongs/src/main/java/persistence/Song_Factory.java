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

import objects.EmotionFeltDetails;
import objects.Song;

import java.util.ArrayList;
import java.util.HashMap;

public class Song_Factory implements IGeneric_Factory<Song, Long>{
	/**
	 * <code>FILEPATH</code>
	 * A String to store the path of the file that contains the songs.
	 */
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\song_data.csv";
	/**
	 * <code>instance</code>
	 * A Song_Factory object that allows to instantiate the class only once.
	 */
    private static Song_Factory istance = null;
    /**
	 * <code>emotionFeltFactory</code>
	 * A EmotionFelt_Factory object.
	 */
    private EmotionFelt_Factory emotionFeltFactory;
    /**
	 * <code>songMap</code>
	 * A Map of Song that contains all the songs.
	 */
    private Map<Long, Song> songMap;
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
     * Song_Factory default constructor.
     * 
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
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
    
    /**
     * This method return the istance of the Song_Factory object.
     * 
     * @return The object Song_Factory.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
    public static Song_Factory getIstance() throws Exception {
    	if(istance == null){
            return new Song_Factory();
        } else return istance;
    }

    /**
     * This method create a song and add to the Map.
     * 
     * @param song The object that represents the song.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     * @throws IOException This exception is thrown if there is any input/output error.
     */
	@Override
	public void create(Song song) throws Exception, IOException {
		song.setSongId(getNextKey());
		songMap.putIfAbsent(song.getSongId(), song);
		save();
	}

	/**
     * This method return a Song based on its id.
     * 
     * @param id The song's id.
     * @return The object Song.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     */
	@Override
	public Song getById(Long id) throws Exception {
		if(songMap.containsKey(id))
			return songMap.get(id);
		else return null;
	}

	/**
	 * This method replace in the map a Song object with the one passed as a parameter.
	 * 
	 * @param song The object that represents the song.
     * @throws Exception This class indicate conditions that a reasonable application might want to catch.
     * @throws IOException This exception is thrown if there is any input/output error.
	 */
	@Override
	public void update(Song song) throws Exception, IOException {
		songMap.replace(song.getSongId(), song);
		save();
	}

	/**
	 * This method remove a Song from the map.
	 * 
	 * @param song The object that represents the song.
	 * @throws Exception This class indicate conditions that a reasonable application might want to catch.
	 */
	@Override
	public void delete(Song song) throws Exception {
		if(songMap.containsKey(song.getSongId())) {
			songMap.remove(song.getSongId());
			save();
		}	
	}

	/**
	 * This method return a Map with all the Song.
	 * 
	 * @return <songMap> A Map of Song.
	 */
	@Override
	public Map<Long, Song> listAll() {
		return songMap;
	}
	
	/**
	 * This method allows to replace a song in the map with the one passed as a parameter.
	 * 
	 * @param song The new Song to replace the old one with.
	 */
	public void updateSongInList(Song song) {
		if(songMap.containsKey(song.getSongId())) {
			songMap.replace(song.getSongId(), song);
		}
	}
	
	/**
	 * This method return a List of Song starting from a search of a String.
	 * Check if the String contains the title or the author or the year of a song and then return the results.
	 * 
	 * @param string The String to search.
	 * @return A List of Song.
	 */
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
	
	/**
	 * This method return a List of Song starting from a search of a musical genre.
	 * @param musicalGenre The musical genre to search.
	 * @return A List of Song.
	 */
	public List<Song> getByMusicalGenre(String musicalGenre) {
		List<Song> songs = new ArrayList<>();
		
		for(Song song : songMap.values()) {
			if(song.getMusicalGenre().equals(musicalGenre)) {
				songs.add(song);
			}
		}
		
		return songs;
	}	
	
	/**
	 * This method return the next key of the song map.
	 * 
	 * @return A Long that indicate the next key of the map.
	 */
	public Long getNextKey() {
    	while(songMap.containsKey(nextKey)) {
    		nextKey++;
    	}
    	return nextKey;
	}
	
	/**
	 * This method creates a new file in which it saves the songs.
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
        
        songMap.clear();
        fillList();
        
        return true;
    }
    
	/**
	 * This method adds the songs to a list of strings.
	 * The data in the list are divided by a separator.
	 */
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
