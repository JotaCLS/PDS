

import java.util.List;

public interface AdvancedPrinterInterface {

    public int print(Document doc);
    public List<Integer> print(List<Document> docs);
    public void showQueueJobs();
    public boolean cancelJob (int jobId);
    public void cancelAllJobs();
    
}
