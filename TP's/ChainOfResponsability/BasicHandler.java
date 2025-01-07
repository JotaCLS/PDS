
public class BasicHandler extends SupportHandler{

    public void handleRequest(int type) {
        if (type == 1) {
            System.out.println("BasicHandler: Handling the request");
        } else {
            System.out.println("BasicHandler: Passing the request to the next handler");
            if (nextHandler != null) {
                nextHandler.handleRequest(type);
            }
        }
    }
    
}
