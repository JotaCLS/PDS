public class Capitalization extends TextDecorator{
    
    public Capitalization(InterfaceTextReader textReader) {
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

        for (String w : words) {
            char chars[] = w.toCharArray();
            if (chars.length > 0) {
                chars[0] = Character.toUpperCase(chars[0]);
                if (chars.length > 1) {
                    for (int i = 1; i < chars.length; i++) {
                        chars[i] = Character.toLowerCase(chars[i]);
                    }
                    chars[chars.length - 1] = Character.toUpperCase(chars[chars.length - 1]);
                }
            }

            for(char c : chars){
                result += c;
            }
            result += " ";
        }
        return result;
    }


}
