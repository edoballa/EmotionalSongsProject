package services;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import objects.EmotionFelt;
import persistence.EmotionFelt_Factory;
import persistence.Song_Factory;
import persistence.User_Factory;

public class Emotion_Service {
	private EmotionFelt_Factory emotionFeltFactory;
	private Song_Factory songFactory;
	private User_Factory userFactory;
	
	public Emotion_Service() throws Exception {
		this.emotionFeltFactory = EmotionFelt_Factory.getIstance();
		this.songFactory = Song_Factory.getIstance();
		this.userFactory = User_Factory.getIstance();
	}
	
	public void insertNewEmotion(EmotionFelt emotionFelt) throws IOException, Exception {
		emotionFeltFactory.create(emotionFelt);
		Map<Long, Double> emotionsData = emotionFeltFactory.getEmotionAndRelativeScoreBySongId(emotionFelt.getSongId());
		emotionFeltFactory.updateEmotionsData(emotionsData, songFactory.getById(emotionFelt.getSongId()));
	}
	
	public boolean checkData(String note, Long songId, Long userId, int score) throws Exception {
		if(songFactory.getById(songId) == null) {
			return false;
		}
		
		if(userFactory.getById(userId) == null) {
			return false;
		}
		
		if(score < 0 || score > 5) {
			return false;
		}
		
		if(StringUtils.isNotEmpty(note)) {
			note = StringUtils.substring(note, 0, 255);
		}
		
		return true;
	}
}
