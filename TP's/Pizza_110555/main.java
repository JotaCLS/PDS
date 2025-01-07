public class main {
    public static void main(String[] args){
        Pizzabuilder pb = new Pizzabuilder();

        Waiter waiter = new Waiter();

        waiter.setPizzabuilder(pb);

        waiter.construct("thin", "tomato", "cheese");

        Pizza pizza1 = waiter.get();

        waiter.construct("thin", "BBQ", "tomato");

        Pizza pizza2 = waiter.get();

        System.out.println(pizza1.toString());
        System.out.println(pizza2.toString());
    }
}