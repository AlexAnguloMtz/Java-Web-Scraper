import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.Scanner;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class HtmlWordCounter extends HTMLEditorKit.ParserCallback  {

    private int wordCounter;
    private int position;
    private boolean isReading;
    private final String searchedWord;

    public HtmlWordCounter(String word) {
        this.searchedWord = requireNonNull(word).toLowerCase();
    }

    @Override
    public void handleText(char[] data, int pos) {
            String text = new String( data );
            var scanner = new Scanner(text);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                while (line.contains(searchedWord)) {
                    wordCounter++;
                    position += line.indexOf(searchedWord);
                    System.out.printf("Coincidence #%s, %nCharacter position #%s, %n%s %n%n", wordCounter, position, line);
                    line = line.substring(line.indexOf(searchedWord) + searchedWord.length(), line.length() - 1);
                }
                position += line.length(); // Add the rest of the line before jumping to the next one
            }
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        this.isReading = true;
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {}

}