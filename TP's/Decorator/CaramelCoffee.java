public class CaramelCoffee extends CoffeeDecorator{

    public CaramelCoffee(Coffee c) {
        super(c);
    }

    public double getCost() { // Overriding methods defined in the abstract superclass
        return super.getCost() + 0.75;
    }

    public String getDescription() {
        return super.getDescription() + ", Caramel";
    }
    
}
