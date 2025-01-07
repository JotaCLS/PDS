
public class CoffeMachine {
    private State state;

    public CoffeMachine() {
        this.state = new Desligado();
    }

    public void switchState() {
        state.switchState(this);
    }

    public void setCoffeMachineState(State state) {
        this.state = state;
    }

    
}
