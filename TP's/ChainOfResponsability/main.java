
public class main {
    public static void main(String[] args) {
        SupportHandler handler1 = new BasicHandler();
        SupportHandler handler2 = new MediumHandler();
        SupportHandler handler3 = new AdvancedHandler();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        Request request1 = new Request(1, "Request 1");
        Request request2 = new Request(2, "Request 2");
        Request request3 = new Request(3, "Request 3");

        handler1.handleRequest(request1.getType());
        handler1.handleRequest(request2.getType());
        handler1.handleRequest(request3.getType());    

    }
}

