public class VowelsFilter extends TextDecorator{
    
    public VowelsFilter(InterfaceTextReader textReader) {
        super(textReader);
    }

    @Override
    public boolean hasNext() {
        return textReader.hasNext();
    }

    @Override
    public String next() {
        String next = textReader.next();
        return next.replaceAll("[aeiouAEIOU]", "");
    }

    

}
