import java.util.*;

public class TestVectorGeneric {
    public static void main(String[] args) {
        // Test addElem and totalElem
        VectorGeneric<Integer> vector = new VectorGeneric<>();
        for (int i = 1; i <= 100; i++) {
            vector.addElem(i);
        }
        System.out.println("Total elements: " + vector.totalElem()); // Should print 100

        // Test getElem
        System.out.println("Element at index 5: " + vector.getElem(5)); // Should print 6

        // Test removeElem
        System.out.println("Removing element 10: " + vector.removeElem(10)); // Should print true
        System.out.println("Total elements after removal: " + vector.totalElem()); // Should print 99

        // Test Iterator
        System.out.println("Iterating through elements using Iterator:");
        Iterator<Integer> iterator1 = vector.iterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();

        // Test ListIterator
        System.out.println("Iterating through elements using ListIterator (forward):");
        ListIterator<Integer> listIterator1 = vector.listIterator();
        while (listIterator1.hasNext()) {
            System.out.print(listIterator1.next() + " ");
        }
        System.out.println();

        // Test ListIterator (backward)
        System.out.println("Iterating through elements using ListIterator (backward):");
        ListIterator<Integer> listIterator2 = vector.listIterator(vector.totalElem());
        while (listIterator2.hasPrevious()) {
            System.out.print(listIterator2.previous() + " ");
        }
        System.out.println();

        // Test multiple iterators simultaneously
        System.out.println("Testing multiple iterators simultaneously:");
        Iterator<Integer> iterator2 = vector.iterator();
        ListIterator<Integer> listIterator3 = vector.listIterator();
        while (iterator2.hasNext() && listIterator3.hasNext()) {
            System.out.print(iterator2.next() + "-" + listIterator3.next() + " ");
        }
        System.out.println();
    }
}

