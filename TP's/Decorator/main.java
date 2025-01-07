public class main {
    public static void main(String[] args) {
        Coffee c = new CaramelCoffee(new MilkCoffee(new BasicCoffee()));
        System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getDescription());

        Coffee c2 = new MilkCoffee(new BasicCoffee());
        System.out.println("Cost: " + c2.getCost() + "; Ingredients: " + c2.getDescription());

        Coffee c3 = new CaramelCoffee(new BasicCoffee());
        System.out.println("Cost: " + c3.getCost() + "; Ingredients: " + c3.getDescription());
    }
    
}
