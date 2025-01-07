// Concrete Colleague
class Student extends Colleague {
    private String name;

    public Student(String name, ChatMediator mediator) {
        super(mediator);
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " receives: " + message);
    }
}