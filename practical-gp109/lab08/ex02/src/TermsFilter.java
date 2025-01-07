public class TermsFilter extends TextDecorator{
    
    public TermsFilter(InterfaceTextReader textReader) {
        super(textReader);
    }

    @Override
    public boolean hasNext() {
        return textReader.hasNext();
    }

    @Override
    public String next() {
        String next = textReader.next();
        String words[] = next.split("\\s+");
        String result = "";
        for(String word : words){
            result += word + "\n";
        }

        return result;
    }
}
