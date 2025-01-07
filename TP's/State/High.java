
public class High implements FanState {
    @Override
    public void switchState(Fan fan) {
        fan.setFanState(new Off());
        System.out.println("Off");
    }
         
}
