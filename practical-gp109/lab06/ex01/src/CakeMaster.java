public class CakeMaster {
    private CakeBuilder cb;

    public void setCakeBuilder(CakeBuilder cakeBuilder){
        cb = cakeBuilder;
    }

    public void createCake(String message){
        cb.createCake();
        cb.setCakeShape(Shape.Circular);
        cb.addCakeLayer();
        cb.addTopLayer();
        cb.addTopping();
        cb.addMessage(message);
    }

    public void createCake(Shape shape, int num_layers, String message){
        cb.createCake();
        cb.setCakeShape(shape);
        cb.addCakeLayer();
        checkNumLayers(num_layers);
        cb.addTopLayer();
        cb.addTopping();
        cb.addMessage(message);
    }

    public void createCake(int num_layers, String message){
        cb.createCake();
        cb.setCakeShape(Shape.Circular);
        cb.addCakeLayer();
        checkNumLayers(num_layers);
        cb.addTopLayer();
        cb.addTopping();
        cb.addMessage(message);
    }

    public void createCake(Shape shape, String message){
        cb.createCake();
        cb.setCakeShape(shape);
        cb.addCakeLayer();
        cb.addTopLayer();
        cb.addTopping();
        cb.addMessage(message);
    }

    private void checkNumLayers(int num_layers){
        if (num_layers > 1){
            cb.addCreamLayer();
            for (int i = 1; i < num_layers; i++){
                cb.addCakeLayer();
            }   
        }
    }

    public Cake getCake(){
        return cb.getCake();
    }
}
