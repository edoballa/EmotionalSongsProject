package services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import objects.EmotionFelt;
import objects.EmotionFeltDetails;
import objects.Emotions;
import objects.Song;
import persistence.EmotionFelt_Factory;
import persistence.Song_Factory;
import persistence.User_Factory;

public class EmotionService {
	private EmotionFelt_Factory emotionFeltFactory;
	private Song_Factory songFactory;
	private User_Factory userFactory;
	
	public EmotionService() throws Exception {
		this.emotionFeltFactory = EmotionFelt_Factory.getIstance();
		this.songFactory = Song_Factory.getIstance();
		this.userFactory = User_Factory.getIstance();
	}
	
	public void insertNewEmotion(EmotionFelt emotionFelt) throws IOException, Exception {
		emotionFelt.calculateEmotionFeltId();
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
	
	public EmotionFelt insertEmotionData(Scanner cmdInput) {
		Emotions.printAllEmotions();
		EmotionFelt ef = new EmotionFelt();
		
		do {
			System.out.print("Emozione: ");
			String emotion = cmdInput.nextLine();
			if(emotion == "1" || emotion == "2" || emotion == "3" || emotion == "4" || emotion == "5" 
					|| emotion == "6" || emotion == "7" || emotion == "8" || emotion == "9") {
				ef.setEmotionId(Long.valueOf(emotion));
			}
		} while(ef.getEmotionId() == null);
		
		do {
			System.out.print("Valutazione: ");
			String rating = cmdInput.nextLine();
			
			if(rating.length() == 1 && (rating == "0" || rating == "1" || rating == "2" || rating == "3" 
					|| rating == "4" || rating == "5" || rating == "6" || rating == "7" || rating == "8" 
					|| rating == "9")) {
				ef.setScore(Integer.valueOf(rating));
			}
			
		}while(ef.getScore() == 0);
		
		String comment;
		do {
			System.out.print("Commento (max 256 caratteri): ");
			comment = cmdInput.nextLine();
			
		}while(comment.length() > 256);

		return ef;
		
	}
}
