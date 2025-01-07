public class Low implements FanState{
    @Override
    public void switchState(Fan fan) {
        fan.setFanState(new Medium());
        System.out.println("Medium speed");
    }
}
