import java.util.Random;

public class IngredientSelector {
    public static Topping selectTopping() {
        Topping[] allToppings = Topping.values();

        Random rand = new Random();
        int randIndex = rand.nextInt(allToppings.length);

        return allToppings[randIndex];
    }

    public static Cream selectCream() {
        Cream[] allCreams = Cream.values();

        Random rand = new Random();
        int randIndex = rand.nextInt(allCreams.length);

        return allCreams[randIndex];
    }
}
