public class Circle implements Geometric{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void accept(InfoVisitor v) {
        v.visit(this);
    }

    
}
