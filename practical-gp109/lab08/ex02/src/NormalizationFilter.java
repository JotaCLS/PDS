import java.text.Normalizer;

public class NormalizationFilter extends TextDecorator{
    
    public NormalizationFilter(InterfaceTextReader textReader) {
        super(textReader);
    }

    @Override
    public boolean hasNext() {
        return textReader.hasNext();
    }

    @Override
    public String next() {
        String next = textReader.next();
        next = Normalizer.normalize(next, Normalizer.Form.NFD);
        next = next.replaceAll("[^\\p{ASCII}]", "");
        next = next.replaceAll("[\\p{Punct}]", "");

        return next;
    }

}
