
public class AdvancedHandler extends SupportHandler{

    public void handleRequest(int type) {
        if (type == 3) {
            System.out.println("AdvancedHandler: Handling the request");
        } else {
            System.out.println("AdvancedHandler: Passing the request to the next handler");
            if (nextHandler != null) {
                nextHandler.handleRequest(type);
            }
        }
    }
    
}
