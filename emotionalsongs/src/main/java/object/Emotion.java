package object;

import java.util.ArrayList;
import java.util.List;

public class Emotion {
	public static final Long STUPORE = 1L;
	public static final Long SOLENNITA = 2L;
	public static final Long TENEREZZA = 3L;
	public static final Long NOSTALGIA = 4L; 
	public static final Long CALMA = 5L;
	public static final Long POTENZA = 6L;
	public static final Long GIOIA = 7L;
	public static final Long TENSIONE = 8L; 
	public static final Long TRISTEZZA = 9L;
	
	private Long emotionId;
	private String name;
	private String description;
	private List<Emotion> emotionsList;
	
	private Emotion(Long emotionId, String name, String description) {
		this.emotionId = emotionId;
		this.name = name;
		this.description = description;
	}
	
	public Emotion() {
		this.emotionsList = new ArrayList<>();
		
		this.emotionsList.add(new Emotion(STUPORE, "Stupore", "Sensazione di meraviglia o felicità"));
		this.emotionsList.add(new Emotion(SOLENNITA, "Solennità", "Sensazione di trascendenza, ispirazione."));
		this.emotionsList.add(new Emotion(TENEREZZA, "Tenerezza", "Sensualità, affetto, sentimento d'amore"));
		this.emotionsList.add(new Emotion(NOSTALGIA, "Nostalgia", "Sensazioni sognanti, malinconiche, sentimentali"));
		this.emotionsList.add(new Emotion(CALMA, "Calma", "Relax, serenità, meditazione"));
		this.emotionsList.add(new Emotion(POTENZA, "Potenza", "Sentirsi forte, eroico, trionfante, energico"));
		this.emotionsList.add(new Emotion(GIOIA, "Gioia", "Voglia di ballare, sentirsi animato, divertito, vivo"));
		this.emotionsList.add(new Emotion(TENSIONE, "Tensione", "Sentirsi nervosi, impazienti, irritati"));
		this.emotionsList.add(new Emotion(TRISTEZZA, "Tristezza", "Sentirsi depresso, addolorato"));
		
	}
	
	public List<Emotion> getEmotionsList(){
		return this.emotionsList;
	}
	
	
	
}
