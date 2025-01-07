package ex1;
import java.util.Vector;

public class Database { // Data elements
    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
    // Code to add employee
        employees.add(employee);
        return true;
    }

    public void deleteEmployee(long emp_num) {
    // Code to delete 
        for (Employee e : employees) {
            if (e.getEmpNum() == emp_num) {
                employees.remove(e);
                break;
            }
        }
    }

    public Employee[] getAllEmployees() {
    // Code to retrieve collection
        Employee[] emp = new Employee[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            emp[i] = employees.get(i);
        }
        return emp;
    }
}
