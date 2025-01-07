public class Triangle implements Geometric{
    double height;
    double base;

    public Triangle(double height, double base) {
        this.height = height;
        this.base = base;
    }

    public void accept(InfoVisitor v) {
        v.visit(this);
    }
    
}
