import java.util.HashMap;
import java.util.Map;

public class Vocabulary {
    Map<String, String> wordsPC = new HashMap<String, String>();

    public Vocabulary() {
        wordsPC.put("mouse", "Мышь");
        wordsPC.put("keyboard", "Клавиатура");
        wordsPC.put("monitor", "Монитор");
        wordsPC.put("printer", "Принтер");
        wordsPC.put("paper", "Бумага");
    }

    public String getTranslation(String word) {
        String wordLower = word.toLowerCase();
        if (wordsPC.containsKey(wordLower)) {
            return wordsPC.get(wordLower);
        }
        return "-нет перевода-";
    }
}
