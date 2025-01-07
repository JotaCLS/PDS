import java.util.ArrayList;

public class Caixa implements Produto {
    protected double weight;
    protected String name;
    protected ArrayList<Produto> produtos;
    protected double totalWeight = 0;

    public Caixa(String name, double weight){
        this.name = name;
        this.weight = weight;
        this.produtos = new ArrayList<>(); 
    }

    @Override
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

    public void add(Produto produto){
        totalWeight = produto.getWeight();
        produtos.add(produto);
    }

    public double getTotalWeight(){
        return totalWeight;
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + "* Caixa '" + getName() + "' [Weight: " + getWeight() + " ; Total: " + getTotalWeight() + "]");
        indent.append("   ");


        for (int i = 0; i < produtos.size(); i++){
            produtos.get(i).draw();
        }
        indent.setLength(indent.length() - 3);
    }
    
}
