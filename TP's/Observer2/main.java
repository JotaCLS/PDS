
import java.util.List;

public class main {
    public static void main(String[] args) {
        ObserverEntity e1 = new ObserverEntity("e1");
        ObserverEntity e2 = new ObserverEntity("e2");
        ObserverEntity e3 = new ObserverEntity("e3");
        EventManager em = new EventManager(List.of(e1, e2, e3));

        em.notifyObservers();

        em.unsubscribe(e2);

        em.notifyObservers();
    }
}
