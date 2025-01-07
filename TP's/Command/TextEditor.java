
public class TextEditor {
    private StringBuilder textBuilder;

    public TextEditor(StringBuilder textBuilder){
        this.textBuilder = textBuilder;
    }

    public void addText(String text){
        textBuilder.append(text);
    }

    public void removeText(int length){
        int start = textBuilder.length() - length;
        if (start < 0) start = 0;
        textBuilder.delete(start, textBuilder.length());
    }

    public String getText(){
        return textBuilder.toString();
    }

}
