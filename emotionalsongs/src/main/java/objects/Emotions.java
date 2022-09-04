/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package objects;

public class Emotions {
	/**
	 * <code>STUPORE</code>
	 * A Long with a value of 1L, that representing the emotion of amazement.
	 */
    public static final Long STUPORE = 1L;
    /**
     * <code>SOLENNITA</code>
	 * A Long with a value of 2L, that representing the emotion of solemnity.
	 */
    public static final Long SOLENNITA = 2L;
    /**
     * <code>TENEREZZA</code>
	 * A Long with a value of 3L, that representing the emotion of tenderness.
	 */
    public static final Long TENEREZZA = 3L;
    /**
     * <code>NOSTALGIA</code>
	 * A Long with a value of 4L, that representing the emotion of nostalgia.
	 */
    public static final Long NOSTALGIA = 4L;
    /**
     * <code>CALMA</code>
	 * A Long with a value of 5L, that representing the emotion of calmness.
	 */
    public static final Long CALMA = 5L;
    /**
     * <code>POTENZA</code>
     * A Long with a value of 6L, that representing th emotion of power.
     */
    public static final Long POTENZA = 6L;
    /**
     * <code>GIOIA</code>
     * A Long with a value of 7L, that representing th emotion of joy.
     */
    public static final Long GIOIA = 7L;
    /**
     * <code>TENSIONE</code>
     * A Long with a value of 8L, that representing th emotion of tension.
     */
    public static final Long TENSIONE = 8L;
    /**
     * <code>TRISTEZZA</code>
     * A Long with a value of 9L, that representing th emotion of sadness.
     */
    public static final Long TRISTEZZA = 9L;

    /**
     * This method return the description based on emotion.
     * @param <id> The emotion's id.
     * @return String the description of the emotion.
     */
    public static String getDescriptionById(Long id) {
        switch (id.intValue()) {
            case 1:
                return "Sensazione di meraviglia o felicita";
            case 2:
                return "Sensazione di trascendenza, ispirazione.";
            case 3:
                return "Sensualità , affetto, sentimento d'amore";
            case 4:
                return "Sensazioni sognanti, malinconiche, sentimentali";
            case 5:
                return "Relax, serenità, meditazione";
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
    
    /**
     * This method return the name of the emotion associate with a given id.
     * @param <id> The emotion's id.
     * @return String the name of the emotion.
     */
    public static String getNameById(Long id) {
        switch (id.intValue()) {
            case 1:
                return "Stupore";
            case 2:
                return "Solennità";
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
    
    /**
     * This method return an Emotion object that correspond to the id passed as a parameter.
     * @param <id> The emotion's id.
     * @return Emotion the Emotion object with related fields.
     */
    public static Emotion getEmotionById(Long id){
        Emotion e = new Emotion();
        e.setEmotionId(id);
        e.setName(getNameById(id));
        e.setDescription(getDescriptionById(id));
        
        return e;
    }
    
    /**
     * This method prints the list of all emotions with their description on the console.
     */
    public static void printAllEmotions() {
    	for(int i = 1; i < 10; i++) {
    		System.out.println(i + " - " + getNameById(Long.valueOf(i)) + " (" + getDescriptionById(Long.valueOf(i)) + ")");
    	}
    }
}
