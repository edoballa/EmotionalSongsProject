package object;

public class EmotionFelt {
	//(emozioneId, score, note, canzoneId, userId)
	private String emotionFeltId;
	private Long emotionId;
	private String note;
	private Long songId;
	private Long userId;
	private int score;
	
	public EmotionFelt() {
		
	}
	
	public EmotionFelt(Long emotionId, String note, Long songId, Long userId, int score) {
		this.emotionId = emotionId;
		this.note = note;
		this.songId = songId;
		this.userId = userId;
		this.emotionFeltId = emotionId + "_" + songId + "_" + userId;
		this.score = score;
	}

	public Long getEmotionId() {
		return emotionId;
	}

	public void setEmotionId(Long emotionId) {
		this.emotionId = emotionId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getEmotionFeltId() {
		return this.emotionFeltId;
	}
	
	public void setEmotionFeltId(String emotionFeltId) {
		this.emotionFeltId = emotionFeltId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
