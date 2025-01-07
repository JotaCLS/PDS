
public class Remove implements CommandInterface {
    private TextEditor textEditor;
    private String text;

    public Remove(TextEditor textEditor, String text){
        this.textEditor = textEditor;
        this.text = text;
    }

    public void execute(){
        textEditor.removeText(text.length());
    }

    public void undo(){
        textEditor.addText(text);
    }
    
}
