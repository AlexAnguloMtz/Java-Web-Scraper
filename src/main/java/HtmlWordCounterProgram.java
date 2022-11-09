import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HtmlWordCounterProgram {

    public static void main(String args[]) throws IOException {

        if( args.length != 2 ) {
            System.err.println("Error. This program needs a URL and a word to search");
            System.exit(1);
        }

        final String word = args[1];

        BufferedReader reader = null;
        URL url;
        try {
           url = new URL(args[0]);
           URLConnection connection = url.openConnection();
           reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException exception) {
            System.err.println("Could not find URL");
        }

        SimpleHtmlEditorKit simpleHtmlEditorKit  = new SimpleHtmlEditorKit();

        HTMLEditorKit.Parser parser = simpleHtmlEditorKit.getParser();

        try {
            System.out.printf("\n\n\nSearched word: %s%n%n", word.toLowerCase());
            parser.parse( reader, new HtmlWordCounter(word), true);
        } catch (IOException e) {
            System.err.println("Could not read document");
            System.exit(3);
        }
    }
}
