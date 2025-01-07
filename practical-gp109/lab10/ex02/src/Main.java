public class Main {
    public static void main(String[] args) {
        Handler sushiChef = new SushiChef();
        Handler pastaChef = new PastaChef();
        Handler burgerChef = new BurgerChef();
        Handler pizzaChef = new PizzaChef();
        Handler dessertChef = new DessertChef();

        // Set up the chain of responsibility
        sushiChef.setNext(pastaChef);
        pastaChef.setNext(burgerChef);
        burgerChef.setNext(pizzaChef);
        pizzaChef.setNext(dessertChef);

        // Test the chain with different dishes
        System.out.println("Can I please get a veggie burger?");
        sushiChef.cook("veggie burger");

        System.out.println("Can I please get a Pasta Carbonara?");
        sushiChef.cook("Pasta Carbonara");

        System.out.println("Can I please get a PLAIN pizza, no toppings!?");
        sushiChef.cook("PLAIN pizza, no toppings!");

        System.out.println("Can I please get a sushi nigiri and sashimi?");
        sushiChef.cook("sushi nigiri and sashimi");

        System.out.println("Can I please get a salad with tuna?");
        sushiChef.cook("salad with tuna");

        System.out.println("Can I please get a strawberry ice cream and waffles dessert?");
        sushiChef.cook("strawberry ice cream and waffles dessert");
    }
}
