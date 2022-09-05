/**
* <p>This package contains the classes that instantiate the objects.</p>
*/
package objects;

import java.util.ArrayList;
import java.util.List;

/**
* <p>This class define what is an emotion felt details</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class EmotionFeltDetails {
	/**
	 * <code>emotion</code>
	 * An Emotion object to store the emotion data.
	 */
	private Emotion emotion;
	/**
	 * <code>averageOfRatings</code>
	 * A Double to keep track of the average of the ratings of an emotion.
	 */
	private Double averageOfRatings;
	/**
	 * <code>numberOfRatings</code>
	 * An Integer to keep track of the number of ratings given to an emotion.
	 */
	private int numberOfRatings;
	/**
	 * <code>comments</code>
	 * A List of String to store the comments about the songs.
	 */
	private List<String> comments;
	
	/**
	 * EmotionFeltDetails default constructor.
	 */
	public EmotionFeltDetails() {
		comments = new ArrayList<String>();
	}
	
	/**
     * This method return the emotion object.
     * @return Emotion the object emotion.
     */
	public Emotion getEmotion() {
		return emotion;
	}

	/**
     * emotion setter method.
     * @param emotion The emotion to set.
     */
	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	/**
     * This method return the averageOfRatings field.
     * @return Double the average of the ratings about an emotion.
     */
	public Double getAverageOfRatings() {
		return averageOfRatings;
	}
	
	/**
     * averageOfRatings setter method.
     * @param averageOfRatings The average of ratings to set.
     */
	public void setAverageOfRatings(Double averageOfRatings) {
		this.averageOfRatings = averageOfRatings;
	}
	
	/**
     * This method return the numberOfRatings field.
     * @return Integer the number of ratings given to an emotion.
     */
	public int getNumberOfRatings() {
		return numberOfRatings;
	}
	
	/**
     * numberOfRatings setter method.
     * @param numberOfRatings The number of ratings to set.
     */
	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}
	
	/**
     * This method return a List of String that contains the comments relating to an emotion felt while listening to a song.
     * @return a List of comments.
     */
	public List<String> getComments() {
		return comments;
	}
	
	/**
     * comments setter method.
     * @param comments The emotion's comments to set.
     */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
}
