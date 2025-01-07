public class Pork implements Portion {
    Temperature temperature;
    State state;

    protected Pork(){
        this.temperature = Temperature.WARM;
        this.state = State.Solid;
    }

    @Override
    public Temperature getTemperature(){
        return this.temperature;
    }

    @Override
    public State getState(){
        return this.state;
    }

    public String toString(){
        return "Pork: Temperature " + getTemperature() + ", State " + getState();
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void setState(State state) {
        this.state = state;
    }

    
}
