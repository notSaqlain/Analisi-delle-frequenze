import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        BookParser parser = new BookParser("../data/commedia.txt");
        
        HashMap<String, Integer> freq = new HashMap<>();
        while (true) {
            List<String> words = parser.parseLine();
            if(words == null) break;
            
            for (String word : words) {
                int value;
                if(freq.containsKey(word)){
                    value = freq.get(word);  
                }else{
                    value = 1;
                }
                freq.put(word, value);
            }          
        }

        System.out.println(freq);
    }
}
