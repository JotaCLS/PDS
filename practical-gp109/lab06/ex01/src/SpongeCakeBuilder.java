public class SpongeCakeBuilder implements CakeBuilder{
    protected Cake cake = new Cake();
    protected int cakeLayers = 0;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(shape);      
    }

    @Override
    public void addCakeLayer() {
        cake.setCakeLayer("Sponge");
        cakeLayers++;
    }

    @Override
    public void addCreamLayer() {
        Cream flavour = IngredientSelector.selectCream();
        cake.setMidLayerCream(flavour);
    }

    @Override
    public void addTopLayer() {
        Cream flavour = IngredientSelector.selectCream();
        cake.setTopLayerCream(flavour);
    }

    @Override
    public void addTopping() {
        Topping topping = IngredientSelector.selectTopping();      
        cake.setTopping(topping);  
    }

    @Override
    public void addMessage(String m) {
        cake.setMessage(m);
    }

    @Override
    public void createCake() {
        Cake cake = new Cake();
    }

    @Override
    public Cake getCake() {
        cake.setNumCakeLayers(cakeLayers);
        return cake;
    }

}
