public class main {
    
    public static void main(String[] args) {

        InterfaceTextReader textReader = new TextReader("text1.txt");
        System.out.println("Original text:");
        System.out.println(textReader.next());

        InterfaceTextReader textReader2 = new NormalizationFilter(new TextReader("text1.txt"));
        System.out.println("Normalized text:");
        System.out.println(textReader2.next());

        InterfaceTextReader textReader3 = new VowelsFilter(new TermsFilter(new TextReader("text1.txt")));
        System.out.println("Vowels removed and terms separated by new lines:");
        System.out.println(textReader3.next());

        InterfaceTextReader textReader4 = new TermsFilter(new TextReader("text1.txt"));
        System.out.println("Terms separated by new lines:");
        System.out.println(textReader4.next());


       
    }
}
