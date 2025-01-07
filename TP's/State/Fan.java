public class Fan {
    private FanState fanState;

    public Fan() {
        this.fanState = new Off();
    }

    public void switchState() {
        fanState.switchState(this);
    }

    public void setFanState(FanState fanState) {
        this.fanState = fanState;
    }


}
