public class main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(2, 3);
        Circle c = new Circle(4);
        Triangle t = new Triangle(5, 6);
        InfoVisitor v = new Visitor();
        r.accept(v);
        c.accept(v);
        t.accept(v);
    }
    
}
