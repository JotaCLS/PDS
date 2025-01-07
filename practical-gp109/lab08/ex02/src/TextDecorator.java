public class TextDecorator implements InterfaceTextReader{
    
    protected final InterfaceTextReader textReader;

    public TextDecorator(InterfaceTextReader textReader) {
        this.textReader = textReader;
    }

    @Override
    public boolean hasNext() {
        return textReader.hasNext();
    }

    @Override
    public String next() {
        return textReader.next();
    }

    
}
