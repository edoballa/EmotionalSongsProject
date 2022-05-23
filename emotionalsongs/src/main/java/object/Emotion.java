package object;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private static List<Emotion> emotionsDetailsList;
	
	private Emotion(Long emotionId, String name, String description) {
		this.emotionId = emotionId;
		this.name = name;
		this.description = description;
	}
	
	public static List<Emotion> getEmotionsList() {
		if(emotionsDetailsList == null) {
			return new Emotion().emotionsDetailsList;
		} else {
			return emotionsDetailsList;
		}
	}
	
	private Emotion() {
		this.emotionsDetailsList = new ArrayList<>();
		
		this.emotionsDetailsList.add(new Emotion(STUPORE, "Stupore", "Sensazione di meraviglia o felicità"));
		this.emotionsDetailsList.add(new Emotion(SOLENNITA, "Solennità", "Sensazione di trascendenza, ispirazione."));
		this.emotionsDetailsList.add(new Emotion(TENEREZZA, "Tenerezza", "Sensualità, affetto, sentimento d'amore"));
		this.emotionsDetailsList.add(new Emotion(NOSTALGIA, "Nostalgia", "Sensazioni sognanti, malinconiche, sentimentali"));
		this.emotionsDetailsList.add(new Emotion(CALMA, "Calma", "Relax, serenità, meditazione"));
		this.emotionsDetailsList.add(new Emotion(POTENZA, "Potenza", "Sentirsi forte, eroico, trionfante, energico"));
		this.emotionsDetailsList.add(new Emotion(GIOIA, "Gioia", "Voglia di ballare, sentirsi animato, divertito, vivo"));
		this.emotionsDetailsList.add(new Emotion(TENSIONE, "Tensione", "Sentirsi nervosi, impazienti, irritati"));
		this.emotionsDetailsList.add(new Emotion(TRISTEZZA, "Tristezza", "Sentirsi depresso, addolorato"));
		
	}
	
	public Emotion getEmotionId(Long i) {
		return (Emotion) emotionsDetailsList.stream().filter(e -> e.emotionId == i).collect(Collectors.toSet());
	}
}
