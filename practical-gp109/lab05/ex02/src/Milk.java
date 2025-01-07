public class Milk implements Portion{
    Temperature temperature;
    State state;

    protected Milk(){
        this.temperature = Temperature.WARM;
        this.state = State.Liquid;
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
        return "Milk: Temperature " + getTemperature() + ", State " + getState();
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void setState(State state) {
        this.state = state;
    }

    

}
