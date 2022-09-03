package services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import objects.EmotionFelt;
import objects.EmotionFeltDetails;
import objects.Song;
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
		List<EmotionFeltDetails> emotionsData = emotionFeltFactory.getEmotionAndRelativeScoreBySongId(emotionFelt.getSongId());
		Song song = songFactory.getById(emotionFelt.getSongId());
		song.setEmotionList(emotionsData);
		songFactory.updateSongInList(song);
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
