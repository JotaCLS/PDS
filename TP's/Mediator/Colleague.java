// Colleague Interface
abstract class Colleague {
    protected ChatMediator mediator;

    public Colleague(ChatMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}