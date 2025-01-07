public class Visitor implements InfoVisitor{
    
    public void visit(Circle c) {
        System.out.println("Circle with radius " + c.radius);
    }
    public void visit(Rectangle r) {
        System.out.println("Rectangle with width " + r.width + " and height " + r.height);
    }
    public void visit(Triangle t) {
        System.out.println("Triangle with base " + t.base + " and height " + t.height);
    }


    
}
