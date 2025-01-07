package ex1;
import java.util.List;

import Empregado;

public interface EmployeeOperations {
    void addEmployee(Empregado emp);
    void removeEmployee(long emp_num);
    boolean isEmployee(long emp_num);
    List<Employee> listEmployees();
}
