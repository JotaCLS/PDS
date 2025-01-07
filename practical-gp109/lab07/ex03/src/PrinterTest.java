import java.util.ArrayList;
import java.util.List;

public class PrinterTest {
    
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<AdvancedPrinterInterface> printers = new ArrayList<>();

        // Adiciona impressoras AdvancedPrinter
        AdvancedPrinterInterface advancedPrinter1 = new AdvancedPrinter();
        AdvancedPrinterInterface advancedPrinter2 = new AdvancedPrinter();
        printers.add(advancedPrinter1);
        printers.add(advancedPrinter2);

        // Adiciona adaptadores para impressoras BasicPrinter
        BasicPrinter basicPrinter1 = new BasicPrinter();
        BasicPrinter basicPrinter2 = new BasicPrinter();
        AdvancedPrinterInterface basicPrinterAdapter1 = new BasicPrinterAdapter(basicPrinter1);
        AdvancedPrinterInterface basicPrinterAdapter2 = new BasicPrinterAdapter(basicPrinter2);
        printers.add(basicPrinterAdapter1);
        printers.add(basicPrinterAdapter2);

        List<Document> docs = new ArrayList<Document>();
        docs.add(new Document("text1.txt"));
        docs.add(new Document("text2.txt"));
        docs.add(new Document("text3.txt"));

        for (AdvancedPrinterInterface printer : printers) {
            printer.print(docs.get(0));   // print first document only
            pause(2000);                  // wait for a while
            printer.print(docs);
            printer.showQueueJobs();
            pause(4000);                  // wait for a while
            printer.print(docs);
            printer.cancelJob(6);
            printer.showQueueJobs();
            pause(4000);                  // wait for a while
            printer.print(docs);
            printer.cancelAllJobs();
            printer.showQueueJobs();
            pause(2000);                  // wait for a while
        }
    }
}
