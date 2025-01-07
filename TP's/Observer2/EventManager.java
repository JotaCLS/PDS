
import java.util.List;

public class EventManager {

    private List<Observer> observers;

    public EventManager(List<Observer> observers) {
        this.observers = observers;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.alert();
        }
    }
    
}
