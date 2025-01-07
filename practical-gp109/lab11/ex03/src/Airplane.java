public class Airplane {
    private String name;
    private MediatorInterface mediator;

    public Airplane(String name, MediatorInterface mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        System.out.println(name + " sending: " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}
