/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class App {
    public static void main(String[] args) throws IOException {

//        int arrayIndex = 3;
//        Gson gson = new Gson();
//        FileReader myFile = new FileReader("./app/src/main/resources/recentquotes.json");
//        TypeToken<ArrayList<Quote>> collectionType = new TypeToken<ArrayList<Quote>>() {
//        };
//        ArrayList<Quote> quotesList = gson.fromJson(myFile, collectionType);
//
//        System.out.println("Quote from index " + arrayIndex + ": " + quotesList.get(arrayIndex).getAuthor() + " : " + quotesList.get(arrayIndex).getText());

        URL forismaticURL = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&key=&format=json&lang=en");
        HttpURLConnection forisConnection = (HttpURLConnection) forismaticURL.openConnection();
        try {
            forisConnection.setRequestMethod("GET");
            int forisStatus = forisConnection.getResponseCode();
            System.out.println(forisStatus);

            if (forisStatus == 200) {

                InputStreamReader forisStreamReader = new InputStreamReader(forisConnection.getInputStream());

                try (BufferedReader forisBufferedReader = new BufferedReader(forisStreamReader)) {
                    String line = forisBufferedReader.readLine();
                    System.out.println(line);

                    Gson gson1 = new Gson();
                    ForismaticQuotes forismaticQuotes = gson1.fromJson(line, ForismaticQuotes.class);
                    System.out.println(forismaticQuotes);

                    // Create a new Quote object from the API response
                    Quote newQuote = new Quote(forismaticQuotes.getQuoteText(), forismaticQuotes.getQuoteAuthor());

                    // Retrieve existing quotes from the local file
                    Gson gson2 = new Gson();
                    FileReader myFile = new FileReader("app/src/main/resources/recentquotes.json");
                    TypeToken<ArrayList<Quote>> collectionType = new TypeToken<ArrayList<Quote>>() {
                    };
                    ArrayList<Quote> quotesList = gson2.fromJson(myFile, collectionType);

                    if (quotesList == null) {
                        quotesList = new ArrayList<>();
                    }

                    // Add the new quote to the list
                    quotesList.add(newQuote);

                    // Save the updated list back to the local file
                    try (FileWriter writer = new FileWriter("app/src/main/resources/recentquotes.json")) {
                        gson2.toJson(quotesList, writer);
                        System.out.println(writer);
                    }

                    System.out.println("Random Quote Added: " + newQuote.getText() + " - " + newQuote.getAuthor());
                }
            }


            if (forisStatus == 400) {
                try {
                    QuoteGenerator quoteGenerator = new QuoteGenerator("app/src/main/resources/recentquotes.json");
                    Quote quote = quoteGenerator.getRandomQuote();
                    System.out.println("Random Quote: " + quote.getText() + " - " + quote.getAuthor());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (MalformedURLException mue) {
            System.out.println("bad URL");
            mue.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Problem with API");
            ioe.printStackTrace();
        } finally {
            forisConnection.disconnect();
        }
    }
}