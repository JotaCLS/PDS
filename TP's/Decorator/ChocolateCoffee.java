public class ChocolateCoffee extends CoffeeDecorator{

    public ChocolateCoffee(Coffee c) {
        super(c);
    }

    public double getCost() { // Overriding methods defined in the abstract superclass
        return super.getCost() + 0.2;
    }

    public String getDescription() {
        return super.getDescription() + ", Chocolate";
    }
    
}
