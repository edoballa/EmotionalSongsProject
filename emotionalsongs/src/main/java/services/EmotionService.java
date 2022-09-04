package services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import objects.EmotionFelt;
import objects.EmotionFeltDetails;
import objects.Emotions;
import objects.Song;
import persistence.EmotionFelt_Factory;
import persistence.Song_Factory;

public class EmotionService {
	private EmotionFelt_Factory emotionFeltFactory;
	private Song_Factory songFactory;
	
	public EmotionService() throws Exception {
		this.emotionFeltFactory = EmotionFelt_Factory.getIstance();
		this.songFactory = Song_Factory.getIstance();
	}
	
	public void insertNewEmotion(EmotionFelt emotionFelt) throws IOException, Exception {
		emotionFelt.calculateEmotionFeltId();
		emotionFeltFactory.create(emotionFelt);
		List<EmotionFeltDetails> emotionsData = emotionFeltFactory.getEmotionAndRelativeScoreBySongId(emotionFelt.getSongId());
		Song song = songFactory.getById(emotionFelt.getSongId());
		song.setEmotionList(emotionsData);
		songFactory.updateSongInList(song);
	}
	
	public EmotionFelt insertEmotionData(Scanner cmdInput, boolean newEmotion) {
		Emotions.printAllEmotions();
		EmotionFelt ef = new EmotionFelt();
		
		if(newEmotion) {
			do {
				System.out.print("Emozione: ");
				String emotion = cmdInput.nextLine();
				if(emotion.equals("1") || emotion.equals("2") || emotion.equals("3") || emotion.equals("4") || emotion.equals("5") 
						|| emotion.equals("6") || emotion.equals("7") || emotion.equals("8") || emotion.equals("9")) {
					ef.setEmotionId(Long.valueOf(emotion));
				}
			} while(ef.getEmotionId() == null);
		}
		
		do {
			System.out.print("Valutazione: ");
			String rating = cmdInput.nextLine();
			
			if(rating.length() == 1 && (rating.equals("1") || rating.equals("2") || rating.equals("3") 
					|| rating.equals("4") || rating.equals("5") )) {
				ef.setScore(Integer.valueOf(rating));
			}
			
		}while(ef.getScore() == 0);
		
		System.out.print("Commento (max 256 caratteri): ");
		String comment = cmdInput.nextLine();

		ef.setNote(comment.substring(0, 255));
		return ef;
	}
	
	public void printEmotionList(Map<String, EmotionFelt> efMap) throws Exception {
		for(EmotionFelt ef : efMap.values()) {
			Song song = songFactory.getById(ef.getSongId());
			System.out.print(ef.getEmotionFeltId() + " " + song.getTitle() + "(" + song.getAuthor() + ")");
			System.out.print("Emozione: " + Emotions.getNameById(ef.getEmotionId()) + ", Valutazione: " + ef.getScore());
			if(ef.getNote() != null && !ef.getNote().isEmpty()) {
				System.out.println(", Commento: " + ef.getNote());
			} else System.out.println("");
		}
	}
	
	public String selectEmotionFelt(Scanner cmdInput, Map<String, EmotionFelt> emotionFeltMap) {
		String emotionFeltCode = "";
        boolean validCode = false;
        
        while(!validCode) {
        	System.out.print("Inserire il codice dell'emozione: ");
        	emotionFeltCode = cmdInput.nextLine();
        	if(emotionFeltMap.containsKey(emotionFeltCode)) {
        		validCode = true;
        	}
        }

        return emotionFeltCode;
	}
	
	public void deleteEmotionFelt(String emotionFeltCode) throws Exception {
		EmotionFelt ef = emotionFeltFactory.getById(emotionFeltCode);
		emotionFeltFactory.delete(ef);
	}
	
	public Map<String, EmotionFelt> getUserEmotion(Long userId){
		return emotionFeltFactory.listAllByUser(userId);
	}
	
	public void updateEmotionFelt(EmotionFelt ef) throws Exception {
		emotionFeltFactory.update(ef);
	}
}
