public class Person {
    private final String name;
    private final int birthYear;
    
    public Person(final String name, final int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBirthYear() {
        return birthYear;
    }
    
    public String toString() {
        return name + " (" + birthYear + ")";
    }
}
