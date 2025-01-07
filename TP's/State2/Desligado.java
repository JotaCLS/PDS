
public class Desligado implements State{

    public void switchState(CoffeMachine coffeMachine) {
        coffeMachine.setCoffeMachineState(new Pronto());
        System.out.println("Cafeteira ligada");
    }

    
    
}
