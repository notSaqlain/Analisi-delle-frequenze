import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        BookParser parser = new BookParser("commedia.txt");
        
        HashMap<String, Integer> freq = new HashMap<>();
        while (true) {
            List<String> words = parser.parseLine();
            if (words == null) break;
            
            for (String word : words) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }          
        }

        // Ordinare le parole per frequenza
        List<Map.Entry<String, Integer>> sortedFreq = new ArrayList<>(freq.entrySet());
        sortedFreq.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Preparare il CSV
        try (FileWriter csvWriter = new FileWriter("output.csv")) {
            csvWriter.write("Parola,Frequenza\n");
            int index = 0;
            int altreFreq = 0;

            for (Map.Entry<String, Integer> entry : sortedFreq) {
                if (index < 10) {
                    csvWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
                } else {
                    altreFreq += entry.getValue();
                }
                index++;
            }

            // Aggiungere la voce "altre"
            csvWriter.write("altre," + altreFreq + "\n");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file CSV: " + e.getMessage());
        }
    }
}
