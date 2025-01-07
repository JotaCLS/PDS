
public class Add implements CommandInterface {
    private TextEditor textEditor;
    private String text;

    public Add(TextEditor textEditor, String text){
        this.textEditor = textEditor;
        this.text = text;
    }

    public void execute(){
        textEditor.addText(text);
    }

    public void undo(){
        textEditor.removeText(text.length());

    }
    
}
