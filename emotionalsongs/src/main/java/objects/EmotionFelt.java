/**
* <p>This package contains the classes that instantiate the objects.</p>
*/
package objects;

/**
* <p>This class define what is an emotion felt</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class EmotionFelt  {
	/**
	 * <code>emotionFeltId</code>
	 * A String to store the emotionFeltId.
	 */
    private String emotionFeltId;
    /**
     * <code>emotionId</code>
	 * A Long to keep track of the id of the emotion felt.
	 */
    private Long emotionId;
    /**
     * <code>note</code>
	 * A String to store the note about the emotion felt.
	 */
    private String note;
    /**
     * <code>songId</code>
	 * A Long to keep track of the song's id.
	 */
    private Long songId;
    /**
     * <code>userId</code>
	 * A Long to keep track of the user's id.
	 */
    private Long userId;
    /**
     * <code>score</code>
     * An integer to keep track of the score about the emotion felt.
     */
    private int score;

    /**
     * EmotionFelt default constructor.
     */
    public EmotionFelt() {
    	this.score = 0;
    }

    /**
     * EmotionFelt constructor with all fields as parameters.
     * 
     * @param emotionId The emotion's id.
     * @param note The note about the emotion felt.
     * @param songId The song's id.
     * @param userId The user's id.
     * @param score The score about the emotion felt.
     */
    public EmotionFelt(Long emotionId, String note, Long songId, Long userId, int score) {
        this.emotionId = emotionId;
        this.note = note;
        this.songId = songId;
        this.userId = userId;
        this.emotionFeltId = emotionId + "_" + songId + "_" + userId;
        this.score = score;
    }

    /**
     * This method return the emotionId field.
     * @return Long the emotionId.
     */
    public Long getEmotionId() {
        return emotionId;
    }

    /**
     * emotionId setter method.
     * @param emotionId The emotionId to set.
     */
    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    /**
     * This method return the note field.
     * @return String the note.
     */
    public String getNote() {
        return note;
    }

    /**
     * note setter method.
     * @param note The note to set.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * This method return the songId field.
     * @return Long the songId.
     */
    public Long getSongId() {
        return songId;
    }

    /**
     * songId setter method.
     * @param songId The songId to set.
     */
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    /**
     * This method return the userId field.
     * @return Long the userId.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * userId setter method.
     * @param userId The userId to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method return the emotionFeltId field.
     * @return String the emotionFeltId.
     */
    public String getEmotionFeltId() {
        return this.emotionFeltId;
    }

    /**
     * emotionFeltId setter method.
     * @param emotionFeltId The emotionFeltId to set.
     */
    public void setEmotionFeltId(String emotionFeltId) {
        this.emotionFeltId = emotionFeltId;
    }

    /**
     * This method return the score field.
     * @return integer the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * score setter method.
     * @param score The score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This method return a concatenation of the emotion, song and user ids.
     * @return String the emotionFeltId.
     */
    public String calculateEmotionFeltId() {
    	this.emotionFeltId = this.emotionId + "_" + this.songId + "_" + this.userId;
        return emotionFeltId;
    }
}
