import java.util.Random;

public class FruitJuice implements Portion {
    Temperature temperature;
    State state;
    Flavors flavor;

    protected FruitJuice(){
        this.temperature = Temperature.COLD;
        this.state = State.Liquid;
        this.flavor = flavorSelector();
    }

    public Flavors flavorSelector(){
        // Get all enum values
        Flavors[] allFlavors = Flavors.values();
        
        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(allFlavors.length);
                
        // Get the flavor at the random index
        Flavors randomFlavor = allFlavors[randomIndex];

        return randomFlavor;
    }

    @Override
    public Temperature getTemperature(){
        return this.temperature;
    }

    @Override
    public State getState(){
        return this.state;
    }

    public Flavors getFlavors(){
        return this.flavor;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setFlavor(Flavors flavor) {
        this.flavor = flavor;
    }

    public String toString(){
        return "FruitJuice: " + getFlavors() + ", Temperature " + getTemperature() + ", State " + getState();
    }
}
