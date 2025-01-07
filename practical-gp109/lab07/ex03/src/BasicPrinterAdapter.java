

import java.util.Collections;
import java.util.List;

public class BasicPrinterAdapter implements AdvancedPrinterInterface{

    private BasicPrinter printer;

    public BasicPrinterAdapter(BasicPrinter basicPrinter) {
        this.printer = basicPrinter;
    }

    public int print(Document doc) {
        // Converte o documento em uma lista de documentos e delega para o método correspondente da impressora básica
        return print(Collections.singletonList(doc)).get(0);
    }

    public List<Integer> print(List<Document> docs) {
    
        // Converte a lista de documentos em um array de strings
        String[] contents = new String[docs.size()];
        for (int i = 0; i < docs.size(); i++) {
            contents[i] = docs.get(i).getContent();
        }

        // Imprime os documentos
        for (int i = 0; i < contents.length; i++) {
            if (!printer.print(contents)) {
                System.out.println("Printer out of ink or paper.");
                return Collections.emptyList();
            }
        }

        return Collections.nCopies(contents.length, 1);
    }

    public void showQueueJobs() {
        // Não suportado para impressoras básicas
        System.out.println("Operação não suportada para impressoras básicas");
    }

    public boolean cancelJob(int jobId) {
        // Não suportado para impressoras básicas
        System.out.println("Operação não suportada para impressoras básicas");
        return false;
    }

    public void cancelAllJobs() {
        // Não suportado para impressoras básicas
        System.out.println("Operação não suportada para impressoras básicas");
    }
    
}
