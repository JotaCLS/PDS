public class Rectangle implements Geometric{
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void accept(InfoVisitor v) {
        v.visit(this);
    }
    
}
