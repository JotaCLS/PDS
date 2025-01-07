
public class main {
    public static void main(String[] args) {
        DocumentFactory documentFactory = new DocumentFactory();
        Document pdfDocument = documentFactory.createDocument("PDF");
        pdfDocument.create();
        
        Document wordDocument = documentFactory.createDocument("Word");
        wordDocument.create();
    }
    
}
