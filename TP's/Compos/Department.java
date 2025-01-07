import java.util.List;

public class Department implements Details {

    private String name;
    private String location;
    private List<Employee> employees;
    private List<Department> departments;

    public Department(String name, String location, List<Employee> employees, List<Department> departments) {
        this.name = name;
        this.location = location;
        this.employees = employees;
        this.departments = departments;
    }

    public void showDetails() {
        System.out.println("Department Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Employees: ");
        for (Employee employee : employees) {
            employee.showDetails();
        }
        System.out.println("Departments: ");
        if (departments == null) {
            System.out.println("No departments");
            return;
        }
        for (Department department : departments) {
            department.showDetails();
        }
    }
    
}