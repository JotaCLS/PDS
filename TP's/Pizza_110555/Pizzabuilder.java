public class Pizzabuilder {

    public String dough;
    public String sauce;
    public String topping;
    
    public Pizzabuilder() {
        
    }
    
    public Pizzabuilder setDough(String dough) {
        this.dough = dough;
        return this;
    }
    
    public Pizzabuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }
    
    public Pizzabuilder setTopping(String topping) {
        this.topping = topping;
        return this;
    }
    
    public Pizza build() {
        return new Pizza(this);
    }
    
}
