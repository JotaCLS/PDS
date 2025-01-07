import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // Creating simple tasks
        Employee employee1 = new Employee("John Doe", "Software Developer", 1000);
        Employee employee2 = new Employee("Jane Doe", "Software Developer", 1000);

        // Creating a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        // Creating a department
        Department department = new Department("Software Development", "New York", employees, null);
        

        List<Department> departments = new ArrayList<>();
        departments.add(department);
        

        // Creating a department
        Department department1 = new Department("Software Development", "New York", employees, departments);

        // Creating a list of departments
        

        department1.showDetails();
    }
}