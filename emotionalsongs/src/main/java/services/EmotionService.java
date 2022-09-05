/**
* This package contains the classes that implements the actions calling the factory. 
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package services;

import java.io.IOException;
import java.util.HashMap;
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
	/**
	 * <code>emotionFeltFactory</code>
	 * A EmotionFelt_Factory object use to invoke the methods of that class.
	 */
	private EmotionFelt_Factory emotionFeltFactory;
	/**
	 * <code>songFactory</code>
	 * A Song_Factory object use to invoke the methods of that class.
	 */
	private Song_Factory songFactory;
	
	/**
	 *  EmotionService default constructor.
	 *  
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public EmotionService() throws Exception {
		this.emotionFeltFactory = EmotionFelt_Factory.getIstance();
		this.songFactory = Song_Factory.getIstance();
	}
	
	/**
	 * This method insert a new emotion felt by the user in the list with the other.
	 * 
	 * @param <emotionFelt> The emotion felt by a user to insert.
	 * @throws <IOException> This exception is thrown if there is any input/output error.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public void insertNewEmotion(EmotionFelt emotionFelt) throws IOException, Exception {
		emotionFelt.calculateEmotionFeltId();
		emotionFeltFactory.create(emotionFelt);
		List<EmotionFeltDetails> emotionsData = emotionFeltFactory.getEmotionAndRelativeScoreBySongId(emotionFelt.getSongId());
		Song song = songFactory.getById(emotionFelt.getSongId());
		song.setEmotionList(emotionsData);
		songFactory.updateSongInList(song);
	}
	
	/**
	 * This method allows you to insert a new emotion felt by the user with the associated data.
	 * If the emotion felt by the user already exists, it is possible to update the associated data.
	 * 
	 * @param <cmdInput> User's input from cmd.
	 * @param <newEmotion> A Boolean to check if the emotion is new or already exists.
	 * @param <songId> The song's id
	 * @param <emotionByUserAndSong> A Map of EmotionFelt.
	 * @return A EmotionFelt object.
	 */
	public EmotionFelt insertEmotionData(Scanner cmdInput, boolean newEmotion, Long songId, Map<String, EmotionFelt> emotionByUserAndSong) {
		EmotionFelt ef = new EmotionFelt();
		
		if(newEmotion) {
			Emotions.printAllEmotions();
			do {
				System.out.print("Emozione: ");
				String emotion = cmdInput.nextLine();
				
				if(emotion.isBlank()) {
					System.out.println("Inserire un valore.");
					continue;
				}
				
				if(emotion.equals("1") || emotion.equals("2") || emotion.equals("3") || emotion.equals("4") || emotion.equals("5") 
						|| emotion.equals("6") || emotion.equals("7") || emotion.equals("8") || emotion.equals("9")) {
					
					if(songId != null && !emotionByUserAndSong.isEmpty()) {
						boolean existEmotionYet = false;
						for(EmotionFelt em : emotionByUserAndSong.values()) {
							if(em.getSongId() == songId) {
								existEmotionYet = false;
							}
						}
						
						if(!existEmotionYet) {
							System.out.println("É già presente un'emozione uguale per questa canzone, inserirne una diversa.");
							continue;
						} 
					}
					
					ef.setEmotionId(Long.valueOf(emotion));
					
				}
			} while(ef.getEmotionId() == null);
		}
		
		do {
			System.out.print("Valutazione (da 1 a 5): ");
			String rating = cmdInput.nextLine();
			
			if(rating.isBlank()) {
				System.out.println("Inserire un valore.");
				continue;
			}
			
			if(rating.length() == 1 && (rating.equals("1") || rating.equals("2") || rating.equals("3") 
					|| rating.equals("4") || rating.equals("5") )) {
				ef.setScore(Integer.valueOf(rating));
			}
			
		}while(ef.getScore() == 0);
		
		System.out.print("Commento (max 256 caratteri): ");
		String comment = cmdInput.nextLine();

		if(comment.length() > 256) {
			ef.setNote(comment.substring(0, 255));
		} else if(comment.isBlank()) {
			ef.setNote("");
		} else ef.setNote(comment);
				
		return ef;
	}
	
	/**
	 * This method prints the emotions felt by users associated with a song.
	 * 
	 * @param <efMap> A Map of EmotionFelt.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
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
	
	/**
	 * This method search, in an EmotionFelt map, the emotion corresponding to the emotionFeltCode insert by the user.
	 * 
	 * @param <cmdInput> User's input from cmd.
	 * @param <emotionFeltMap> A Map of EmotionFelt.
	 * @return A String that contain the code of the emotion felt.
	 */
	public String selectEmotionFelt(Scanner cmdInput, Map<String, EmotionFelt> emotionFeltMap) {
		String emotionFeltCode = "";
        boolean validCode = false;
        
        while(!validCode) {
        	System.out.print("Inserire il codice dell'emozione: ");
        	emotionFeltCode = cmdInput.nextLine();
        	
        	if(emotionFeltCode.isBlank()) {
				System.out.println("Inserire un valore.");
				continue;
			}
        	
        	if(emotionFeltMap.containsKey(emotionFeltCode)) {
        		validCode = true;
        	}
        }

        return emotionFeltCode;
	}
	
	/**
	 * This method delete an EmotionFelt from the map based on its id.
	 * 
	 * @param <emotionFeltCode> The id of the emotion felt.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public void deleteEmotionFelt(String emotionFeltCode) throws Exception {
		EmotionFelt ef = emotionFeltFactory.getById(emotionFeltCode);
		emotionFeltFactory.delete(ef);
	}
	
	/**
	 * This method return a Map with all the emotions felt by the user.
	 * 
	 * @param <userId> The user's id.
	 * @return A map of EmotionFelt.
	 */
	public Map<String, EmotionFelt> getUserEmotion(Long userId){
		return emotionFeltFactory.listAllByUser(userId);
	}
	
	/**
	 * This method returns a map containing the emotions felt based on the user and song id.
	 * 
	 * @param <userId> The user's id.
	 * @param <songId> The song's id.
	 * @return A Map of EmotionFelt.
	 */
	public Map<String, EmotionFelt> getSongUserEmotion(Long userId, Long songId){
		Map<String, EmotionFelt> emotionByUserAndSong = new HashMap<String, EmotionFelt>();
		for(EmotionFelt ef : emotionFeltFactory.listAllByUser(userId).values()) {
			if(ef.getSongId() == songId) {
				emotionByUserAndSong.put(ef.getEmotionFeltId(), ef);
			}
		}
		
		return emotionByUserAndSong;
	}
	
	/**
	 * This method update an emotion felt by the user in the map.
	 * 
	 * @param <ef> The emotion felt by the user to update.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
	public void updateEmotionFelt(EmotionFelt ef) throws Exception {
		emotionFeltFactory.update(ef);
	}
}
