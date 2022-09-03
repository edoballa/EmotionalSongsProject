package objects;

import java.util.ArrayList;
import java.util.List;

public class EmotionFeltDetails {
	private Emotion emotion;
	private Double averageOfRatings;
	private int numberOfRatings;
	private List<String> comments;
	
	public EmotionFeltDetails() {
		comments = new ArrayList<String>();
	}
	
	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	public Double getAverageOfRatings() {
		return averageOfRatings;
	}
	
	public void setAverageOfRatings(Double averageOfRatings) {
		this.averageOfRatings = averageOfRatings;
	}
	
	public int getNumberOfRatings() {
		return numberOfRatings;
	}
	
	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}
	
	public List<String> getComments() {
		return comments;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	
}
