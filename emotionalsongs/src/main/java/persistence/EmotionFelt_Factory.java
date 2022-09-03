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

import objects.Emotion;
import objects.EmotionFelt;
import objects.EmotionFeltDetails;
import objects.Emotions;
import objects.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EmotionFelt_Factory implements IGeneric_Factory<EmotionFelt, String>{
	/**
	 * <code>FILEPATH</code>
	 * A String to store the path of the file that contains the emotions felt by the users.
	 */
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\emotionFelt_data.csv";
	/**
	 * <code>istance</code>
	 */
    private static EmotionFelt_Factory istance = null;
    /**
     * <code>emotionFeltList</code>
     */
    private Map<String, EmotionFelt> emotionFeltList;
    /**
     * <code>lines</code>
     */
    private List<String> lines;
    
    /**
     * EmotionFelt_Factory default constructor.
     */
    private EmotionFelt_Factory() throws Exception {
    	this.emotionFeltList = new HashMap<>();
        this.lines = new ArrayList<>();
        
        fillList();
    }
    
    /**
     * This method return the istance of the EmotionFelt_Factory object.
     * 
     * @return the object EmotionFelt_Factory.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    public static EmotionFelt_Factory getIstance() throws Exception {
    	if(istance == null){
            return new EmotionFelt_Factory();
        } else return istance;
    }

    /**
     * This method add a new emotion felt by an user if it isn't present in the list.
     * 
     * @param <emotionFelt> The object that represents the emotion felt.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     * @throws <IOException> This exception is thrown if there is any input/output error.
     */
	@Override
	public void create(EmotionFelt emotionFelt) throws Exception, IOException {
		emotionFeltList.putIfAbsent(emotionFelt.getEmotionFeltId(), emotionFelt);
		save();
	}

	/**
	 * This method return the EmotionFelt object based on its id.
	 * 
	 * @param <id> The emotion felt's id.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	@Override
	public EmotionFelt getById(String id) throws Exception {
		return emotionFeltList.get(id);
	}

	/**
	 * This method replace in the list an EmotionFelt object with the one passed as a parameter.
	 * 
	 * @param <emotionFelt> The object that represents the emotion felt.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 * @throws <IOException> This exception is thrown if there is any input/output error.
	 */
	@Override
	public void update(EmotionFelt emotionFelt) throws Exception, IOException {
		emotionFeltList.remove(emotionFelt.getEmotionFeltId());
		emotionFeltList.put(emotionFelt.getEmotionFeltId(), emotionFelt);
		save();
	}

	/**
	 * This method remove an EmotionFelt object from the list.
	 * 
	 * @param <emotionFelt> The object that represents the emotion felt.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	@Override
	public void delete(EmotionFelt emotionFelt) throws Exception {
		emotionFeltList.remove(emotionFelt.getEmotionFeltId());
		save();
	}

	/**
	 * This method return a Map with all the EmotionFelt objects.
	 */
	@Override
	public Map<String, EmotionFelt> listAll() {
		return emotionFeltList;
	}
	
	/**
	 * This method return 
	 * 
	 * @param <userId> The user's id.
	 * @return
	 */
	public List<EmotionFelt> listAllByUser(Long userId) {
		List<EmotionFelt> userEmotions = new ArrayList<>();
		for(EmotionFelt emotionFelt : emotionFeltList.values()) {
			if(emotionFelt.getUserId() == userId) {
				userEmotions.add(emotionFelt);
			}
		}
		
		return userEmotions;
	}
	
	/**
	 * This method return a list
	 * 
	 * @param <songId> The song's id.
	 * @return
	 */
	public List<EmotionFelt> listAllBySongId(Long songId) {
		List<EmotionFelt> songEmotions = new ArrayList<>();
		for(EmotionFelt emotionFelt : emotionFeltList.values()) {
			if(emotionFelt.getSongId() == songId) {
				songEmotions.add(emotionFelt);
			}
		}
		
		return songEmotions;
	}
	
	/**
	 * This class contains the fields related to the score of a song.
	 */
	private class EmotionScore{
		/**
		 * <code>emotionId</code>
		 * A Long to keep track of the id of an emotion.
		 */
		public Long emotionId;
		/**
		 * <code>scoreSum</code>
		 * An integer to keep track of the sum of the scores about a song.
		 */
		public int scoreSum;
		/**
		 * <code>counter</code>
		 * An integer to keep track of the number of scores given to a song by users.
		 */
		public int counter;
		/**
		 * <code>score</code>
		 * An integer to keep track the score related to an emotion.
		 */
		public Double score;		
	}
	
	/**
	 * 
	 * @param <songId> The song's id.
	 * @return
	 */
	public List<EmotionFeltDetails> getEmotionAndRelativeScoreBySongId(Long songId) {
		if(emotionFeltList.isEmpty()) {
			return new ArrayList<EmotionFeltDetails>();
		}
		
		Map<Long, EmotionScore> songEmotions = new HashMap<>();
		EmotionScore emotionScore = new EmotionScore();
		
		for(EmotionFelt emotionFelt : emotionFeltList.values()) {
			if(emotionFelt.getSongId() == songId) {
				if(!songEmotions.containsKey(emotionFelt.getEmotionId())) {
					emotionScore.emotionId = emotionFelt.getEmotionId();
					emotionScore.counter = 1;
					emotionScore.scoreSum = emotionFelt.getScore();
					emotionScore.score = Double.valueOf(emotionScore.scoreSum / emotionScore.counter);
					songEmotions.put(emotionFelt.getEmotionId(), emotionScore);
				} else {
					emotionScore = songEmotions.get(emotionFelt.getEmotionId());
					emotionScore.counter++;
					emotionScore.scoreSum += emotionFelt.getScore();
					emotionScore.score = Double.valueOf(emotionScore.scoreSum / emotionScore.counter);
					songEmotions.replace(emotionScore.emotionId, emotionScore);
				}
			}
		}
		
		List<EmotionFeltDetails> emotionFeltDetailsList = new ArrayList<EmotionFeltDetails>();
		for(EmotionScore eScore : songEmotions.values()) {
			EmotionFeltDetails emotionFeltDetails = new EmotionFeltDetails();
			emotionFeltDetails.setAverageOfRatings(eScore.score);
			emotionFeltDetails.setNumberOfRatings(eScore.counter);
			emotionFeltDetails.setEmotion(Emotions.getEmotionById(eScore.emotionId));
			
			for(EmotionFelt emotionFelt : emotionFeltList.values()) {
				if(emotionFelt.getSongId() == songId && !emotionFelt.getNote().isEmpty()) {
					emotionFeltDetails.getComments().add(emotionFelt.getNote());					
				}
			}
			
			emotionFeltDetailsList.add(emotionFeltDetails);
		}
		
		return emotionFeltDetailsList;
	}
	
	/**
	 * 
	 * @return
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
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
        
        emotionFeltList.clear();
        fillList();
        
        return true;
    }
    
	/**
	 * 
	 */
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        
        for(EmotionFelt emotionFelt : emotionFeltList.values()) {
        	//(emozioneId, score, note, canzoneId, userId)
            line = emotionFelt.getEmotionId() + ";" 
                    + emotionFelt.getScore() + ";"
                    + emotionFelt.getNote() + ";"
                    + emotionFelt.getSongId() + ";"
                    + emotionFelt.getUserId();

            lines.add(line);
        }        
    }
    
    /**
     * 
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    private void fillList() throws Exception{
        lines.clear();
        
        try {
            lines = Files.readAllLines(new File(FILEPATH).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        EmotionFelt emotionFelt;

        for(String line : lines) {
            emotionFelt = new EmotionFelt(); //do this for cache problems
            
            String[] strs = line.split(";");
            if(strs.length > 0){
            	if(strs[0] != "" && strs[0] != null) {
            		emotionFelt.setEmotionId(Long.valueOf(strs[0]));
            	}
            	if(strs[1] != "" && strs[1] != null) {
            		emotionFelt.setScore(Integer.valueOf(strs[1]));            		
            	}
            	
            	emotionFelt.setNote(strs[2]);            		

            	if(strs[3] != "" && strs[3] != null) {
            		emotionFelt.setSongId(Long.valueOf(strs[3]));            		
            	}
            	if(strs[4] != "" && strs[4] != null) {
            		emotionFelt.setUserId(Long.valueOf(strs[4]));            		
            	}
                
                emotionFelt.setEmotionFeltId(strs[0] + "_" + strs[3] + "_" + strs[4]);
                
                emotionFeltList.putIfAbsent(emotionFelt.getEmotionFeltId(), emotionFelt);
            }
            emotionFelt = null; //do this for cache problems
        }    
    }

}
