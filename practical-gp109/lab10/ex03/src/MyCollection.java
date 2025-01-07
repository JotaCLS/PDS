import java.util.LinkedList;
import java.util.Collection;

public class MyCollection<E> {
    private Collection<E> collection = new LinkedList<>();
    private Invoker invoker = new Invoker();

    public void add(E element) {
        CommandInterface addCommand = new AddCommand<>(collection, element);
        invoker.execute(addCommand);
    }

    public void remove(E element) {
        CommandInterface removeCommand = new RemoveCommand<>(collection, element);
        invoker.execute(removeCommand);
    }

    public void undo() {
        invoker.undo();
    }

    public void print() {
        System.out.println(collection);
    }

}
