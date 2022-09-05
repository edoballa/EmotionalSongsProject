/**
* <p>This package contains the classes that instantiate the objects.</p>
*/
package objects;

/**
* <p>This class define what is an emotion</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class Emotion {
	/**
	 * <code>emotionId</code>
	 * A Long to keep track of the id of an emotion.
	 */
    private Long emotionId;
    /**
     * <code>name</code>
	 * A String to store the emotion's name.
	 */
    private String name;
    /**
     * <code>description</code>
	 * A String to store the emotion's description.
	 */
    private String description;

    /**
     * Emotion default constructor.
     */
    public Emotion() {
    }

    /**
     * Emotion constructor with all fields as parameters.
     * 
     * @param emotionId The emotion's id.
     * @param name The emotion's name.
     * @param description The description of the emotion.
     */
    public Emotion(Long emotionId, String name, String description) {
        this.emotionId = emotionId;
        this.name = name;
        this.description = description;
    }

    /**
     * This method return the emotionId field.
     * @return Long the emotionId.
     */
    public Long getEmotionId() {
        return this.emotionId;
    }

    /**
     * This method return the name field.
     * @return String the name of the emotion.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method return the description field.
     * @return String the description of the emotion.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * emotionId setter method.
     * @param emotionId The emotionId to set.
     */
    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    /**
     * name setter method.
     * @param name The name of the emotion to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * description setter method.
     * @param description The description of the emotion to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
