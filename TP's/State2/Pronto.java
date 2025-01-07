
public class Pronto implements State{
    
    public void switchState(CoffeMachine coffeMachine) {
        coffeMachine.setCoffeMachineState(new Desligado());
        System.out.println("Café pronto");
    }
    
}
