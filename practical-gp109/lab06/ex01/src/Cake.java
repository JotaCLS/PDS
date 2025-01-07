class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;

    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    public String getCakeLayer() {
        return cakeLayer;
    }
    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }
    public int getNumCakeLayers() {
        return numCakeLayers;
    }
    public void setNumCakeLayers(int numCakeLayers) {
        this.numCakeLayers = numCakeLayers;
    }
    public Cream getMidLayerCream() {
        return midLayerCream;
    }
    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }
    public Cream getTopLayerCream() {
        return topLayerCream;
    }
    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }
    public Topping getTopping() {
        return topping;
    }
    public void setTopping(Topping topping) {
        this.topping = topping;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        int num_layers = getNumCakeLayers();
        if (num_layers == 1){
            return getCakeLayer() + " cake with " + getNumCakeLayers() + " layers, topped with " + getTopLayerCream() + " cream and " + getTopping() + ". Message says: \"" + getMessage() + "\"";
        }else{
            return getCakeLayer() + " cake with " + getNumCakeLayers() + " layers and " + getMidLayerCream()+ " cream, topped with " + getTopLayerCream() + " cream and " + getTopping() + ". Message says: \"" + getMessage() + "\"";
        }
    }
}