public class Main {
    public static void main(String[] args) {
        // Creating mediator
        ChatMediator mediator = new GroupChat();

        // Creating colleagues (students)
        Colleague c1 = new Student("Alice", mediator);
        Colleague c2 = new Student("Bob", mediator);
        Colleague c3 = new Student("Charlie", mediator);

        // Adding colleagues to the mediator
        mediator.addColleague(c1);
        mediator.addColleague(c2);
        mediator.addColleague(c3);

        // Sending messages
        c1.sendMessage("Hello everyone!");
        c2.sendMessage("Hey there!");
        c3.sendMessage("Hi, how are you all?");
    }
}
