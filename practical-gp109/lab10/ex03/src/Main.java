public class Main {

    public static void main(String[] args) {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        collection.add(5);
        collection.print();

        collection.remove(3);
        collection.print();

        collection.undo();
        collection.print();

        collection.add(8);
        collection.print();

        collection.undo();
        collection.print();

    }
    
}
