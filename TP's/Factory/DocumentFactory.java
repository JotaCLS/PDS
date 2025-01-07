
public class DocumentFactory {

    public Document createDocument(String type){
        if(type.equals("PDF")){
            return new PDFDocument();
        } else if(type.equals("Word")){
            return new WordDocument();
        } else {
            return null;
        }
    }
    
}
