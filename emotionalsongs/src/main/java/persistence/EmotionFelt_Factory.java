package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import object.Emotion;
import object.EmotionFelt;
import object.Song;

public class EmotionFelt_Factory implements IGeneric_Factory<EmotionFelt, String>{
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\emotionFelt_data.csv";
    private static EmotionFelt_Factory istance = null;
    
    private Map<String, EmotionFelt> emotionFeltList;
    private List<String> lines;
    
    private EmotionFelt_Factory() throws Exception {
    	this.emotionFeltList = new HashMap<>();
        this.lines = new ArrayList<>();
        
        fillList();
    }
    
    public static EmotionFelt_Factory getIstance() throws Exception {
    	if(istance == null){
            return new EmotionFelt_Factory();
        } else return istance;
    }

	@Override
	public void create(EmotionFelt emotionFelt) throws Exception, IOException {
		emotionFeltList.putIfAbsent(emotionFelt.getEmotionFeltId(), emotionFelt);
		save();
	}

	@Override
	public EmotionFelt getById(String id) throws Exception {
		return emotionFeltList.get(id);
	}

	@Override
	public void update(EmotionFelt emotionFelt) throws Exception, IOException {
		emotionFeltList.remove(emotionFelt.getEmotionFeltId());
		emotionFeltList.put(emotionFelt.getEmotionFeltId(), emotionFelt);
		save();
	}

	@Override
	public void delete(EmotionFelt emotionFelt) throws Exception {
		emotionFeltList.remove(emotionFelt.getEmotionFeltId());
		save();
	}

	@Override
	public Map<String, EmotionFelt> listAll() {
		return emotionFeltList;
	}
	
	public List<EmotionFelt> listAllByUser(Long userId) {
		List<EmotionFelt> userEmotions = new ArrayList<>();
		for(EmotionFelt emotionFelt : emotionFeltList.values()) {
			if(emotionFelt.getUserId() == userId) {
				userEmotions.add(emotionFelt);
			}
		}
		
		return userEmotions;
	}
	
	public List<EmotionFelt> listAllBySongId(Long songId) {
		List<EmotionFelt> songEmotions = new ArrayList<>();
		for(EmotionFelt emotionFelt : emotionFeltList.values()) {
			if(emotionFelt.getSongId() == songId) {
				songEmotions.add(emotionFelt);
			}
		}
		
		return songEmotions;
	}
	
	private class EmotionScore{
		public Long emotionId;
		public int scoreSum;
		public int counter;
		public Double score;
		
	}
	
	public Map<Long, Double> getEmotionAndRelativeScoreBySongId(Long songId) {
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
		
		Map<Long, Double> score = new HashMap<>();
		for(EmotionScore eScore : songEmotions.values()) {
			score.put(eScore.emotionId, eScore.score);
		}
		
		return score;
	}
	
	public void updateEmotionsData(Map<Long, Double> emotionsData, Song song) {
		for(int i = 0; i < emotionsData.size(); i++) {
			song.getEmotionList().replace(Emotion.getEmotionsList().get(i), emotionsData.get(i));
		}
		
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
        
        emotionFeltList.clear();
        fillList();
        
        return true;
    }
    
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
                emotionFelt.setEmotionId(Long.valueOf(strs[0]));
                emotionFelt.setScore(Integer.valueOf(strs[1]));
                emotionFelt.setNote(strs[2]);
                emotionFelt.setSongId(Long.valueOf(strs[3]));
                emotionFelt.setUserId(Long.valueOf(strs[4]));
                
                emotionFelt.setEmotionFeltId(strs[0] + "_" + strs[3] + "_" + strs[4]);
                
                emotionFeltList.putIfAbsent(emotionFelt.getEmotionFeltId(), emotionFelt);
            }
            emotionFelt = null; //do this for cache problems
        }    
    }

}
