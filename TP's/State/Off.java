public class Off implements FanState {

    @Override
    public void switchState(Fan fan) {
        fan.setFanState(new Low());
        System.out.println("Low speed");
    }
    
}
