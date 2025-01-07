import java.util.Date;

public class Employee implements EmployeeInterface {
    private String name;

    public Employee(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start(Date date) {
        System.out.println("The employee " + this.getName() + " started working in " + date);
    }

    @Override
    public void terminate(Date date) {
        System.out.println("The employee " + this.getName() + " was terminated on " + date);
    }
        
    @Override
    public void work() {
        System.out.println("The employee is working");
    }
}
