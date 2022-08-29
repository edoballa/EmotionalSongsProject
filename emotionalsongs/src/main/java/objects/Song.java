/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi Matr. 744457.
* @author Edoardo Ballabio Matr. 745115.
*/
package objects;

import java.util.HashMap;
import java.util.Map;

public class Song{
    /**
     * A Long to keep track of the song's id.
     */
    private Long songId;
    /**
	 * A String to store the song's title.
	 */
    private String title;
    /**
	 * A String to store the song's author.
	 */
    private String author;
    /**
	 * A String to store the song's musical genre.
	 */
    private String musicalGenre;
    /**
	 * A String to store the song's year.
	 */
    private String year;
    /**
     * A Map of Emotion to store all the type of emotions.
     */
    private Map<Emotion, Double> emotionList; 

    /**
     * Song default constructor.
     */
    public Song() {
        this.emotionList = new HashMap<>();
    }

    /**
     * Song constructor with all fields as parameters.
     * 
     * @param songId The song's id.
     * @param title The song's title.
     * @param author The song's author.
     * @param musicalGenre The song's musical genre.
     * @param year The song's year.
     * @param emotionList The list of emotions.
     */
    public Song(Long songId, String title, String author, String musicalGenre, String year, Map<Emotion, Double> emotionList) {
        this.songId = songId;
        this.title = title;
        this.author = author;
        this.musicalGenre = musicalGenre;
        this.year = year;
        if(emotionList == null)
            this.emotionList = emotionList;
        else this.emotionList = new HashMap<>();
    }

    /**
     * songId setter method.
     * @param songId The songId to set.
     */
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    /**
     * This method return the songId field.
     * @return Long the songId.
     */
    public Long getSongId() {
        return songId;
    }

    /**
     * This method return the title field.
     * @return String the song's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * title setter method.
     * @param title The song's title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method return the author field.
     * @return String the song's author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * author setter method.
     * @param author The song's author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method return the musicalGenre field.
     * @return String the song's musical genre.
     */
    public String getMusicalGenre() {
        return musicalGenre;
    }

    /**
     * musicalGenre setter method.
     * @param musicalGenre The song's musical genre to set.
     */
    public void setMusicalGenre(String musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    /**
     * This method return the year field.
     * @return String the song's year.
     */
    public String getYear() {
        return year;
    }

    /**
     * year setter method.
     * @param year The song's year to set.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * This method return a Map of Emotion.
     * @return Map<Emotion, Double> the List of Emotions.
     */
    public Map<Emotion, Double> getEmotionList() {
        return emotionList;
    }

    /**
     * emotionList setter method.
     * @param emotionList The list of emotions to set.
     */
    public void setEmotionList(Map<Emotion, Double> emotionList) {
        this.emotionList = emotionList;
    }
}

