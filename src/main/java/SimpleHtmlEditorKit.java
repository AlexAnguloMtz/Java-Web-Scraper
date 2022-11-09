import javax.swing.text.html.HTMLEditorKit;

public class SimpleHtmlEditorKit extends HTMLEditorKit{

    public  HTMLEditorKit.Parser getParser() {

        return super.getParser();
    }

}
