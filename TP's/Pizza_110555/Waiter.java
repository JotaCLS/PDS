public class Waiter {
    
    private Pizzabuilder pizzabuilder;

    public void setPizzabuilder(Pizzabuilder pb){
        pizzabuilder = pb;
    }

    public void construct(String dough, String sauce, String topping){
        pizzabuilder.setDough(dough)
                    .setSauce(sauce)
                    .setTopping(topping);
    }

    public Pizza get(){
        return pizzabuilder.build();
    }


}
