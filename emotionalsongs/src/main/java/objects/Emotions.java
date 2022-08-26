/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * @author Diana Cantaluppi Matr. 744457
 * @author Edoardo Ballabio Matr. 
 */
public class Emotions {

    public static final Long STUPORE = 1L;
    public static final Long SOLENNITA = 2L;
    public static final Long TENEREZZA = 3L;
    public static final Long NOSTALGIA = 4L;
    public static final Long CALMA = 5L;
    public static final Long POTENZA = 6L;
    public static final Long GIOIA = 7L;
    public static final Long TENSIONE = 8L;
    public static final Long TRISTEZZA = 9L;

    public static String getDescriptionById(Long id) {
        switch (id.intValue()) {
            case 1:
                return "Sensazione di meraviglia o felicita";
            case 2:
                return "Sensazione di trascendenza, ispirazione.";
            case 3:
                return "Sensualità, affetto, sentimento d'amore";
            case 4:
                return "Sensazioni sognanti, malinconiche, sentimentali";
            case 5:
                return "Relax, serenità , meditazione";
            case 6:
                return "Sentirsi forte, eroico, trionfante, energico";
            case 7:
                return "Voglia di ballare, sentirsi animato, divertito, vivo";
            case 8:
                return "Sentirsi nervosi, impazienti, irritati";
            case 9:
                return "Sentirsi depresso, addolorato";
        }
        return "";
    }
    
    public static String getNameById(Long id) {
        switch (id.intValue()) {
            case 1:
                return "Stupore";
            case 2:
                return "Solennità ";
            case 3:
                return "Tenerezza";
            case 4:
                return "Nostalgia";
            case 5:
                return "Calma";
            case 6:
                return "Potenza";
            case 7:
                return "Gioia";
            case 8:
                return "Tensione";
            case 9:
                return "Tristezza";
        }
        return "";
    }
    
    public static Emotion getEmotionById(Long id){
        Emotion e = new Emotion();
        e.setEmotionId(id);
        e.setName(getNameById(id));
        e.setDescription(getDescriptionById(id));
        
        return e;
    }
}
