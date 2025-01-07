
public class Medium implements FanState{
    @Override
    public void switchState(Fan fan) {
        fan.setFanState(new High());
        System.out.println("High speed");
    }
}
