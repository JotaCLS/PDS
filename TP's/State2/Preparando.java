
public class Preparando implements State {

    public void switchState(CoffeMachine coffeMachine) {
        coffeMachine.setCoffeMachineState(new Pronto());
        System.out.println("Café sendo preparado");
    }
    
}
