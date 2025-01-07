
public class MediumHandler extends SupportHandler{

    public void handleRequest(int type) {
        if (type == 2) {
            System.out.println("MediumHandler: Handling the request");
        } else {
            System.out.println("MediumHandler: Passing the request to the next handler");
            if (nextHandler != null) {
                nextHandler.handleRequest(type);
            }
        }
    }  
}
