package quotes;

import java.util.Arrays;

public class Quote {
    String text;
    String author;
    String test;
    String[] tags;

    public Quote() {
    }

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Quote(String text, String author, String test, String[] tags) {
        this.text = text;
        this.author = author;
        this.test = test;
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", test='" + test + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}

//Used ChatGPT to assist with quoteListType