package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Emotion {
    private Long emotionId;
    private String name;
    private String description;

    public Emotion() {

    }

    public Emotion(Long emotionId, String name, String description) {
        this.emotionId = emotionId;
        this.name = name;
        this.description = description;
    }

    public Long getEmotionId() {
        return this.emotionId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
