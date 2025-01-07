public class Doce implements Produto{
    protected String name;
    protected double weight;

    public Doce(String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + "Doce '" + name + "' - Weight : " + weight);
    }

}