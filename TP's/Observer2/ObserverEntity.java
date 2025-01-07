
public class ObserverEntity implements Observer{
    private String name;

    public ObserverEntity(String name) {
        this.name = name;
    }

    public void alert() {
        System.out.println("Alert from event:" + name);
    }
    
}
