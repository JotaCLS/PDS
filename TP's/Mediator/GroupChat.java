import java.util.ArrayList;
import java.util.List;

// Concrete Mediator
class GroupChat implements ChatMediator {
    private List<Colleague> colleagues;

    public GroupChat() {
        this.colleagues = new ArrayList<>();
    }

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void sendMessage(String message, Colleague originator) {
        for (Colleague colleague : colleagues) {
            // Broadcasting message to all colleagues except the originator
            if (colleague != originator) {
                colleague.receiveMessage(message);
            }
        }
    }
}
