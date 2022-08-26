package objects;

import java.util.HashMap;
import java.util.Map;

public class Song{
    //(titolo, autore, genere, anno, List<emozione>)
    private Long songId;
    private String title;
    private String author;
    private String musicalGenre;
    private String year;
    private Map<Emotion, Double> emotionList; 

    public Song() {
        this.emotionList = new HashMap<>();
    }

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

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMusicalGenre() {
        return musicalGenre;
    }

    public void setMusicalGenre(String musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Map<Emotion, Double> getEmotionList() {
        return emotionList;
    }

    public void setEmotionList(Map<Emotion, Double> emotionList) {
        this.emotionList = emotionList;
    }
}
