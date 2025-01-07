import java.util.concurrent.atomic.AtomicInteger;

public class PrintJob {

    private static AtomicInteger jobIdGenerator = new AtomicInteger(0);

    private Document document;
    private int jobId;

    public PrintJob(Document document) {
        this.document = document;
        this.jobId = jobIdGenerator.incrementAndGet();
    }

    public Document getDocument() {
        return this.document;
    }

    public int getId() {
        return this.jobId;
    }

    public String toString() {
        return "Job ID: " + this.jobId + " - Document: " + this.document.getName();
    }

    public Object run() {
        System.out.println("Finished Job" + jobId + " : " + this.document.getContent().substring(0, Math.min(20, this.document.getContent().length()-1)) + "...\"");
        return null;
    }



}
