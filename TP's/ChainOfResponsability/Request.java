
public class Request {
    private int type;
    private String message;

    public Request(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
    
}
