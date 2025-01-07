import java.util.LinkedList;

public class Invoker {
    private LinkedList<CommandInterface> history = new LinkedList<>();

    public void execute(CommandInterface command) {
        command.execute();
        history.add(command);
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        CommandInterface command = history.removeLast();
        command.undo();
    }


}
