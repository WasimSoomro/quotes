package quotes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;



public class QuoteGenerator {
    private List<Quote> quotes;


    public QuoteGenerator(String filePath) throws FileNotFoundException {
        Gson gson = new Gson();
        Type quoteListType = new TypeToken<List<Quote>>(){}.getType();
        this.quotes = gson.fromJson(new FileReader(filePath), quoteListType);
//        for (Quote quote : quotes) {
//            System.out.println(quote.getQuote() + " - " + quote.getAuthor());
//        }
    }

    public Quote getRandomQuote() {
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));

    }


}


//ChatGPT helped get missing imports