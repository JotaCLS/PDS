public class Bebida implements Produto {
    protected double weight;
    protected String name;

    public Bebida(String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + "Bebida '" + name + "' - Weight : " + weight);
    }

    
}
